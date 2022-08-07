# CARSHARING

Design/Architecture exercice

## Statement:

Una compañía de car sharing desea desarrollar un sistema de identificación que permita a sus 
usuarios registrarse de forma remota. Al acceder a la aplicación móvil por primera vez, el sistema realizará 
la identificación del usuario mediante vídeo, solicitando que muestre su permiso de conducir y su cara. Una 
vez finalizado este proceso, el vídeo pasará por una serie de procesamientos(costosos en tiempo) en backend, 
luego de lo cual culminará la fase de identificación automática. Los vídeos que hayan culminado exitosamente 
esta fase, pasarán por un proceso externo de verificación manual (provisto por un tercero), donde un agente
humano confirmará o rechazará la validez de la identificación. El resto de sistemas de la compañía (incluida 
la app) deberán estar al tanto de la culminación de cada una de las dos fases. El sistema de identificación será
altamente concurrente y deberá soportar un gran número de usuarios en situaciones de gran demanda
(campañas promocionales, etc.).
Esboce, de manera general, un posible diseño para este sistema

## Resolution:

Una propuesta de diseño se puede ver en la siguiente imagen

![alt text](https://github.com/jeag2002/ElectronicID/blob/master/CarSharing/CarSharing.jpg?raw=true)

En la que se pueden las diferentes etapas:

### Etapa Captura/Preproceso Automático de Video:

* Registro Datos simples del Usuario 1 (datos personales, información de contacto, etc)
* Captura Video Carnet de conducir/Cara del usuario(Recogido en un servidor RTP/RTPC).
* Proceso Imagen a Imagen del video capturado.
* Identificación Imagen a Imagen del video capturado.
* Almacenamiento de: 
  * Datos iniciales del usuario.
  * Video carnet de conducir + Cara del usuario.
  * Resultados después de todo el proceso e identificación automático. 

### Etapa Validación Automática:

* En el portal de los operadores, se recibe una nueva notificación de que un nuevo usuario ha solicitado ingreso.
* Un operador recoge un ticket asociado al usuario 1, lo abre, revisa toda la información recogida en la etapa anterior y da su veredicto.
* Registra este veredicto en portal.

### Etapa Resolución:

* Un motor recoge todas las peticiones solucionadas, y envía su veredicto al usuario.
  * Si el veredicto es positivo, el usuario recibirá las credenciales para entrar en el sistema
  * Si el veredicto es negativo, el usuario recibirá un aviso de que su petición ha sido rechazada y el motivo por el cual ha sido rechazado.
* Usuario 1 recibe a través del canal de contacto que ha insertado previamente, el resultado de su petición. 

## Implementation:

La propuesta de arquitectura se ha pensado que sea modular con el objetivo que sea escalable y fácil de integrar en clusters; ya sea en servidores propios o en la nube.

* Para el servidor de streaming RTC/RTCP se puede plantear un servidor netty con soporte de protocolo streaming capaz de capturar video en tiempo real.

* Para los diferentes servidores de proceso de imagen/machine learning; se podría pensar en procesos python (es un lenguaje propicio para estos menesteres) trabajando 
en paralelo.

* El servidor de "Simple Data Registration Engine"; sería un servidor encargado de almacenar los datos del usuario más el video en formato raw en una base de datos, asociado
a un identificador (Por ejemplo un Back End java basado en SpringBoot; que publicase una API restful donde el usuario registrase sus datos).

* Es importante delimitar tanto el estándar de calidad que se quiere para este video; así como la duración del mismo. Estos dos parámetros son esenciales en todo el 
proceso.

* Uno o varios servidores de base de datos trabajando en cluster que sean capaces de almacenar los datos de diferentes usuarios, los videos (ya sean como blob o referenciados
a un servidor documental) y las conclusiones de todo el proceso de análisis del video; es esencial en todo el proceso. 

* El almacenamiento de las conclusiones derivadas del proceso automático; se hace de manera asíncrona; así que se impone un sistema de mensajería de colas que permita ir 
transmitiendo información entre las diferentes etapas de la arquitectura.

* La arquitectura de la fase de validación manual; es un entorno; portal, típico de tres capas; (Modelo-Vista-Controlador); en la que hay varias funcionalidades.

  * Un server push que se encarga de notificar a los operadores que hay nuevas entradas a validar.
 
  * Un sistema gestor que permita a los operadores recoger un ticket, validar toda la información y dar un veredicto.
  
* Para esto; como propuesta se podría implementar un BE con Angular/React como Frontend; y un API implementado en SpringBoot que publique una API rest encargada de gestionar 
estos tickets, y recoger las notificaciones. 

* No es aconsejable fusionar el API de registro inicial con el API de gestión de tickets ya que pertenecen conceptualmente a dos mundos distintos.

* Se implementaría además un sistema de cache distribuido que permitiese tener el control de cada uno de tickets gestionados por cada operador-nodo; así como una gestión eficiente
de las consultas (presumiblemente pesadas dada la cantidad de información que maneja)

* Por último, el proceso final sería un batch encargado de:

  * Gestionar los tickets resueltos.
  
  * Notificar a los usuarios el resultado de toda la validación
  
  * Registrar, en caso afirmativo, el usuario + passcode en la tabla correspondiente
  
  * Borrar toda la información asociada al ticket. 
  




  
  
  


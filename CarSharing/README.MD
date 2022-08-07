### CARSHARING

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

- Etapa Captura/Preproceso Automático de Video:

* Registro Datos simples del Usuario 1 (datos personales, información de contacto, etc)
* Captura Video Carnet de conducir/Cara del usuario(Recogido en un servidor RTP/RTPC).
* Proceso Imagen a Imagen del video capturado.
* Identificación Imagen a Imagen del video capturado.
* Almacenamiento de: 
** Datos iniciales del usuario.
** Video carnet de conducir + Cara del usuario.
** Resultados despues de todo el proceso e identificación automático. 

- Etapa Validación Automática:

* En el portal de los operadores, se recibe una nueva notificación de que un nuevo usuario ha solicitado ingreso.
* Un operador recoge un ticket asociado al usuario 1, lo abre, revisa toda la información recogida en la etapa anterior y da su veredicto.
* Registra este veredicto en portal.

- Etapa Resolución:

* Un motor recoge todas las peticiones solucionadas, y envia su veredicto al usuario.
** Si el veredicto es positivo, el usuario recibirá las credenciales para entrar en el sistema
** Si el veredicto es negativo, el usuario recibirá un aviso de que su petición ha sido rechazada y el motivo por el cual ha sido rechazado.
* Usuario 1 recibe a través del canal de contacto que ha insertado previamente, el resultado de su petición. 
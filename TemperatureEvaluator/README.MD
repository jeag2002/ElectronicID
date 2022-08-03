### TEMPERATURE EVALUATOR

Developing exercice. Evaluates the skills of programming, design and use of the jdk and libraries

## Statement:

Una empresa de domótica necesita la siguiente funcionalidad: dada una lista con las lecturas de temperatura
obtenidas de un termostato inteligente, se quiere determinar el mayor incremento en la temperatura entre dos 
lecturas.

Por ejemplo, dada la lista [10, 15, 18, 1, 8], el mayor incremento sería 8 (entre la lectura 1 (10) y la 3 (18)). 
Note que las lecturas en la respuesta no tienen que ser necesariamente consecutivas (1 y 3 en este caso), 
aunque sí estar en orden (3 y 1 no sería un intervalo válido). En todos los casos, las temperaturas en la lista 
aparecen en el orden en que fueron capturadas por elsensor, y son siempre mayores que 0. A continuación se 
muestran otros ejemplos de entradas y salidas deseadas:

[] => 0 [1] => 0 [1,1,1] => 0 [1,2,3] => 2 [3,2,1] => 0 [1,3,2,4] => 3

Codifique un API REST en Spring y Java ≥ 8 que, dada una llamada a /v2/stats/compute con un payload
como este {'readings': [...] }, devuelva el mayor incremento en la temperatura entre dos lecturas en la 
lista readings.

Nota: Independientemente del tamaño, su solución deberá poseer las características de un código de 
producción, atendiendo –pero no solo limitado– a calidad (limpieza, legibilidad, diseño, cobertura de tests, 
etc.), correctitud y eficiencia, minimizando en lo posible la complejidad temporal y espacial del algoritmo 
desarrollado.

## Technologies used:

- Microsoft Windows 10 Professional Edition x64
- Java 11.0.3
- Spring Boot 2.7.2
- JUnit 5
- springdoc-openapi-ui 1.6.4
- pitest-plugin 1.7.4 (mutability test engine)
- checkstyle-plugin 3.1.2 (checkstyle)

## Build:

- Go to baselib of the project and execute the following command "mvn clean install"

## Execution:

- Go to baseline and run "mvn spring-boot:run"
- Go to baseline\target and run "java -jar TemperatureEvaluator-1.0.0.jar"

## Evaluation:

- Open a browser and access to the following URL "http://localhost:8080/swagger-ui/index.html"

- You can see this. 
![alt text](https://github.com/jeag2002/ElectronicID/blob/master/TemperatureEvaluator/openapi.png?raw=true)
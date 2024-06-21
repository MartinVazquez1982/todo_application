# Todo Application

Este repositorio contiene el código fuente del proyecto "Todo" Application, desarrollado por Martin Vazquez Arispe.

## Descripción del Proyecto

"Todo" Application es una aplicación de lista de tareas que permite a los usuarios gestionar sus pendientes de manera eficiente. La aplicación está escrita en Java y utiliza Maven como herramienta de administración de dependencias y compilación.

## Requisitos Previos

Antes de poder compilar y ejecutar este proyecto, asegúrate de tener instalados los siguientes componentes:

- [Java Development Kit (JDK) 8 o superior](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)
- [Apache Maven 3.6.3 o superior](https://maven.apache.org/download.cgi)

## Compilación y Ejecución

Sigue los siguientes pasos para compilar y ejecutar la aplicación:

1. **Compilar el proyecto**

  ```bash
  mvn compile
  ```
2. **Empaquetar el proyecto**
  
  ```bash
  mvn package
  ```

3. **Ejecutar la aplicación**

 ```bash
 java -cp .\target\ToDoProject-1.0-SNAPSHOT.jar org.example.Main
 ```

## Estructura del Proyecto

La estructura principal del proyecto es la siguiente:
```
todo_application/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/
│   │   │       └── example/
│   │   │           └── Main.java
│   │   └── resources/
│   └── test/
│       ├── java/
│       └── resources/
│
├── target/
│   └── ToDoProject-1.0-SNAPSHOT.jar
│
├── pom.xml
│
└── README.md
```
- src/: Contiene el código fuente y los recursos de la aplicación.
- target/: Contiene los artefactos generados después de la compilación y empaquetado, incluyendo el JAR ejecutable.
- pom.xml: Archivo de configuración de Maven.
- README.md: Este archivo con información sobre el proyecto.

## Contribuciones

Las contribuciones son bienvenidas. Si tienes alguna mejora, sugerencia o has encontrado algún error, por favor, abre un "issue" o envía un "pull request".

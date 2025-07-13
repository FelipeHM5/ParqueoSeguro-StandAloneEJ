🅿️ ParqueoSeguro - App Administrativa con JavaFX y Spring Boot

Este proyecto es una aplicación de escritorio desarrollada con JavaFX que permite a un administrador gestionar usuarios de un sistema de parqueadero. 
La app se conecta a un backend desarrollado en Spring Boot que expone una API REST para operaciones CRUD sobre usuarios.

🔧 Tecnologías utilizadas

  Frontend (Desktop App):
        
        Java 21

        JavaFX

        HTTP Client (Java 11+)

        Jackson (para deserialización JSON)

   Backend (API REST):

        Spring Boot 3

        Spring Data JPA

        H2 o MySQL (según configuración)

        Maven

📦 Funcionalidades principales

    Visualización de la lista de usuarios desde una tabla JavaFX.

    Creación, actualización y eliminación de usuarios desde el backend.

    Botón de recarga para actualizar datos en tiempo real.

    Arquitectura modular: Controller, Service, Repository, Model.

🚀 Cómo ejecutar

Backend (Spring Boot):
  
    mvn spring-boot:run

App de escritorio (JavaFX):

    mvn javafx:run

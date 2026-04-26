# ShopFlow

**ShopFlow** es una empresa del sector e-commerce, y requiere una plataforma que permita administrar su catálogo de productos y procesar órdenes de compra de manera eficiente, escalable y segura.

- [ShopFlow](#shopflow)
  - [Descripción](#descripción)
  - [Aprendizajes esperados](#aprendizajes-esperados)
    - [Desafíos](#desafíos)
  - [Prerrequisitos](#prerrequisitos)
  - [Autor](#autor)
  - [Licencia](#licencia)

## Descripción

Este proyecto tiene como objetivo desarrollar un sistema que permita gestionar productos y órdenes, abordando problemáticas comunes como la actualización de inventario, el seguimiento de compras y la correcta relación entre entidades dentro de una arquitectura backend moderna.

## Aprendizajes esperados

Al desarrollar este proyecto, se adquieren conocimientos clave en el diseño de arquitecturas basadas en microservicios utilizando Spring Boot, junto con buenas prácticas en persistencia de datos y desarrollo de APIs REST.

Entre los principales aprendizajes destacan:

- Uso de **Spring Boot** para la creación de servicios REST.
- Implementación de **Spring Data JPA** para operaciones CRUD sin necesidad de SQL manual.
- Configuración de bases de datos **MySQL** y su integración con aplicaciones Java.
- Diseño de modelos de datos utilizando anotaciones JPA como `@Entity`, `@Table`, `@Id`, y relaciones (`@OneToMany`, `@ManyToOne`, entre otras).
- Separación de responsabilidades mediante capas:
  - Repository (acceso a datos)
  - Service (lógica de negocio)
  - Controller (exposición de endpoints)
- Manejo de transacciones con `@Transactional` para garantizar consistencia de datos.
- Uso de `ResponseEntity` para respuestas HTTP más robustas y controladas.
- Diseño y prueba de endpoints REST (GET, POST, PUT, DELETE) usando herramientas como Postman.

### Desafíos

- **Modelado correcto de entidades y relaciones**, utilizando anotaciones JPA adecuadas.
- **Persistencia y consistencia de datos**, aplicando `@Transactional` para evitar inconsistencias.
- **Configuración de base de datos**, usando migraciones de SQL para controlar las versiones del esquema.
- **Separación de responsabilidades**, aplicación de arquitectura en capas para mantener código limpio y escalable.

## Prerrequisitos

Antes de ejecutar el proyecto, asegúrate de tener instalado lo siguiente:

- **JDK 17** o superior: Es un paquete de software esencial que proporciona las herramientas necesarias para desarrollar, compilar, depurar y ejecutar aplicaciones Java. Para descargarlo ve a la [página oficial](https://www.oracle.com/java/technologies/downloads/).
- **Docker Desktop**: Es una aplicación que proporciona un entorno de desarrollo para crear, gestionar y ejecutar contenedores Docker, incluyendo Docker Engine, Docker Compose, CLI y una interfaz gráfica (GUI). Para instalarlo, visita la [página oficial](https://www.docker.com/products/docker-desktop/).

Adicionalmente, si usas [`Visual Studio Code`](https://code.visualstudio.com) como `IDE`, te recomiendo instalar las siguientes extensiones:

- Extension Pack for Java: Extensiones populares para el desarrollo en Java que ofrecen IntelliSense para Java, depuración, pruebas, compatibilidad con Maven/Gradle, gestión de proyectos y mucho más.
- Spring Boot Extension Pack: Una colección de extensiones para desarrollar aplicaciones Spring Boot.

## Autor

👨🏻‍💻 José Miguel Candia - [Correo](mailto:jo.candiah@profesor.duoc.cl) | [GitHub](https://www.github.com/jmcandia)

## Licencia

[MIT](https://choosealicense.com/licenses/mit/)

# Microservicio `auth-service`

- [Microservicio `auth-service`](#microservicio-auth-service)
  - [Descripción](#descripción)
  - [Dependencias principales](#dependencias-principales)
  - [Componentes clave](#componentes-clave)

## Descripción

Este microservicio gestiona la autenticación de usuarios. Permite registrar nuevos usuarios y permitir el inicio de sesión, incluyendo información como:

- Nombre de usuario
- Correo electrónico
- Contraseña

## Dependencias principales

- Spring Web
- Spring Security
- Spring Data JPA
- MySQL Driver
- Lombok
- Validation
- Flyway Migration

## Componentes clave

- `UserRepository`: Manejo de persistencia de usuarios.
- `AuthService`: Lógica de negocio asociada a los inicios de sesión.
- `JwtService`: Lógica de negocio asociada a la creación de Tokens JWT.
- `AuthController`: Endpoints REST para gestión de inicios de sesión.

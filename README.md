# ShopFlow

**ShopFlow** es una empresa del sector e-commerce, y requiere una plataforma que permita administrar su catálogo de productos y procesar órdenes de compra de manera eficiente, escalable y segura.

- [ShopFlow](#shopflow)
  - [Descripción](#descripción)
  - [Aprendizajes esperados](#aprendizajes-esperados)
    - [Desafíos](#desafíos)
  - [Prerrequisitos](#prerrequisitos)
  - [Ejecución](#ejecución)
    - [Levantar todo el proyecto](#levantar-todo-el-proyecto)
    - [Levantar solo la infraestructura](#levantar-solo-la-infraestructura)
    - [Detener los servicios](#detener-los-servicios)
  - [Monitoreo con Grafana](#monitoreo-con-grafana)
    - [Configurar `Loki` como fuente de datos](#configurar-loki-como-fuente-de-datos)
    - [Consultar logs](#consultar-logs)
    - [Filtros útiles](#filtros-útiles)
  - [Administración de base de datos](#administración-de-base-de-datos)
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

## Ejecución

Para ejecutar las aplicaciones y los servicios de base de datos y observabilidad necesitamos `Docker Compose` instalado.

### Levantar todo el proyecto

Para levantar la infraestructura y los microservicios juntos, ejecuta desde la raíz del proyecto:

```bash
docker compose --profile servicios up --build -d
```

Esto levanta los siguientes contenedores:

| Contenedor     | Descripción                    | Puerto |
|:---------------|:-------------------------------|:-------|
| `mysql`        | Base de datos MySQL 8          | `3306` |
| `phpmyadmin`   | Administrador de MySQL         | `8090` |
| `loki`         | Centralización de logs         | `3100` |
| `grafana`      | Visualización de logs          | `3000` |
| `auth-service` | Microservicio de autenticación | `8081` |

### Levantar solo la infraestructura

Durante el desarrollo es útil levantar únicamente la infraestructura y ejecutar los microservicios localmente para tener hot reload y depuración. Para eso ejecuta:

```bash
docker compose up -d
```

Esto levanta solo los servicios de infraestructura:

| Contenedor   | Descripción            | Puerto |
|:-------------|:-----------------------|:-------|
| `mysql`      | Base de datos MySQL 8  | `3306` |
| `phpmyadmin` | Administrador de MySQL | `8090` |
| `loki`       | Centralización de logs | `3100` |
| `grafana`    | Visualización de logs  | `3000` |

Luego, desde la carpeta de cada microservicio, ejecútalos de forma independiente:

```bash
# Microservicio de autenticación
cd auth-service
./mvnw spring-boot:run
```

### Detener los servicios

> **Importante:** usa siempre el mismo perfil con el que levantaste los servicios. De lo contrario, los contenedores de los microservicios seguirán conectados a la red y Docker mostrará el error `Network is still in use`.

Para detener los contenedores manteniendo los datos:

```bash
# Si levantaste con perfil
docker compose --profile servicios down

# Si levantaste sin perfil
docker compose down
```

Si además deseas eliminar los volúmenes (esto borrará todos los datos de la base de datos y los logs almacenados):

```bash
# Si levantaste con perfil
docker compose --profile servicios down -v

# Si levantaste sin perfil
docker compose down -v
```

## Monitoreo con Grafana

Grafana está disponible en [http://localhost:3000](http://localhost:3000) con las credenciales `admin / admin`.

### Configurar `Loki` como fuente de datos

1. Ve a **Connections → Data sources → Add data source**
2. Selecciona **Loki**
3. En la URL ingresa `http://loki:3100`
4. Haz clic en **Save & test**

### Consultar logs

Ve a **Explore**, selecciona **Loki** como fuente de datos y usa `LogQL` para consultar:

```logql
# Todos los logs de un servicio
{app="pacientes-microservice"}
{app="atenciones-microservice"}

# Solo errores
{app="pacientes-microservice"} | json | level="ERROR"

# Buscar texto específico
{app="pacientes-microservice"} |= "Paciente no encontrado"

# Logs de los dos servicios al mismo tiempo
{app=~"pacientes-microservice|atenciones-microservice"}
```

> **Nota:** Para más información acerca de `LogQL`, visita la [documentación oficial](https://grafana.com/docs/loki/latest/query/).

### Filtros útiles

| Operador  | Descripción       | Ejemplo                    |
|:----------|:------------------|:---------------------------|
| `\|=`     | Contiene texto    | `\|= "error"`              |
| `!=`      | No contiene texto | `!= "INFO"`                |
| `\|~`     | Expresión regular | `\|~ "paciente.*"`         |
| `\| json` | Parsear JSON      | `\| json \| level="ERROR"` |

## Administración de base de datos

**phpMyAdmin** es una herramienta gratuita y de código abierto, escrita en PHP, que proporciona una interfaz gráfica basada en web para gestionar bases de datos **MySQL** y **MariaDB**. Permite realizar tareas complejas como crear, modificar, eliminar tablas, ejecutar consultas SQL y gestionar usuarios sin usar la línea de comandos. Está disponible en [http://localhost:8090](http://localhost:8090) e inicia sesión automáticamente con el usuario `root`.

Desde aquí puedes:

- Explorar las bases de datos `db_hospital_vm_pacientes` y `db_hospital_vm_atenciones`
- Ejecutar consultas SQL directamente
- Revisar la estructura de las tablas y los datos generados por **Flyway**
- Exportar o importar datos en formato SQL o CSV

## Autor

👨🏻‍💻 José Miguel Candia - [Correo](mailto:jo.candiah@profesor.duoc.cl) | [GitHub](https://www.github.com/jmcandia)

## Licencia

[MIT](https://choosealicense.com/licenses/mit/)

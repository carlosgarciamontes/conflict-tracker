# 🌍 Conflict Tracker API

¡Hola! Este es mi proyecto final para la parte de Backend del módulo de Programación Fullstack.

Es una **API REST** desarrollada con Spring Boot que sirve para gestionar información sobre conflictos internacionales, los países involucrados, bandos y eventos cronológicos. El objetivo principal era aprender a montar una buena arquitectura backend, separando bien la lógica y protegiendo los datos.

## 🛠️ Tecnologías que he usado

* **Java 17** (Lógica pura y dura).
* **Spring Boot 3** (Para levantar la API rápido y gestionar inyecciones).
* **Spring Data JPA** (Para no tener que escribir SQL a mano).
* **H2 Database** (Base de datos en memoria para desarrollo, así no hay que instalar nada extra).
* **Vanilla JS + HTML** (Un frontend sencillito para probar que la API responde bien).

## 🏗️ Sobre la Arquitectura

Para este proyecto no he querido meter todo el código en el Controlador. He seguido una **Arquitectura por Capas** clásica para tenerlo todo ordenado:

1.  **Controllers:** Solo reciben la petición HTTP y devuelven la respuesta.
2.  **Services:** Aquí está toda la "chicha" (lógica de negocio).
3.  **Repositories:** Interactúan con la base de datos.

✨ **Punto clave:** He implementado el patrón **DTO (Data Transfer Object)**.
En lugar de devolver las Entidades de la base de datos directamente (que es mala práctica), he creado objetos específicos (`ConflictDTO`, `CreateConflictDTO`, etc.) y hago el mapeo manualmente en los servicios. Ha sido más trabajo, pero el código es mucho más seguro y limpio.

## 🚀 ¿Cómo probarlo en tu PC?

Es muy fácil, ni siquiera necesitas tener Maven instalado globalmente si usas el wrapper.

1.  **Clona el proyecto:**
    ```bash
    git clone [https://github.com/tu-usuario/conflict-tracker.git](https://github.com/tu-usuario/conflict-tracker.git)
    cd conflict-tracker
    ```

2.  **Arranca la aplicación:**
    En Linux/Mac:
    ```bash
    ./mvnw spring-boot:run
    ```
    En Windows:
    ```cmd
    mvnw.cmd spring-boot:run
    ```

3.  **¡Listo!**
    * Abre el navegador en: [http://localhost:8080](http://localhost:8080) (Verás la tabla con los conflictos).
    * Si quieres cotillear la base de datos: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
        * *JDBC URL:* `jdbc:h2:mem:conflicttrackerdb`
        * *Usuario:* `sa`
        * *Password:* (déjalo vacío)

## 📡 Ejemplos de uso de la API

Si usas **Postman** o **Insomnia**, aquí tienes los endpoints principales para jugar:

* `GET /api/v1/conflicts` -> Lista todo.
* `GET /api/v1/conflicts?status=ACTIVE` -> Filtra los que están activos.
* `GET /api/v1/countries` -> Lista de países.

### Crear un conflicto (Ejemplo con cURL)
Si te gusta la terminal, puedes crear un conflicto nuevo copiando esto:

```bash
curl -X POST http://localhost:8080/api/v1/conflicts \
-H "Content-Type: application/json" \
-d '{
    "name": "Conflicto de Prueba",
    "startDate": "2025-01-01",
    "status": "ACTIVE",
    "description": "Este conflicto ha sido creado desde la terminal",
    "countryIds": [1, 2]
}'
🛍️ StyleShop - Backend API

Este es el backend de StyleShop, una tienda online de ropa streetwear. Está desarrollado con Java utilizando el framework Spring Boot y proporciona una API REST completa para gestionar usuarios, productos, categorías, carritos y pedidos.

🚀 Tecnologías utilizadas

Java 17

Spring Boot 3

Spring Security + JWT (autenticación y autorización)

Spring Data JPA (Hibernate)

MySQL como base de datos

Docker (contenedor para la base de datos y PhpMyAdmin)

Lombok para simplificar código Java

IntelliJ IDEA como entorno de desarrollo

📦 Características principales de la API

Registro y login de usuarios con autenticación JWT

CRUD de productos y categorías

Visualización de productos por categoría

Carrito de compras por usuario:

Agregar, actualizar, eliminar productos del carrito

Generación de pedidos a partir del carrito

Consulta de pedidos pasados por usuario

🔐 Seguridad

Autenticación con JWT

Encriptación de contraseñas con BCrypt

Protección de endpoints sensibles

Middleware de validación de token en cada request

⚙️ Requisitos para ejecutar

Java 17

Maven

Docker (para levantar base de datos y PhpMyAdmin)

🐳 Docker (Base de datos y PhpMyAdmin)

En el proyecto hay un archivo docker-compose.yml configurado para levantar:

MySQL 8 (base de datos styleshop, usuario user, password password)

PhpMyAdmin disponible en http://localhost:8081

Para levantar los contenedores:

docker-compose up -d

🧪 Ejecución local

Clona el repositorio:

git clone https://github.com/PabloMorales088/styleShop_BACKEND.git

Asegúrate de tener la base de datos corriendo con Docker.

Abre el proyecto en IntelliJ IDEA.

Ejecuta:

mvn spring-boot:run

La API estará disponible en http://localhost:8080

🔗 Endpoints principales

Método

Endpoint

Descripción

POST

/auth/registro

Registro de nuevo usuario

POST

/auth/login

Login y generación de token JWT

GET

/api/productos

Listar productos

GET

/api/productos/{id}

Obtener producto por ID

GET

/api/categorias

Listar categorías

POST

/api/carrito

Agregar producto al carrito

GET

/api/carrito?usuarioId

Obtener productos del carrito

DELETE

/api/carrito/{id}

Eliminar producto del carrito

POST

/api/pedidos/confirmar

Confirmar pedido

GET

/api/pedidos

Listar pedidos del usuario actual

🛡️ Algunos endpoints requieren autenticación con JWT en el header: Authorization: Bearer <token>

📁 Estructura del proyecto

styleshop-backend/
├── src/
│   ├── config/         ← Seguridad, JWT y CORS
│   ├── controller/     ← Controladores REST
│   ├── dto/            ← Objetos de transferencia de datos
│   ├── mapper/         ← Mapeo entre entidades y DTOs
│   ├── model/          ← Entidades JPA
│   ├── repository/     ← Interfaces de acceso a datos
│   ├── service/        ← Lógica de negocio
│   └── util/           ← Funciones auxiliares
├── docker-compose.yml
├── pom.xml
└── application.properties

🧠 Notas adicionales

Las contraseñas de los usuarios se guardan cifradas (BCrypt).

Los tokens JWT tienen una validez de 15 días.

Puedes gestionar los datos manualmente desde PhpMyAdmin en localhost:8081

El proyecto está preparado para ser consumido por un frontend en Angular (ver repositorio styleShop_FRONTEND).

📜 Licencia

Este proyecto fue desarrollado como parte de un trabajo académico (TFG) y no tiene licencia comercial.


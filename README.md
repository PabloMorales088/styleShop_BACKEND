# StyleShop - Backend API

Este es el backend de **StyleShop**, una tienda online de ropa streetwear. Está desarrollado en Java usando Spring Boot y proporciona una API REST para gestionar usuarios, productos, categorías, carrito de compras y pedidos.

---

## Tecnologías utilizadas

- Java 17
- Spring Boot 3
- Spring Security + JWT
- Spring Data JPA (Hibernate)
- MySQL
- Docker (MySQL + PhpMyAdmin)
- Lombok
- IntelliJ IDEA

---

## Características de la API

- Registro y login de usuarios con autenticación JWT
- CRUD de productos y categorías
- Visualización de productos por categoría
- Carrito de compras:
  - Agregar productos
  - Editar cantidad o talla
  - Eliminar productos
- Confirmación de pedidos
- Consulta de pedidos del usuario autenticado

---

## Seguridad

- Autenticación con JWT
- Contraseñas cifradas con BCrypt
- Middleware que protege rutas sensibles

---

## Requisitos para ejecutar

- Java 17
- Maven
- Docker (para levantar base de datos y PhpMyAdmin)

---

## Docker (base de datos y PhpMyAdmin)

Usa el `docker-compose.yml` incluido para levantar:

- MySQL (base de datos: `styleshop`)
- PhpMyAdmin en `http://localhost:8081`

```bash
docker-compose up -d
```

---

## Cómo ejecutar el proyecto

1. Clona el repositorio:
   ```bash
   git clone https://github.com/PabloMorales088/styleShop_BACKEND.git
   ```

2. Levanta MySQL con Docker:
   ```bash
   docker-compose up -d
   ```

3. Ejecuta el backend desde IntelliJ o con:
   ```bash
   mvn spring-boot:run
   ```

El backend estará disponible en:  
`http://localhost:8080`

---

## Principales endpoints

| Método | Endpoint                  | Descripción                          |
|--------|---------------------------|--------------------------------------|
| POST   | `/auth/registro`          | Registro de usuario                  |
| POST   | `/auth/login`             | Autenticación y token JWT            |
| GET    | `/api/productos`          | Listar productos                     |
| GET    | `/api/categorias`         | Listar categorías                    |
| POST   | `/api/carrito`            | Agregar producto al carrito          |
| GET    | `/api/carrito?usuarioId=` | Ver carrito del usuario              |
| DELETE | `/api/carrito/{id}`       | Eliminar producto del carrito        |
| POST   | `/api/pedidos/confirmar`  | Confirmar un nuevo pedido            |
| GET    | `/api/pedidos`            | Ver historial de pedidos             |

---

## Notas

- Requiere token JWT para la mayoría de endpoints (`Authorization: Bearer <token>`)
- El backend está preparado para integrarse con el frontend Angular (`styleShop_FRONTEND`)

---

## Autor

Desarrollado por [PabloMorales088](https://github.com/PabloMorales088) como parte de un Trabajo de Fin de Grado.

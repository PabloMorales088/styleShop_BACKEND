# 🛍️ StyleShop - Backend API

Este es el **backend de StyleShop**, una tienda online de ropa *streetwear*. Desarrollado en **Java** con **Spring Boot**, ofrece una **API REST** completa para gestionar usuarios, productos, categorías, carrito de compras y pedidos.

---

## 🚀 Tecnologías utilizadas

- ☕ **Java 17**
- 🌱 **Spring Boot 3**
- 🔐 **Spring Security + JWT**
- 💾 **Spring Data JPA (Hibernate)**
- 🐬 **MySQL**
- 🐳 **Docker** (MySQL + PhpMyAdmin)
- 🧰 **Lombok**
- 💡 **IntelliJ IDEA**

---

## 🧩 Características de la API

- 👤 Registro y login con autenticación JWT
- 🛠️ CRUD de productos y categorías
- 📂 Visualización por categoría
- 🛒 Carrito de compras:
  - ➕ Agregar productos
  - 🔁 Editar cantidad o talla
  - ❌ Eliminar productos
- ✅ Confirmación de pedidos
- 📋 Consultar pedidos del usuario autenticado

---

## 🛡️ Seguridad

- 🔑 Autenticación con JWT
- 🔒 Contraseñas cifradas con **BCrypt**
- 🚧 Middleware para proteger rutas sensibles

---

## ⚙️ Requisitos

- ☕ Java 17
- 🛠️ Maven
- 🐳 Docker

> **Usa el `docker-compose.yml` incluido para levantar los servicios:**

```bash
docker-compose up -d
```

Servicios disponibles:
- MySQL (base de datos: `styleshop`)
- PhpMyAdmin → [http://localhost:8081](http://localhost:8081)

---

## ▶️ Cómo ejecutar

1. Clona el repositorio:

```bash
git clone https://github.com/PabloMorales088/styleShop_BACKEND.git
```

2. Levanta MySQL con Docker:

```bash
docker-compose up -d
```

3. Ejecuta el backend desde IntelliJ o por terminal:

```bash
mvn spring-boot:run
```

📍 El backend estará disponible en: [http://localhost:8080](http://localhost:8080)

---

## 📬 Principales Endpoints

| Método | Endpoint                      | Descripción                         |
|--------|-------------------------------|-------------------------------------|
| POST   | `/auth/registro`              | Registro de usuario                 |
| POST   | `/auth/login`                 | Autenticación y token JWT           |
| GET    | `/api/productos`              | Listar productos                    |
| GET    | `/api/categorias`             | Listar categorías                   |
| POST   | `/api/carrito`                | Agregar producto al carrito         |
| GET    | `/api/carrito?usuarioId=`     | Ver carrito del usuario             |
| DELETE | `/api/carrito/{id}`           | Eliminar producto del carrito       |
| POST   | `/api/pedidos/confirmar`      | Confirmar un nuevo pedido           |
| GET    | `/api/pedidos`                | Ver historial de pedidos            |

ℹ️ Requiere token JWT para la mayoría de los endpoints. Añade el header:

```
Authorization: Bearer <token>
```

---

## 👨‍💻 Autor

Desarrollado por **PabloMorales088** como parte de su **Trabajo de Fin de Grado** 🎓.
# 🛒 API Supermercado - Prueba Técnica

## 📌 Descripción

API REST desarrollada con Spring Boot para la gestión de productos, sucursales y ventas de un supermercado.

Permite realizar operaciones CRUD (crear, listar, actualizar y eliminar) utilizando una arquitectura en capas (controller, service, repository, DTO, mapper, etc.).

---

## 🧱 Arquitectura del proyecto

El proyecto está organizado en las siguientes capas:

* controller → Manejo de endpoints REST
* service → Lógica de negocio
* repository → Acceso a datos con JPA
* model → Entidades
* dto → Transferencia de datos
* mapper → Conversión entre entidades y DTOs
* exception → Manejo de errores

---

## 🚀 Tecnologías utilizadas

* Java 17
* Spring Boot
* Spring Web MVC
* Spring Data JPA
* H2 Database (para pruebas)
* MySQL (configurable)
* Lombok
* Maven
* Postman

---

## 📂 Endpoints principales

### 🛍️ Productos

* GET /api/productos → Listar productos
* POST /api/productos → Crear producto
* PUT /api/productos/{id} → Actualizar producto
* DELETE /api/productos/{id} → Eliminar producto

### 🏪 Sucursales

* GET /api/sucursales → Listar sucursales
* POST /api/sucursales → Crear sucursal

### 💰 Ventas

* GET /api/ventas → Listar ventas
* POST /api/ventas → Registrar venta

---

## 🧪 Pruebas con Postman

Se incluye una colección de Postman en la carpeta:

📁 `/postman`

Permite probar todos los endpoints de la API.

---

## ▶️ Ejecución del proyecto

1. Clonar el repositorio
2. Abrir en IntelliJ IDEA (o IDE preferido)
3. Ejecutar la clase principal:
   `PruebaTecSupermercadoApplication`
4. Acceder a:
   http://localhost:8080/api/productos

---

## 🗄️ Base de datos

El proyecto soporta:

### 🔹 H2 (en memoria)

Ideal para pruebas rápidas.

Acceso a consola:
http://localhost:8080/h2-console

---

### 🔹 MySQL (opcional)

Configurando el `application.properties`.

---

## ⚠️ Notas

* El proyecto no incluye Docker ni deploy, ya que el enfoque está en el desarrollo de la API REST.
* Se priorizó la correcta implementación de la arquitectura y los endpoints.

---

## ✍️ Autor

Valentina Pognante

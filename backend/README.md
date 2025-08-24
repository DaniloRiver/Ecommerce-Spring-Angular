# Backend - Ecommerce Spring + Angular

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=java&logoColor=white)](https://www.oracle.com/java/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-336791?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](../LICENSE)

---

##  Descripción del Backend

Este módulo proporciona la API RESTful para el proyecto **Ecommerce Spring + Angular**, construida con **Spring Boot** y destinada a gestionar toda la lógica del lado del servidor. Aquí se manejan rutas como productos, pedidos, usuarios, autenticación y validación de datos, con almacenamiento en **PostgreSQL**.

---

##  Tecnologías Utilizadas

| Categoría         | Tecnologías                                              |
|------------------|-----------------------------------------------------------|
| Framework        | Spring Boot (versión 3.x o superior)                      |
| Lenguaje         | Java 17                                                   |
| Base de datos    | PostgreSQL                                                |
| Persistencia     | Spring Data JPA / Hibernate                               |
| Seguridad        | Spring Security (opcional, si lo has integrado)           |
| Construcción     | Maven o Gradle                                            |
| Contenedores     | Docker (opcional, si lo has configurado)                 |

---

##  Funcionalidades Principales

- Exposición de API REST para operaciones CRUD sobre recursos como productos, órdenes y usuarios.
- Validación de datos entrantes y manejo centralizado de errores.
- Conexión a base de datos PostgreSQL con entidades JPA.
- Posible soporte para autenticación/roles si usas Spring Security.

---

##  Instalación y Ejecución

1. Clona el repositorio principal (si aún no lo tienes):
   ```bash
   git clone https://github.com/DaniloRiver/Ecommerce-Spring-Angular.git
   cd Ecommerce-Spring-Angular/backend

# Restaurant Tree System

Sistema desarrollado en Java 17 y Spring Boot 3 para la gestión de estructuras de árboles jerárquicos orientadas a menús de restaurantes, categorías, productos o cualquier estructura organizacional basada en nodos padre-hijo.

El proyecto fue diseñado con arquitectura modular, múltiples estrategias de algoritmos de árbol y múltiples mecanismos de persistencia seleccionables en tiempo de ejecución.

---

# Tecnologías Utilizadas

* Java 17
* Spring Boot 3.2.5
* Maven Multi-Módulo
* Docker
* Docker Compose
* PostgreSQL 17
* MongoDB 7
* Spring Data JPA
* Spring Data MongoDB
* Swagger / OpenAPI
* Eclipse IDE
* GitHub
* Trello

---

# Arquitectura del Proyecto

El proyecto está dividido en dos módulos Maven:

## tree-engine

Contiene toda la lógica de negocio relacionada con árboles.

Responsabilidades:

* Modelo TreeNode
* Algoritmos DFS
* Algoritmos BFS
* Cálculo de altura
* Cálculo de profundidad
* Obtención de ancestros
* Construcción de rutas (Path)
* Validación de ciclos
* Patrón Strategy

Este módulo no depende de Spring Boot.

Su objetivo es ser un motor reutilizable de estructuras de datos.

---

## app

Contiene toda la capa de aplicación.

Responsabilidades:

* API REST
* Swagger
* Persistencia
* Integración con PostgreSQL
* Integración con MongoDB
* Frontend de demostración
* Servicios
* DTOs
* Mappers

Este módulo utiliza Spring Boot y consume el módulo tree-engine.

---

# Estrategias Disponibles

La aplicación permite seleccionar dinámicamente la implementación del motor de árbol.

## Estrategia Custom

Implementación manual utilizando estructuras propias.

```properties
app.tree-strategy=custom
```

Implementación:

```text
CustomTreeStrategy
```

---

## Estrategia Collections

Implementación apoyada en colecciones Java.

```properties
app.tree-strategy=collections
```

Implementación:

```text
CollectionsTreeStrategy
```

---

# Persistencias Disponibles

La persistencia puede cambiarse sin modificar código.

## Memory

Persistencia temporal en memoria.

```properties
spring.profiles.active=memory
```

Ventajas:

* Rápida
* No requiere bases de datos
* Ideal para pruebas

---

## PostgreSQL

Persistencia relacional.

```properties
spring.profiles.active=postgres
```

Ventajas:

* Datos estructurados
* Relaciones consistentes
* Persistencia permanente

---

## MongoDB

Persistencia documental NoSQL.

```properties
spring.profiles.active=mongo
```

Ventajas:

* Flexible
* Documentos JSON
* Ideal para estructuras jerárquicas

---

# Levantar el Proyecto con Docker

El proyecto incluye Docker Compose para ejecutar todos los servicios necesarios.

## Construir imágenes

```bash
docker compose build
```

---

## Levantar servicios

```bash
docker compose up
```

---

## Levantar en segundo plano

```bash
docker compose up -d
```

---

## Detener servicios

```bash
docker compose down
```

---

# Servicios Docker

## Aplicación Spring Boot

Contenedor:

```text
restaurant-tree-app
```

Puerto externo:

```text
8081
```

Acceso:

```text
http://localhost:8081
```

Swagger:

```text
http://localhost:8081/swagger-ui/index.html
```

---

## MongoDB

Contenedor:

```text
restaurant-tree-mongodb
```

Puerto:

```text
27018
```

Conexión:

```text
mongodb://localhost:27018
```

Base:

```text
restaurant_tree_system
```

Colección:

```text
trees
```

---

## PostgreSQL

Contenedor:

```text
restaurant-tree-postgres
```

Puerto:

```text
5432
```

Base:

```text
restaurant_tree_db
```

Usuario:

```text
postgres
```

Contraseña:

```text
postgres
```

---

# Ejecutar desde Eclipse

Primero iniciar Docker Desktop.

Levantar PostgreSQL y MongoDB:

```bash
docker compose up -d
```

Luego ejecutar:

```text
RestaurantTreeApplication
```

desde Eclipse.

La aplicación quedará disponible en:

```text
http://localhost:8080
```

Swagger:

```text
http://localhost:8080/swagger-ui/index.html
```

---

# Endpoints Disponibles

## Crear Nodo Raíz

```http
POST /api/tree/root
```

Ejemplo:

```json
{
  "id": 100,
  "value": "MENU"
}
```

---

## Agregar Hijo

```http
POST /api/tree/{parentId}/child
```

Ejemplo:

```json
{
  "id": 101,
  "value": "COMIDA"
}
```

---

## Obtener Árbol Completo

```http
GET /api/tree/{rootId}
```

---

## Recorrido DFS

```http
GET /api/tree/{rootId}/dfs
```

---

## Recorrido BFS

```http
GET /api/tree/{rootId}/bfs
```

---

## Altura del Árbol

```http
GET /api/tree/{rootId}/height
```

---

## Validar Ciclos

```http
GET /api/tree/{rootId}/validate
```

---

## Actualizar Nodo

```http
PUT /api/tree/{id}
```

---

## Eliminar Nodo

```http
DELETE /api/tree/{id}
```

---

## Verificar Existencia

```http
GET /api/tree/{id}/exists
```

---

## Obtener Hijos

```http
GET /api/tree/{id}/children
```

---

## Profundidad

```http
GET /api/tree/{id}/depth
```

Ejemplo:

```text
Nodo MENU = profundidad 0
Nodo COMIDA = profundidad 1
Nodo TACOS = profundidad 2
```

---

## Ancestros

```http
GET /api/tree/{id}/ancestors
```

Ejemplo:

```json
[
  {
    "id": 101,
    "value": "COMIDA"
  },
  {
    "id": 100,
    "value": "MENU"
  }
]
```

---

## Ruta Completa

```http
GET /api/tree/{id}/path
```

Ejemplo:

```json
[
  {
    "id": 100,
    "value": "MENU"
  },
  {
    "id": 101,
    "value": "COMIDA"
  },
  {
    "id": 103,
    "value": "TACOS"
  }
]
```

---

# Swagger

La documentación OpenAPI se genera automáticamente mediante SpringDoc.

Acceso:

```text
http://localhost:8081/swagger-ui/index.html
```

o

```text
http://localhost:8080/swagger-ui/index.html
```

según el entorno utilizado.

Swagger permite:

* Consultar endpoints
* Probar operaciones
* Visualizar modelos
* Validar respuestas

---

# Flujo General del Sistema

```text
Frontend
    |
    v
TreeController
    |
    v
TreeService
    |
    +---- TreeAlgorithmStrategy
    |          |
    |          +---- CustomTreeStrategy
    |          |
    |          +---- CollectionsTreeStrategy
    |
    v
TreeRepository
    |
    +---- Memory
    |
    +---- PostgreSQL
    |
    +---- MongoDB
```

---

# Funcionalidades Implementadas

* Árbol n-ario
* Creación de nodos raíz
* Inserción de hijos
* Recorrido DFS
* Recorrido BFS
* Altura
* Profundidad
* Ancestros
* Path
* Validación de ciclos
* Actualización de nodos
* Eliminación de nodos
* Persistencia en memoria
* Persistencia PostgreSQL
* Persistencia MongoDB
* Swagger/OpenAPI
* Frontend demostrativo
* Docker Compose
* Arquitectura modular Maven

---

# Entregables Cubiertos

* Motor de árbol independiente (tree-engine)
* Patrón Strategy
* Estrategias custom y collections
* Persistencia Memory
* Persistencia PostgreSQL
* Persistencia MongoDB
* API REST completa
* Swagger/OpenAPI
* Docker Compose
* Frontend demostrativo
* GitHub Flow con Pull Requests
* Evidencias de validación runtime
* Documentación técnica

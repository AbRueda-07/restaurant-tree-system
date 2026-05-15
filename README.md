# Restaurant Tree System

## Descripción general

Restaurant Tree System es un proyecto académico desarrollado con Java 17, Spring Boot y Maven. El sistema permite gestionar una estructura jerárquica basada en árboles, aplicada al caso de uso de un menú de restaurante.

El proyecto utiliza una arquitectura por capas y contempla el uso de diferentes mecanismos de persistencia mediante la propiedad `app.storage`.

## Arquitectura general

El sistema sigue una arquitectura por capas:

```text
Controller -> Service -> Repository

Flujo general:

Usuario
  ↓
Frontend React / Vite
  ↓
API REST
  ↓
Backend Spring Boot
  ↓
Controller
  ↓
Service
  ↓
Repository
  ↓
Memory / PostgreSQL / MongoDB
Módulos del proyecto
restaurant-tree-system/
├── app/
├── tree-engine/
├── frontend/
├── docs/
└── pom.xml
app

Contiene la aplicación Spring Boot, controladores REST, servicios y repositorios del backend.

tree-engine

Contiene la lógica principal del árbol y sus operaciones.

frontend

Contiene la interfaz básica desarrollada con React y Vite para visualizar el árbol del menú de restaurante.


Frontend básico - ARLI WEEK II

El frontend básico fue desarrollado como parte de la responsabilidad asignada a ARLI en WEEK II.

Objetivo

Crear una interfaz inicial que permita visualizar la estructura del árbol y preparar el consumo de endpoints REST del backend.

Funcionalidades iniciales
Visualización básica del árbol.
Render jerárquico de nodos.
Consumo inicial de API REST.
Botones para consultar árbol, DFS y BFS.
Interfaz básica para pruebas visuales del sistema.

Tecnologías utilizadas
Backend
Java 17
Spring Boot
Maven
API REST

Frontend
React
Vite
JavaScript
CSS
Fetch API

Organización
GitHub
Trello

Estructura del frontend
frontend/src/
├── api/
│   └── treeApi.js
├── components/
│   └── TreeView.jsx
├── pages/
│   └── TreePage.jsx
├── App.jsx
└── App.css

Archivos principales del frontend
treeApi.js: contiene las funciones para consumir la API REST del backend.
TreeView.jsx: renderiza el árbol de forma jerárquica.
TreePage.jsx: contiene la pantalla principal del frontend.
App.jsx: conecta la aplicación con la página principal.
App.css: contiene los estilos básicos de la interfaz.
Endpoints consumidos por el frontend

El frontend consume inicialmente los siguientes endpoints del backend:

GET /api/tree/{id}
GET /api/tree/{id}/dfs
GET /api/tree/{id}/bfs

El backend debe ejecutarse en:

http://localhost:8080

El frontend se ejecuta en:

http://localhost:5173

Rama utilizada:

feature/frontend-tree-view

Commit principal:

feat: create tree visualization page

La tarjeta de Trello fue organizada con las etiquetas:

WEEK II
FRONTEND

Este avance corresponde al area FRONTEND WEEK II y fue desarrolado en la rama feature/frontend-tree-view.

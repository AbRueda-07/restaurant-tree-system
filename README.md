# Restaurant Tree System

## Validación de integración 

Se realizó una validación básica del proyecto después de actualizar la rama `main` con los últimos cambios de arquitectura.

### Comando ejecutado

```bash
mvn clean compile
Resultado obtenido
BUILD SUCCESS

La validación confirmó la compilación correcta de los módulos principales:

restaurant-tree-system
tree-engine
app

Esta validación forma parte del apoyo de pruebas correspondiente a la integración del proyecto.

Arquitectura oficial de integración

El proyecto trabaja con una arquitectura desacoplada basada en la abstracción:

TreeRepository

El servicio principal no debe depender directamente de tecnologías específicas como MongoDB o PostgreSQL. Las persistencias deben adaptarse a la arquitectura existente mediante implementaciones compatibles con TreeRepository.

Estructura indicada para la integración:

app.persistence
app.persistence.memory
app.persistence.mongo
app.persistence.postgres
app.config
app.service
Configuración app.storage

La propiedad:

app.storage=

permite seleccionar la persistencia activa del backend.

Valores esperados:

memory
postgres
mongo
Valor	Descripción
memory	Usa persistencia en memoria
postgres	Usa persistencia con PostgreSQL
mongo	Usa persistencia con MongoDB
Pruebas en modo memory

El modo memory permite validar el funcionamiento base del sistema sin depender de bases de datos externas.

Se utiliza para comprobar:

Compilación correcta del proyecto.
Funcionamiento base del backend.
Pruebas iniciales antes de integrar MongoDB o PostgreSQL.
Validación frontend-backend cuando el backend esté ejecutándose.
Validación frontend con backend

Para validar frontend con backend se debe comprobar que:

El backend Spring Boot esté ejecutándose correctamente.
El frontend pueda consumir los endpoints REST.
La visualización del árbol cargue información desde la API.
No existan errores de conexión entre frontend y backend.


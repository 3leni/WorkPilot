/*Perfecto. Ahora ya tenemos el contexto del proyecto. Con esto podemos diseñar el backend como lo haría un proyecto real.

Hasta ahora construimos la capa de identidad y seguridad. Ahora toca la parte del negocio.

        Para WorkPilot, las piezas principales que veo son:

WorkPilot
│
        ├── Users ✅ (ya lo tienes)
        │
        ├── Projects
│
        ├── Tasks
│
        ├── Teams / Members
│
        └── Workflows

Pero no vamos a crear todo de golpe. Vamos por capas.

Orden que te recomiendo
1. Project (primero)

¿Por qué?

Porque casi todo depende de un proyecto.

Un proyecto tendrá:

Nombre.
        Descripción.
Fecha de creación.
        Estado.
        Creador.
        Miembros.
        Tareas.

Algo así:

Project

        id
name
        description
status
        createdAt
updatedAt
        owner

Ejemplo:

Proyecto:
        "Crear plataforma WorkPilot"

Estado:
IN_PROGRESS

Dueño:
Juan
2. Task (segundo)

Una tarea pertenece a un proyecto.

Ejemplo:

Proyecto:
WorkPilot Backend

Tareas:

        - Crear login JWT
- Crear entidad User
- Implementar roles

Entidad:

Task

        id
title
        description
status
        priority
project
        assignedUser
createdAt
        updatedAt
3. Team / Members

Después vemos relaciones entre usuarios y proyectos.

Porque aquí aparece una pregunta de diseño:

        ¿Un usuario puede estar en muchos proyectos?

Probablemente sí.

Entonces sería:

User
 |
         | muchos
 |
Project

Esto sería una relación:

@ManyToMany

o posiblemente una entidad intermedia:

ProjectMember

que es más cercana a sistemas empresariales.

4. Workflow

Esto lo dejaría después.

Porque depende de cómo quieras manejar estados.

        Ejemplo:

TODO
 |
IN_PROGRESS
 |
DONE
Entonces el siguiente módulo será:
project/

con:

project
│
        ├── Project.java
├── ProjectDTO.java
├── ProjectRepository.java
├── ProjectService.java
└── ProjectController.java

Igual que hicimos con usuario:

Controller
    |
Service
    |
Repository
    |
Database

Antes de crear la entidad Project, necesito que definamos una decisión de negocio.

Primera pregunta importante:

Un proyecto en WorkPilot, ¿quién puede crearlo?

Opción A:

Cualquier usuario registrado puede crear proyectos.

Opción B:

Solo usuarios con rol ADMIN pueden crear proyectos.

Opción C:

Depende del equipo/organización.

¿Cuál encaja con la idea que tienes para WorkPilot?*/
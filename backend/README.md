# WorkPilot Backend

Backend API for WorkPilot, a SaaS project and task management platform designed for teams and small businesses.

---

## Overview

WorkPilot is a collaborative project management system where teams can:

- Manage projects
- Create and assign tasks
- Organize workflows
- Track progress
- Manage team members
- Authenticate securely using JWT

This project is being built to simulate a real-world enterprise backend architecture using Java and Spring Boot.

---

## Features

- User authentication and authorization
- JWT-based security
- Project management
- Task management
- Role-based access
- RESTful API
- PostgreSQL integration
- Modular architecture

---

## Tech Stack

### Backend

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok
- JWT Authentication

---

## Architecture

This project follows a modular monolith architecture organized by business domains.

```text
src/main/java/com/workpilot_backend

├── auth
├── user
├── project
├── task
├── common
└── config
```

Each module contains:

```text
controller
service
repository
entity
dto
```

---

## Project Structure

### auth
Handles:
- Authentication
- JWT logic
- Login/Register

### user
Handles:
- User management
- Roles
- Profile operations

### project
Handles:
- Project management
- Team collaboration

### task
Handles:
- Task workflows
- Status updates
- Assignments

---

## Database

The application uses PostgreSQL as the primary relational database.

---

## Running the Project

### Clone repository

```bash
git clone https://github.com/TU_USUARIO/workpilot-backend.git
```

### Enter project

```bash
cd workpilot-backend
```

### Run application

```bash
./mvnw spring-boot:run
```

---

## Future Improvements

- React frontend
- Docker support
- CI/CD pipelines
- WebSockets
- Notifications
- File uploads
- Microservices migration

---

## Author

Luz Elena Tovar Flores

Software Engineer focused on backend development, software architecture, and enterprise applications.
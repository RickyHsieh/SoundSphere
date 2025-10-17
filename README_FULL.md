
# SoundSphere

## Overview

SoundSphere is a practice tracking and self-growth application designed for musicians, athletes, and learners who want to record, visualize, and improve their daily training habits.
The goal of this project is to provide a structured yet flexible platform to log practice sessions, track progress over time, and eventually share achievements within a community.

This repository demonstrates a full-stack application using a modern Java + JavaScript tech stack, following clean architecture principles and development best practices.

---

## Features

* Practice session logging with notes, tags, and timestamps
* Visualization of progress (charts, statistics, history view)
* User authentication (JWT, optional Google OAuth2)
* Database versioning with Flyway
* Backend API with Spring Boot 3.x
* Frontend built with React + Vite
* CI/CD pipeline using GitHub Actions
* Containerized environment with Docker Compose

---

## System Architecture

```mermaid
flowchart TD

subgraph Frontend [Frontend (React + Vite)]
    UI[User Interface] --> APIRequest[REST API Calls]
end

subgraph Backend [Backend (Spring Boot 3.x)]
    API[REST Controller]
    Service[Service Layer]
    Repo[Repository Layer]
    Event[Domain Events]

    API --> Service
    Service --> Repo
    Service --> Event
end

subgraph DB [Database]
    Postgres[(PostgreSQL)]
    Flyway[Flyway Migration]
    Repo --> Postgres
    Postgres --> Flyway
end

subgraph Auth [Authentication]
    JWT[JWT Auth]
    OAuth2[OAuth2 (Google Login)]
    API --> JWT
    API --> OAuth2
end

subgraph Observability [Observability]
    Actuator[Spring Boot Actuator]
    OTel[OpenTelemetry Exporter]
    Actuator --> OTel
end

subgraph Infra [Infrastructure]
    Docker[Docker Compose]
    CI[GitHub Actions CI/CD]
    Docker --> Backend
    Docker --> DB
    CI --> Backend
    CI --> Frontend
end

Frontend --> API
Event --> Observability
```

---

## Tech Stack

### Backend

* Java 21, Spring Boot 3.x
* Spring Data JPA, Hibernate
* Spring Security (JWT, OAuth2 client)
* Flyway migration
* PostgreSQL
* Gradle (Kotlin DSL)

### Frontend

* React 18, Vite
* TypeScript
* Axios (API calls)
* TailwindCSS (styling)

### Infrastructure

* Docker, Docker Compose
* GitHub Actions (CI/CD)
* OpenTelemetry Exporter
* Spring Boot Actuator

---

## Project Structure

```
SoundSphere/
├─ backend/                Spring Boot backend
│  └─ soundsphere-service/
│     ├─ src/main/java/com/soundsphere/...
│     ├─ src/main/resources/
│     └─ build.gradle.kts
│
├─ frontend/               React frontend
│  ├─ src/
│  ├─ package.json
│  └─ vite.config.js
│
├─ db/                     Migration scripts
│  └─ migration/
│     ├─ V1__init.sql
│     └─ V2__...
│
├─ .github/
│  ├─ workflows/ci.yml     CI/CD pipeline
│  └─ PULL_REQUEST_TEMPLATE.md
│
├─ .gitignore
├─ CONTRIBUTING.md
├─ LICENSE.md
└─ README.md
```

---

## Development Setup

### Prerequisites

* JDK 21
* Node.js 20+
* Docker + Docker Compose
* PostgreSQL (if not using Docker)

### Steps

1. Clone the repository

   ```bash
   git clone git@github.com:RickyHsieh/SoundSphere.git
   cd SoundSphere
   ```

2. Start backend

   ```bash
   cd backend/soundsphere-service
   ./gradlew bootRun
   ```

3. Start frontend

   ```bash
   cd frontend
   npm install
   npm run dev
   ```

4. Access the application at `http://localhost:5173`

---

## Git Workflow

* Branch strategy: Trunk-Based

    * `main` is always stable
    * `feat/*`, `fix/*`, `docs/*`, `chore/*` for changes
* Commit convention: Conventional Commits
* PRs: small, reviewed, CI must pass before merge
* Releases: Git tags `vMAJOR.MINOR.PATCH`

---

## Roadmap

* MVP: Practice logging, user authentication, basic charts
* Phase 2: Google OAuth2, community features, badges
* Phase 3: Event-driven architecture with RabbitMQ/Kafka, advanced observability with OpenTelemetry

---

## License

This project is licensed under the MIT License.

---
# SoundSphere
Hi ; )

SoundSphere is a practice tracking and self-growth app for musicians, athletes, and learners.  
It helps users log practice sessions, visualize progress, and track their growth over time.

## Features
- Practice session logging with notes and timestamps
- Progress visualization (charts, stats, history)
- User authentication (JWT, optional Google OAuth2)
- Full-stack architecture: React + Vite + Spring Boot 3.x
- CI/CD with GitHub Actions, containerized with Docker

## Tech Stack
- Backend: Java 21, Spring Boot, PostgreSQL, Flyway
- Frontend: React, Vite, TypeScript, TailwindCSS
- Infra: Docker, GitHub Actions, OpenTelemetry

## Quick Start

### Prerequisites
- Java 21
- Docker & Docker Compose
- Git

### Setup

1. Clone the repository
```bash
git clone git@github.com:RickyHsieh/SoundSphere.git
cd SoundSphere
```

2. Configure environment variables
```bash
# Copy the example environment file
cp .env.example .env

# Edit .env and set your database credentials
# Default values are fine for local development
```

3. Start PostgreSQL database
```bash
docker compose up -d
```

4. Run the application
```bash
# Option 1: Using the startup script (recommended)
./start.sh

# Option 2: Using Gradle directly with environment variables
POSTGRES_DB=soundsphere POSTGRES_USER=postgres POSTGRES_PASSWORD=postgres ./gradlew bootRun
```

5. Access the application
```
API: http://localhost:8080
Health Check: http://localhost:8080/actuator/health
API Endpoints: http://localhost:8080/api/sessions
```

### Running Tests
```bash
# Run all tests
./gradlew test

# Run specific test
./gradlew test --tests PracticeSessionControllerTest
```

### Stopping the Application
```bash
# Stop Spring Boot (Ctrl+C in the terminal)
# Stop PostgreSQL
docker compose down
```
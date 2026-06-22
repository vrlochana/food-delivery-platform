# 🍔 Food Delivery Platform — Microservices Architecture (V1)

A production-style Food Delivery Backend Platform built using Spring Boot Microservices.

This project demonstrates scalable backend architecture using API Gateway, Service Discovery, Distributed Tracing, Authentication, Centralized Configuration, and Inter-service Communication.

---

# 🚀 Features

- User Registration & Login
- JWT Authentication & Authorization
- API Gateway Routing
- User Service
- Order Service
- Payment Service
- Service Discovery (Eureka)
- Centralized Configuration (Config Server)
- Distributed Tracing (Zipkin)
- Health Monitoring (Actuator)
- Inter-service Communication
- Context Propagation
- Production-ready Architecture

---

# 🏗 Architecture

Client
↓
API Gateway (8080)
↓
Auth Service (8081)
↓
User Service (8082)
↓
Order Service (8084)
↓
Payment Service (8085)
↓
Config Server (8888)
↓
Eureka Server (8761)
↓
Zipkin (9411)

---

# 🧰 Tech Stack

## Backend
- Java 21
- Spring Boot 3
- Spring Security
- Spring Cloud

## Microservices
- Eureka Discovery Server
- Spring Cloud Gateway
- OpenFeign
- Config Server

## Database
- PostgreSQL

## Monitoring
- Spring Actuator
- Zipkin

## Testing
- Postman

## Version Control
- Git
- GitHub

---

# 🔐 Authentication

JWT Token Based Authentication

Example:

Authorization:
Bearer <token>

---

# 📦 Services

| Service | Port |
|----------|------|
| API Gateway | 8080 |
| Auth Service | 8081 |
| User Service | 8082 |
| Order Service | 8084 |
| Payment Service | 8085 |
| Config Server | 8888 |
| Eureka Server | 8761 |
| Zipkin | 9411 |

---

# ❤️ Health Endpoints

Config Server
GET /actuator/health

User Service
GET /api/v1/users/health

Order Service
GET /api/v1/orders/health

Payment Service
GET /api/v1/payments/health

---

# 📈 Observability

Zipkin UI

http://localhost:9411/zipkin

---

# 👩‍💻 Developer

Built by Lochana R

GitHub:
(Add Repository URL)

---

# 📄 License

MIT License
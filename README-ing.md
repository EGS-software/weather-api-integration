[![pt-BR](https://img.shields.io/badge/lang-pt--BR-green)](readme.md)

# Weather API Integration

## 🎓 Academic Information

**Institution:** Universidade Regional do Noroeste do Estado do Rio Grande do Sul (UNIJUÍ)  
**Course:** Bachelor's in Software Engineering  
**Discipline:** Software Reuse  
**Professor:** Junior Marcos Bandeira  
**Assignment:** API Integration Project  
**Team Members:** João Alfredo Williges Cunha, João Victor Benetti Filipim, Pedro Schmitt.

**Academic Objective:**  
This project was developed as an evaluative requirement for the discipline, aiming to demonstrate the practical application of integration with external services via OpenFeign, history and original payload persistence in a NoSQL database (MongoDB), fault tolerance, and coverage with automated integration tests.

Furthermore, as a graduation assignment, this project also meets relevant academic criteria for the discipline, including integration with third-party APIs, the use of design patterns and architecture, layered application organization, database response logging, and automated test validation.

---

## 📖 Overview

Integration system with the public OpenWeather API to query meteorological data and expose a standardized internal REST API for consumption by client applications.

This project was developed in Java 21 with Spring Boot 3 and aims to centralize integration with an external weather API, keep business logic well separated, reduce boilerplate with Lombok, and document endpoints with SpringDoc OpenAPI.

The main proposal is to demonstrate good practices in building integrations with external services, including:
- Declarative API consumption via OpenFeign;
- Data persistence with MongoDB;
- Use of containers for local infrastructure with Docker Compose;
- Automated testing with Testcontainers, JUnit 5, and Mockito;
- Interactive documentation with Swagger/OpenAPI;
- Logging of the original payload and query history.

## 🧩 Integration Classification

According to the project's evaluation criteria, the integration performed is classified as follows:
- **Business Context:** Third-party API consumption integration / Data Enrichment. The application acts as a client system that fetches raw data from OpenWeather and transforms it into a standardized internal format.
- **Architectural Pattern:** RESTful API.
- **Communication Pattern:** Synchronous based on Request-Response. The integration occurs in real-time via HTTP protocol, using the declarative Spring Cloud OpenFeign client to block and wait for the external response.

## ✅ Academic Criteria Met

This project was structured to cover the main requirements expected in an API integration assignment within the context of the discipline:
- Integration with an external public API;
- Use of a declarative HTTP client with OpenFeign;
- Layered application organization;
- Data persistence in MongoDB;
- Storage of the original payload received from the external API;
- Query date logging;
- Data conversion to a standardized internal format;
- Automatic API documentation;
- Automated tests;
- Use of environment variables for sensitive credentials;
- Local execution with Docker Compose;
- Appropriate structure for presentation and academic evaluation.

## 🛠️ Main Stack
- Java 21
- Spring Boot 3
- Spring Cloud OpenFeign
- Lombok
- MongoDB
- Docker Compose
- SpringDoc OpenAPI (Swagger)
- JUnit 5
- Mockito
- Testcontainers

## 🏗️ Architecture

The architecture followed the Point-to-Point model, where it consumes the OpenWeather API. Furthermore, the application was organized in layers to keep the responsibility of each part of the system well separated:
- **Controller:** exposes HTTP endpoints;
- **Service:** orchestrates business logic;
- **Client/Integration (Feign):** performs communication with the external API;
- **DTOs:** represent input and output payloads;
- **Domain:** internal domain entities;
- **Config:** application configuration and Spring beans;
- **Exceptions:** centralized error handling;
- **Repository:** access to MongoDB.

## 🌐 External API Integration

The project uses the OpenWeather API as a meteorological data provider.

The integration is done with Spring Cloud OpenFeign, allowing the API to be consumed in a declarative and typed manner, with the project structure organized to facilitate maintenance and evolution.

Example of the endpoint used by the external API:
```text
/weather
The received data is processed and converted to an internal application standard to ensure consistency and facilitate use by other services.

## 💾 Persistence

The project uses MongoDB as a NoSQL database. The local infrastructure can be started via Docker Compose, with the `mongo:latest` image running on the local port:

```text
27017
```

The application runs directly on the host machine, while MongoDB is managed by Docker Compose.

**Persisted data:** The application saves the query history with relevant information, including:
- Queried city;
- Weather description;
- Temperature converted to Celsius;
- Humidity;
- Query date;
- Original payload returned by the external API.

This strategy is important to demonstrate the ability to enrich responses and maintain traceability of consumed data.

## 🧪 Tests

The project features an automated test suite to ensure the quality of the integration and API behavior. Technologies used:
- **Testcontainers** to spin up ephemeral MongoDB instances in tests;
- **JUnit 5** for test execution;
- **Mockito** for dependency mocking and Feign client simulation.

This allows validating integration scenarios without relying on static external infrastructure.

## 📄 API Documentation

The API documentation is generated with SpringDoc OpenAPI (Swagger), allowing interactive access to endpoints and direct testing via the web interface. The documentation can be accessed at:

```text
http://localhost:8080/swagger-ui/index.html
```

## ⚙️ Environment Configuration

The application requires an OpenWeather API key, which must be configured as a system or IDE environment variable. 

Mandatory variable:
```bash
OPEN_WEATHER_API_KEY
```

The key is dynamically injected into the `application.yml` file, with the exact format used in the project:
```yaml
open-weather:
  api-key: ${OPEN_WEATHER_API_KEY}
```

This approach prevents the real credential from being exposed in the Git repository.

## 🚀 How to Run Locally

### Prerequisites
Before running the project, make sure you have installed:
- Git
- Java 21
- Maven
- Docker and Docker Compose
- IDE or Bash terminal

### 1. Clone the project
```bash
git clone https://github.com/EGS-software/weather-api-integration.git
cd weather-api-integration
```

### 2. Configure the environment variable
On Linux/macOS:
```bash
export OPEN_WEATHER_API_KEY="sua_chave_aqui"
```

On Windows PowerShell:
```powershell
$env:OPEN_WEATHER_API_KEY="sua_chave_aqui"
```

### 3. Start MongoDB with Docker Compose
```bash
docker compose up -d
```

### 4. Run the application
```bash
./mvnw spring-boot:run
```

## 📁 Project Structure

```text
.
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/
│   │   │       └── com/
│   │   │           └── jawc/
│   │   │               └── api_clima/
│   │   │                   ├── controller/
│   │   │                   ├── domain/
│   │   │                   ├── dto/
│   │   │                   ├── exception/
│   │   │                   ├── feign/
│   │   │                   ├── repository/
│   │   │                   └── service/
│   │   └── resources/
│   │       └── application.yml
├── docker-compose.yml
├── pom.xml
├── README.md
└── .gitignore
```

## 💡 Usage Examples

### Weather query endpoint
```http
GET /api/weather/Ijui
```

**Expected response:**
```json
{
  "id": "6ad2dfb317d01b13326e8d19",
  "city": "Ijui",
  "description": "scattered clouds",
  "tempInCelsius": 15.01,
  "humidity": 90.0,
  "consultDate": "2026-10-16T23:38:43.422209",
  "originalPayLoad": {
    "name": "Ijuí",
    "main": {
      "temp": 288.16,
      "humidity": 90.0
    },
    "weather": [
      {
        "description": "scattered clouds"
      }
    ]
  }
}
```

## 🛑 Error Handling

The application properly handles situations such as:
- City not found (Returns structured 404);
- Communication failure with the external API (Returns structured 500).

Exceptions are centralized through `@RestControllerAdvice` to ensure standardized and readable responses.

## 🚀 Possible Evolutions

Some improvements and future extensions for the project include:
- Query caching with Redis;
- Support for multiple weather providers;
- Authentication and authorization for API access;
- Automated CI/CD.

---
**Developed by João Alfredo Williges Cunha and team.**  
*Project aimed at demonstrating integration with external services, backend best practices in Java, and the use of modern technologies for development and testing.*

# Journal App - Personal Journal Management System

A comprehensive Spring Boot application for personal journal management with sentiment analysis, weather integration, and AI-powered insights.

## 🚀 Features

- **Journal Entry Management**: Create, read, update, and delete journal entries
- **Sentiment Analysis**: AI-powered sentiment analysis of journal entries using OpenAI
- **Weather Integration**: Automatic weather data for journal entries
- **User Authentication**: JWT-based authentication and authorization
- **Email Notifications**: Weekly sentiment analysis reports via email
- **Caching**: Redis-based caching for improved performance
- **Message Queue**: Kafka integration for asynchronous processing
- **MongoDB**: NoSQL database for flexible data storage
- **RESTful API**: Comprehensive REST API with OpenAPI documentation
- **Docker Support**: Containerized deployment with Docker and Docker Compose
- **Kubernetes**: K8s deployment configurations included

## 🛠️ Technology Stack

- **Backend**: Spring Boot 3.4.0, Java 17
- **Database**: MongoDB
- **Cache**: Redis
- **Message Queue**: Apache Kafka
- **Security**: Spring Security, JWT
- **AI Integration**: OpenAI API
- **Email**: Spring Mail (Gmail SMTP)
- **Documentation**: OpenAPI 3.0 (Swagger)
- **Containerization**: Docker, Docker Compose
- **Orchestration**: Kubernetes
- **Build Tool**: Maven

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- MongoDB
- Redis
- Apache Kafka
- Docker (optional)
- Kubernetes cluster (optional)

## 🔧 Environment Variables

Create a `.env` file in the root directory with the following variables:

```env
# Server Configuration
SERVER_PORT=8082
SECRET_KEY=your-secret-key-here

# MongoDB Configuration
MONGO_URI=mongodb://localhost:27017
MONGO_DB=journalapp

# Redis Configuration
REDIS_HOST=localhost
REDIS_PORT=6379
REDIS_PASSWORD=your-redis-password

# Kafka Configuration
KAFKA_BOOTSTRAP=localhost:9092
KAFKA_GROUP_ID=journal-group
KAFKA_USERNAME=your-kafka-username
KAFKA_PASSWORD=your-kafka-password
CLIENT_ID=journal-client

# Email Configuration
MAIL_USERNAME=your-email@gmail.com
MAIL_PASSWORD=your-app-password

# OpenAI Configuration
OPENAI_API_KEY=your-openai-api-key
OPENAI_API_URL=https://api.openai.com/v1

# Weather API Configuration
WEATHER_API_KEY=your-weather-api-key
```

## 🚀 Quick Start

### Option 1: Local Development

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd jornalapp1
   ```

2. **Set up environment variables**
   ```bash
   cp .env.example .env
   # Edit .env with your configuration
   ```

3. **Start dependencies (using Docker Compose)**
   ```bash
   docker-compose up -d mongodb redis kafka
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

### Option 2: Docker Deployment

1. **Build and run with Docker Compose**
   ```bash
   docker-compose up --build
   ```

2. **Or build and run individually**
   ```bash
   docker build -t journal-app .
   docker run -p 8082:8082 --env-file .env journal-app
   ```

### Option 3: Kubernetes Deployment

1. **Apply Kubernetes configurations**
   ```bash
   kubectl apply -f k8s/
   ```

2. **Check deployment status**
   ```bash
   kubectl get pods
   kubectl get services
   ```

## 📚 API Documentation

Once the application is running, you can access the API documentation at:

- **Swagger UI**: http://localhost:8082/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8082/v3/api-docs

### Key API Endpoints

#### Authentication
- `POST /api/auth/register` - User registration
- `POST /api/auth/login` - User login
- `POST /api/auth/refresh` - Refresh JWT token

#### Journal Entries
- `GET /api/journal` - Get all journal entries
- `POST /api/journal` - Create new journal entry
- `GET /api/journal/{id}` - Get specific journal entry
- `PUT /api/journal/{id}` - Update journal entry
- `DELETE /api/journal/{id}` - Delete journal entry

#### User Management
- `GET /api/user/profile` - Get user profile
- `PUT /api/user/profile` - Update user profile
- `DELETE /api/user` - Delete user account

#### Admin Operations
- `GET /api/admin/users` - Get all users (Admin only)
- `GET /api/admin/analytics` - Get system analytics (Admin only)

## 🏗️ Project Structure

```
src/
├── main/
│   ├── java/durgaproject/jornalapp1/
│   │   ├── cache/                 # Caching configuration
│   │   ├── confogure/             # Application configuration
│   │   ├── controler/             # REST controllers
│   │   ├── dto/                   # Data Transfer Objects
│   │   ├── entity/                # MongoDB entities
│   │   ├── Enum/                  # Enumerations
│   │   ├── jwt/                   # JWT authentication
│   │   ├── model/                 # Data models
│   │   ├── repo/                  # Repository interfaces
│   │   ├── service/               # Business logic services
│   │   ├── sheduler/              # Scheduled tasks
│   │   ├── util/                  # Utility classes
│   │   └── wheatherentity/        # Weather API entities
│   └── resources/
│       ├── application.properties # Main configuration
│       ├── application-Dev.properties  # Development profile
│       ├── application-Prod.properties # Production profile
│       └── static/                # Static resources
└── test/                          # Test classes
```

## 🧪 Testing

Run the test suite:

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=SentimentConsumerServiceTest

# Run with coverage
mvn jacoco:report
```

## 📊 Monitoring and Logging

- **Application Logs**: Check `journalApp.log` for application logs
- **Health Check**: `GET /actuator/health`
- **Metrics**: `GET /actuator/metrics`

## 🔒 Security Features

- JWT-based authentication
- Password encryption with BCrypt
- CORS configuration
- Input validation
- Rate limiting (configurable)

## 🔄 Scheduled Tasks

- **Weekly Sentiment Analysis**: Automatically analyzes journal entries and sends email reports
- **Data Cleanup**: Periodic cleanup of old data
- **Health Checks**: Regular system health monitoring

## 🚀 Deployment

### Production Deployment

1. **Set production profile**
   ```bash
   export SPRING_PROFILES_ACTIVE=Prod
   ```

2. **Configure production environment variables**

3. **Deploy using your preferred method**:
   - Docker containers
   - Kubernetes
   - Traditional server deployment

### Environment-Specific Configurations

- **Development**: `application-Dev.properties`
- **Production**: `application-Prod.properties`

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🆘 Support

For support and questions:
- Create an issue in the repository
- Contact the development team
- Check the API documentation

## 🔄 Version History

- **v0.0.1-SNAPSHOT**: Initial release with core journal functionality
- Features: User authentication, journal CRUD, sentiment analysis, weather integration

---

**Note**: Make sure to replace placeholder values in configuration files with your actual credentials and endpoints before running the application.

# 📝 Journal App (Backend Only)

A secure journal application built using **Spring Boot**, **MongoDB**, and **Spring Security**. This app allows users to register, log in, and create personal journal entries.

## 🚀 Features

- ✅ User Registration
- ✅ Secure Login with Spring Security
- ✅ JWT-Free Authentication (Session-based or basic auth)
- ✅ MongoDB for data persistence
- ✅ Create, Read, Update, Delete (CRUD) operations on journal entries
- ✅ Organized code structure with layered architecture

## 🛠 Tech Stack

- Java 17+
- Spring Boot
- Spring Security
- Spring Data MongoDB
- MongoDB
- Maven

## 📁 Project Structure

JornalApp/
├── src/
│ ├── main/
│ │ ├── java/com/example/jornalapp/
│ │ │ ├── controller/
│ │ │ ├── model/
│ │ │ ├── repository/
│ │ │ ├── service/
│ │ │ ├── security/
│ │ │ └── JornalAppApplication.java
│ │ └── resources/
│ │ ├── application.properties
├── pom.xml

## 🔐 Authentication Flow

1. **Register** with username and password
2. **Login** via Spring Security
3. After login, user is redirected to the `/tasks` or `/journal` endpoint
4. Only authenticated users can access their journal entries

## 🧪 Endpoints

| Method | Endpoint       | Description            |
|--------|----------------|------------------------|
| POST   | `/register`    | Register a new user    |
| POST   | `/login`       | Login with credentials |
| GET    | `/journal`     | Get all entries        |
| POST   | `/journal`     | Create a new entry     |
| PUT    | `/journal/{id}`| Update an entry        |
| DELETE | `/journal/{id}`| Delete an entry        |

> Note: These are sample routes – adjust based on your actual implementation.

## 🧰 Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/durga6372/JornalApp.git
   cd JornalApp
Configure the MongoDB Atlas URI in application.properties.

./mvnw spring-boot:run
Test your endpoints using Postman, Thunder Client, or cURL.
👤 Author
Durga Sankar Pothal

GitHub

LinkedIn

📧 durgasankarputhal@gmail.com
📝 License
This project is open-source and available under the MIT License.

# Bookstore API

## Overview
This is a Spring Boot-based REST API for a Bookstore. It provides functionalities for managing books and user authentication (login and signup).

---

## Setup Instructions

### Prerequisites
- Java 17+
- Maven
- Docker (if using containerization)
- MySQL (or any preferred database)

### Steps to Run Locally
1. **Clone the repository:**
   ```sh
   git clone <repository-url>
   cd book-store-api
   ```
2. **Configure the database:**
   - Update `application.properties` (src/main/resources/application.properties) with your database credentials:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/bookstore
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     spring.jpa.hibernate.ddl-auto=update
     ```
3. **Build the project:**
   ```sh
   mvn clean install
   ```
4. **Run the application:**
   ```sh
   mvn spring-boot:run
   ```

### Running with Docker
1. **Build the Docker image:**
   ```sh
   docker build -t book-store-api .
   ```
2. **Run the container:**
   ```sh
   docker run -p 8080:8080 book-store-api
   ```

---

## API Endpoints

### Books API
#### Create a Book
- **Endpoint:** `POST /books`
- **Request Body:**
  ```json
  {
    "title": "The Alchemist",
    "author": "Paulo Coelho",
    "category": "Fiction",
    "price": 499.99,
    "rating": 4.8
  }
  ```
- **Response:**
  ```json
  {
    "title": "The Alchemist",
    "author": "Paulo Coelho",
    "category": "Fiction",
    "price": 499.99,
    "rating": 4.8
  }
  ```

#### Get All Books
- **Endpoint:** `GET /books`
- **Response:**
  ```json
  [
   {
    "title": "The Alchemist",
    "author": "Paulo Coelho",
    "category": "Fiction",
    "price": 499.99,
    "rating": 4.8
  }
  ]
  ```

#### Get Book by ID
- **Endpoint:** `GET /books/{id}`
- **Response:**
  ```json
  {
    "title": "The Alchemist",
    "author": "Paulo Coelho",
    "category": "Fiction",
    "price": 499.99,
    "rating": 4.8
  }
  ```

### Authentication API
#### User Signup
- **Endpoint:** `POST /auth/signup`
- **Request Body:**
  ```json
  {
    "username": "ABC",
    "password": "securepassword"
  }
  ```
- **Response:**
  ```json
  {
    "id": 1,
    "username": "john_doe"
  }
  ```

#### User Login
- **Endpoint:** `POST /auth/login`
- **Request Body:**
  ```json
  {
    "username": "ABC",
    "password": "securepassword"
  }
  ```
- **Response:**
  ```json
  "Login successful!"
  ```

---

## Assumptions & Enhancements
### Assumptions
- The API implements JWT authentication for secure access.
- Users are identified by their username and password.
- Books can be sorted and filtered based on parameters.

### Enhancements
- Implemented Pagination for `GET /books`.
- Added Sorting by price and rating.
- Filtering books by author, category, and rating.
- Searching books by title (partial matches).

### Author
Developed by **Dharani Sree M.C**.


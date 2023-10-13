# Library REST API

This is a Spring Boot project for a letgo-hiring-challenge that allows admin users to manage books
and reviews, while regular users can create reviews and access book data. The project uses Docker
Compose for containerization and MongoDB as the database.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
    - [Docker Compose](#docker-compose)
- [API Endpoints](#api-endpoints)
- [Usage](#usage)

## Prerequisites

Before running this project, ensure you have the following prerequisites:

- [Docker](https://www.docker.com/get-started) installed and running on your system.

## Getting Started

Follow the steps below to set up and run the letgo-hiring-challenge.

### Docker Compose

Docker Compose is used to containerize the project and its required services. To start the
application, use the following Docker Compose commands:

1. Clone the repository:

   git clone https://github.com/rhancav/letgo-hiring-challenge.git

2. Navigate to the project directory:

   ```cd letgo-hiring-challenge```

3. Build and start the Docker containers:

   ```docker-compose up -d```
4. The application and MongoDB will be started in separate containers. You can access the
   application Swagger UI at `http://localhost:8080/api/swagger-ui/index.html`.

5. To stop the containers when you're done, use the following command:

   ```docker-compose down```

## API Endpoints

The Library REST API provides the following endpoints:

- **Books**
- `GET /api/books`: Retrieve all books(Admins-Users).
- `GET /api/books/{bookName}`: Retrieve all books by name (Admins-Users).
- `GET /api/books/author/{name}`: Retrieve all books by author name (Admins-Users).
- `POST /api/books`: Create a new book (Admins).
- `PUT /api/books`: Update a book (Admins).
- `DELETE /api/books/{id}`: Delete a book by ID (Admins).
- `GET /api/books/{bookId}/reviews`: Retrieve all reviews by book ID (Admins-Users).
- `POST /api/books/reviews`: Create a new review for a book (Admins-Users).
- **Swagger UI**
- `/api/swagger-ui/index.html`

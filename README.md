# Library REST API

This is a Spring Boot project for a Library REST API that allows admin users to manage books and reviews, while regular users can create reviews and access book data. The project uses Docker Compose for containerization and MongoDB as the database.

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

Follow the steps below to set up and run the Library REST API.

### Docker Compose

Docker Compose is used to containerize the project and its required services. To start the application, use the following Docker Compose commands:

1. Clone the repository:

   git clone https://github.com/your-username/library-rest-api.git

2. Navigate to the project directory:

     ```cd library-rest-api```

3. Build and start the Docker containers:

   ```docker-compose up -d```
4. The application and MongoDB will be started in separate containers. You can access the application at `http://localhost:8080`.

5. To stop the containers when you're done, use the following command:

   ```docker-compose down```

## API Endpoints

The Library REST API provides the following endpoints:

- **Books**
- `GET /api/books`: Retrieve all books.
- `GET /api/books/{bookName}`: Retrieve all books by name.
- `GET /api/books/author/{name}`: Retrieve all books by author name.
- `POST /api/books`: Create a new book (admin users only).
- `PUT /api/books`: Update a book (admin users only).
- `DELETE /api/books/{id}`: Delete a book by ID (admin users only).
- `GET /api/books/{bookId}/reviews`: Retrieve all reviews by book ID.
- `POST /api/books/reviews`: Create a new review for a book (users only).
- **Swagger UI**
- `/api/swagger-ui/index.html`

## Usage

You can use tools like Postman, cURL, or any REST client to interact with the API. Make sure to set appropriate headers and permissions for admin and user actions.

- Admin users should have the necessary privileges to access admin-specific endpoints.
- User users can access user-specific endpoints to create reviews and retrieve book data.

Feel free to explore and integrate this REST API into your projects!
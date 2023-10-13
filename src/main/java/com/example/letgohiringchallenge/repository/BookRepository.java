package com.example.letgohiringchallenge.repository;

import com.example.letgohiringchallenge.model.entity.Book;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {

  List<Book> findAllByName(String name);

  List<Book> findAllByAuthor_Name(String name);
}

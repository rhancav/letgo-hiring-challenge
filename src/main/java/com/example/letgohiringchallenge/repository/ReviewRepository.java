package com.example.letgohiringchallenge.repository;

import com.example.letgohiringchallenge.model.entity.Review;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, String> {

  List<Review> findAllByBookId(String bookId);
}

package com.example.letgohiringchallenge.service;

import com.example.letgohiringchallenge.model.request.AddReviewRequest;
import com.example.letgohiringchallenge.model.request.RegisterBookRequest;
import com.example.letgohiringchallenge.model.request.UpdateBookRequest;
import com.example.letgohiringchallenge.model.response.AddReviewResponse;
import com.example.letgohiringchallenge.model.response.GetBooksResponse;
import com.example.letgohiringchallenge.model.response.GetReviewsResponse;
import com.example.letgohiringchallenge.model.response.RegisterBookResponse;

public interface BookService {

  RegisterBookResponse register(RegisterBookRequest registerBookRequest);

  void update(UpdateBookRequest updateBookRequest);

  GetBooksResponse findAll();

  GetBooksResponse findAll(String bookName);

  GetBooksResponse findAllByAuthor(String author);

  AddReviewResponse addReview(AddReviewRequest addReviewRequest);

  void delete(String bookId);

  GetReviewsResponse getReviews(String bookId);

}

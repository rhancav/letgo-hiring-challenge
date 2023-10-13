package com.example.letgohiringchallenge.model.converter;

import com.example.letgohiringchallenge.model.entity.Book;
import com.example.letgohiringchallenge.model.entity.Review;
import com.example.letgohiringchallenge.model.request.AddReviewRequest;
import com.example.letgohiringchallenge.model.request.RegisterBookRequest;
import com.example.letgohiringchallenge.model.request.UpdateBookRequest;
import com.example.letgohiringchallenge.model.response.AddReviewResponse;
import com.example.letgohiringchallenge.model.response.RegisterBookResponse;

public class Converter {

  public static Book convert(RegisterBookRequest registerBookRequest) {
    return Book.builder()
        .pages(registerBookRequest.pages())
        .name(registerBookRequest.name())
        .author(registerBookRequest.author())
        .genre(registerBookRequest.genre())
        .build();
  }

  public static Book convert(UpdateBookRequest updateBookRequest) {
    return Book.builder()
        .pages(updateBookRequest.pages())
        .name(updateBookRequest.name())
        .author(updateBookRequest.author())
        .genre(updateBookRequest.genre())
        .id(updateBookRequest.id())
        .build();
  }

  public static RegisterBookResponse convert(Book book) {
    return RegisterBookResponse.builder()
        .id(book.getId())
        .author(book.getAuthor())
        .pages(book.getPages())
        .genre(book.getGenre())
        .name(book.getName())
        .build();
  }

  public static Review convert(AddReviewRequest addReviewRequest) {
    return Review.builder()
        .bookId(addReviewRequest.bookId())
        .rating(addReviewRequest.rating())
        .username(addReviewRequest.username())
        .comment(addReviewRequest.comment())
        .build();
  }

  public static AddReviewResponse convert(Review review) {
    return AddReviewResponse.builder()
        .reviewId(review.getId())
        .bookId(review.getBookId())
        .rating(review.getRating())
        .username(review.getUsername())
        .comment(review.getComment())
        .build();
  }
}

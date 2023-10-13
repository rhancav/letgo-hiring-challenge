package com.example.letgohiringchallenge.service.impl;

import com.example.letgohiringchallenge.model.converter.Converter;
import com.example.letgohiringchallenge.model.entity.Book;
import com.example.letgohiringchallenge.model.entity.Review;
import com.example.letgohiringchallenge.model.exception.DocumentNotFoundException;
import com.example.letgohiringchallenge.model.exception.constant.Errors;
import com.example.letgohiringchallenge.model.request.AddReviewRequest;
import com.example.letgohiringchallenge.model.request.RegisterBookRequest;
import com.example.letgohiringchallenge.model.request.UpdateBookRequest;
import com.example.letgohiringchallenge.model.response.AddReviewResponse;
import com.example.letgohiringchallenge.model.response.GetBooksResponse;
import com.example.letgohiringchallenge.model.response.GetReviewsResponse;
import com.example.letgohiringchallenge.model.response.RegisterBookResponse;
import com.example.letgohiringchallenge.repository.BookRepository;
import com.example.letgohiringchallenge.repository.ReviewRepository;
import com.example.letgohiringchallenge.service.BookService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;
  private final ReviewRepository reviewRepository;

  @Override
  public RegisterBookResponse register(RegisterBookRequest registerBookRequest) {
    Book book = Converter.convert(registerBookRequest);
    book.setInsertDate(LocalDateTime.now());
    book.setUpdateDate(LocalDateTime.now());
    book.setRating(BigDecimal.ZERO);
    return Converter.convert(bookRepository.insert(book));
  }

  @Override
  public void update(UpdateBookRequest updateBookRequest) {
    Book byId = bookRepository.findById(updateBookRequest.id())
        .orElseThrow(() -> new DocumentNotFoundException(Errors.DOCUMENT_NOT_FOUND));

    Book book = Converter.convert(updateBookRequest);
    book.setUpdateDate(LocalDateTime.now());
    book.setRating(byId.getRating());
    book.setInsertDate(byId.getInsertDate());
    bookRepository.save(book);
  }

  @Override
  public GetBooksResponse findAll() {
    List<Book> all = bookRepository.findAll();
    GetBooksResponse getBooksResponse = new GetBooksResponse();
    getBooksResponse.setBooks(all);
    return getBooksResponse;
  }

  @Override
  public GetBooksResponse findAll(String bookName) {
    List<Book> allByName = bookRepository.findAllByName(bookName);
    GetBooksResponse getBooksResponse = new GetBooksResponse();
    getBooksResponse.setBooks(allByName);
    return getBooksResponse;
  }

  @Override
  public GetBooksResponse findAllByAuthor(String name) {
    GetBooksResponse getBooksResponse = new GetBooksResponse();
    getBooksResponse.setBooks(bookRepository.findAllByAuthor_Name(name));
    return getBooksResponse;
  }

  @Override
  public AddReviewResponse addReview(AddReviewRequest addReviewRequest) {
    Book book = bookRepository.findById(addReviewRequest.bookId())
        .orElseThrow(() -> new DocumentNotFoundException(Errors.DOCUMENT_NOT_FOUND));

    Review review = reviewRepository.insert(
        Review.builder().comment(addReviewRequest.comment()).username(addReviewRequest.username())
            .rating(addReviewRequest.rating()).bookId(addReviewRequest.bookId()).build());

    updateBookRating(book);
    return Converter.convert(review);
  }

  @Override
  public void delete(String bookId) {
    if (!bookRepository.existsById(bookId)) {
      throw new DocumentNotFoundException(Errors.DOCUMENT_NOT_FOUND);
    }
    bookRepository.deleteById(bookId);
  }

  @Override
  public GetReviewsResponse getReviews(String bookId) {
    List<Review> allByBookId = reviewRepository.findAllByBookId(bookId);
    return new GetReviewsResponse(allByBookId);
  }

  private void updateBookRating(Book book) {
    List<Review> reviews = reviewRepository.findAllByBookId(book.getId());
    int ratingSum = reviews.stream().map(Review::getRating).reduce(Integer::sum).get();
    float averageRating = (float) ratingSum / reviews.size();
    book.setRating(new BigDecimal(String.valueOf(averageRating).substring(0, 3)));
    book.setUpdateDate(LocalDateTime.now());
    bookRepository.save(book);
  }
}

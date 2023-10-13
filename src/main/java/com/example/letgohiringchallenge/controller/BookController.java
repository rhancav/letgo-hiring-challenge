package com.example.letgohiringchallenge.controller;

import com.example.letgohiringchallenge.model.exception.constant.Errors;
import com.example.letgohiringchallenge.model.request.AddReviewRequest;
import com.example.letgohiringchallenge.model.request.RegisterBookRequest;
import com.example.letgohiringchallenge.model.request.UpdateBookRequest;
import com.example.letgohiringchallenge.model.response.AddReviewResponse;
import com.example.letgohiringchallenge.model.response.GetBooksResponse;
import com.example.letgohiringchallenge.model.response.GetReviewsResponse;
import com.example.letgohiringchallenge.model.response.RegisterBookResponse;
import com.example.letgohiringchallenge.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Validated
public class BookController {

  private final BookService bookService;

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  public ResponseEntity<RegisterBookResponse> register(
      @RequestBody @Valid RegisterBookRequest registerBookRequest) {
    return new ResponseEntity<>(bookService.register(registerBookRequest), HttpStatus.CREATED);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping
  public ResponseEntity<?> update(@RequestBody @Valid UpdateBookRequest updateBookRequest) {
    bookService.update(updateBookRequest);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{bookId}")
  public ResponseEntity<?> delete(
      @PathVariable @Valid @NotBlank(message = Errors.FIELD_CANNOT_BE_BLANK) String bookId) {
    bookService.delete(bookId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
  @GetMapping("/{bookName}")
  public ResponseEntity<GetBooksResponse> findAll(
      @PathVariable @Valid @NotBlank(message = Errors.FIELD_CANNOT_BE_BLANK) String bookName) {
    return new ResponseEntity<>(bookService.findAll(bookName), HttpStatus.OK);
  }

  @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
  @GetMapping
  public ResponseEntity<GetBooksResponse> findAll() {
    return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
  }

  @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
  @GetMapping("/author/{name}")
  public ResponseEntity<GetBooksResponse> findAllByAuthor(
      @PathVariable @Valid @NotBlank(message = Errors.FIELD_CANNOT_BE_BLANK) String name) {
    return new ResponseEntity<>(bookService.findAllByAuthor(name), HttpStatus.OK);
  }

  @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
  @PostMapping("/reviews")
  public ResponseEntity<AddReviewResponse> addReview(
      @RequestBody @Valid AddReviewRequest addReviewRequest) {
    return new ResponseEntity<>(bookService.addReview(addReviewRequest), HttpStatus.CREATED);
  }

  @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
  @GetMapping("/{bookId}/reviews")
  public ResponseEntity<GetReviewsResponse> getReviews(
      @PathVariable @Valid @NotBlank(message = Errors.FIELD_CANNOT_BE_BLANK) String bookId) {
    return new ResponseEntity<>(bookService.getReviews(bookId), HttpStatus.OK);
  }
}

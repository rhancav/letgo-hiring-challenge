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
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
  @Operation(description = "Registers a new book (Admin only).")
  public ResponseEntity<RegisterBookResponse> register(
      @RequestBody @Valid RegisterBookRequest registerBookRequest) {
    return new ResponseEntity<>(bookService.register(registerBookRequest), HttpStatus.CREATED);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping
  @Operation(description = "Updates a book (Admin only), throws NoDocumentFoundException if no book found with giving id.")
  public ResponseEntity<?> update(@RequestBody @Valid UpdateBookRequest updateBookRequest) {
    bookService.update(updateBookRequest);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{bookId}")
  @Operation(description = "Deletes a book (Admin only), throws NoDocumentFoundException if no book found with given id.")
  public ResponseEntity<?> delete(
      @PathVariable @Valid @NotBlank(message = Errors.FIELD_CANNOT_BE_BLANK) String bookId) {
    bookService.delete(bookId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
  @GetMapping("/{bookName}")
  @Operation(description = "Fetches all the available books by given book name.")
  public ResponseEntity<GetBooksResponse> findAll(
      @PathVariable @Valid @NotBlank(message = Errors.FIELD_CANNOT_BE_BLANK) String bookName) {
    return new ResponseEntity<>(bookService.findAll(bookName), HttpStatus.OK);
  }

  @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
  @GetMapping
  @Operation(description = "Fetches all the available books.")
  public ResponseEntity<GetBooksResponse> findAll() {
    return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
  }

  @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
  @GetMapping("/author/{name}")
  @Operation(description = "Fetches all the available books by given author name.")
  public ResponseEntity<GetBooksResponse> findAllByAuthor(
      @PathVariable @Valid @NotBlank(message = Errors.FIELD_CANNOT_BE_BLANK) String name) {
    return new ResponseEntity<>(bookService.findAllByAuthor(name), HttpStatus.OK);
  }

  @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
  @PostMapping("/reviews")
  @Operation(description = "Adds a review and updates the book rating.")
  public ResponseEntity<AddReviewResponse> addReview(
      @RequestBody @Valid AddReviewRequest addReviewRequest) {
    return new ResponseEntity<>(bookService.addReview(addReviewRequest), HttpStatus.CREATED);
  }

  @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
  @GetMapping("/{bookId}/reviews")
  @Operation(description = "Fetches all the reviews by given book id.")
  public ResponseEntity<GetReviewsResponse> getReviews(
      @PathVariable @Valid @NotBlank(message = Errors.FIELD_CANNOT_BE_BLANK) String bookId) {
    return new ResponseEntity<>(bookService.getReviews(bookId), HttpStatus.OK);
  }
}

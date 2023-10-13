package com.example.letgohiringchallenge.service;

import com.example.letgohiringchallenge.model.converter.Converter;
import com.example.letgohiringchallenge.model.entity.Author;
import com.example.letgohiringchallenge.model.entity.Book;
import com.example.letgohiringchallenge.model.entity.Review;
import com.example.letgohiringchallenge.model.exception.DocumentNotFoundException;
import com.example.letgohiringchallenge.model.request.RegisterBookRequest;
import com.example.letgohiringchallenge.model.request.UpdateBookRequest;
import com.example.letgohiringchallenge.model.response.AddReviewResponse;
import com.example.letgohiringchallenge.model.response.GetBooksResponse;
import com.example.letgohiringchallenge.model.response.RegisterBookResponse;
import com.example.letgohiringchallenge.repository.BookRepository;
import com.example.letgohiringchallenge.repository.ReviewRepository;
import com.example.letgohiringchallenge.service.impl.BookServiceImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

  @InjectMocks
  BookServiceImpl bookService;
  @Mock
  BookRepository bookRepository;
  @Mock
  ReviewRepository reviewRepository;

  @Test
  void register() {
    RegisterBookRequest request = new RegisterBookRequest("Hasan", new Author("Hasan", "Bıdı Bıdı"),
        100, "Roman");
    Book expected = Converter.convert(request);
    expected.setId("ASD1251");
    when(bookRepository.insert(any(Book.class))).thenReturn(expected);
    RegisterBookResponse actual = bookService.register(request);

    assertAll(
        () -> assertEquals(expected.getId(), actual.getId()),
        () -> assertEquals(expected.getPages(), actual.getPages()),
        () -> assertEquals(expected.getAuthor(), actual.getAuthor())
    );
  }

  @Test
  void update_should_throw_document_not_found() {
    when(bookRepository.findById(any())).thenReturn(Optional.empty());
    assertThrows(DocumentNotFoundException.class,
        () -> bookService.update(new UpdateBookRequest("asfgsdg", null, null, 0, null)));
  }

  @Test
  void findAll_response_list_exact_size_1() {
    when(bookRepository.findAll()).thenReturn(List.of(new Book()));
    GetBooksResponse actual = bookService.findAll();
    assertEquals(1, actual.getBooks().size());
  }

  @Test
  void testFindAll() {
    when(bookRepository.findAllByName(any())).thenReturn(List.of(new Book()));
    GetBooksResponse actual = bookService.findAll("test1");
    assertEquals(1, actual.getBooks().size());
  }

  @Test
  void findAllByAuthor() {
    when(bookRepository.findAllByAuthor_Name(any())).thenReturn(List.of(new Book()));
    GetBooksResponse actual = bookService.findAll("test1");
    assertEquals(1, actual.getBooks().size());
  }

  @Test
  void addReview() {
    when(reviewRepository.insert(any(Review.class))).thenReturn(new Review());
    AddReviewResponse actual = bookService.addReview(null);
  }
  @Test
  void addReview_should_throw_document_not_found() {
    when(reviewRepository.insert(any(Review.class))).thenReturn(new Review());
    AddReviewResponse actual = bookService.addReview(null);
  }

  @Test
  void delete() {
    when(bookRepository.existsById(any())).thenReturn(false);
    assertThrows(DocumentNotFoundException.class,
        () -> bookService.delete("ASDASD21"));
  }

  @Test
  void getReviews() {
  }
}
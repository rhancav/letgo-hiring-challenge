package com.example.letgohiringchallenge.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.example.letgohiringchallenge.model.converter.Converter;
import com.example.letgohiringchallenge.model.entity.Book;
import com.example.letgohiringchallenge.model.entity.Review;
import com.example.letgohiringchallenge.model.exception.DocumentNotFoundException;
import com.example.letgohiringchallenge.model.request.AddReviewRequest;
import com.example.letgohiringchallenge.model.request.RegisterBookRequest;
import com.example.letgohiringchallenge.model.request.UpdateBookRequest;
import com.example.letgohiringchallenge.model.response.AddReviewResponse;
import com.example.letgohiringchallenge.model.response.GetBooksResponse;
import com.example.letgohiringchallenge.model.response.GetReviewsResponse;
import com.example.letgohiringchallenge.model.response.RegisterBookResponse;
import com.example.letgohiringchallenge.repository.BookRepository;
import com.example.letgohiringchallenge.repository.ReviewRepository;
import com.example.letgohiringchallenge.service.impl.BookServiceImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

  static final String BOOK_ID = "E6129AB35";
  @InjectMocks
  BookServiceImpl bookService;
  @Mock
  BookRepository bookRepository;
  @Mock
  ReviewRepository reviewRepository;
  Book book;
  RegisterBookRequest registerBookRequest;
  UpdateBookRequest updateBookRequest;
  AddReviewRequest addReviewRequest;
  Review review;

  @BeforeEach
  void setUp() {
    PodamFactory podamFactory = new PodamFactoryImpl();
    registerBookRequest = podamFactory.manufacturePojo(RegisterBookRequest.class);
    updateBookRequest = podamFactory.manufacturePojo(UpdateBookRequest.class);
    addReviewRequest = podamFactory.manufacturePojo(AddReviewRequest.class);
    review = podamFactory.manufacturePojo(Review.class);
    book = podamFactory.manufacturePojo(Book.class);
  }

  @AfterEach
  void destroy() {
    registerBookRequest = null;
    updateBookRequest = null;
    addReviewRequest = null;
    review = null;
    book = null;
  }

  @Test
  void register_returns_result() {
    Book expected = Converter.convert(registerBookRequest);
    expected.setId(BOOK_ID);

    when(bookRepository.insert(any(Book.class))).thenReturn(expected);

    RegisterBookResponse actual = bookService.register(registerBookRequest);

    assertAll(
        () -> assertEquals(expected.getId(), actual.getId()),
        () -> assertEquals(expected.getPages(), actual.getPages()),
        () -> assertEquals(expected.getAuthor(), actual.getAuthor()),
        () -> assertNotNull(actual)
    );
  }

  @Test
  void update_should_throw_document_not_found() {
    when(bookRepository.findById(any())).thenReturn(Optional.empty());
    assertThrows(DocumentNotFoundException.class,
        () -> bookService.update(new UpdateBookRequest(BOOK_ID, null, null, 0, null)));
  }

  @Test
  void findAll_response_list_exact_size_1() {
    when(bookRepository.findAll()).thenReturn(List.of(book));
    GetBooksResponse actual = bookService.findAll();
    assertEquals(1, actual.getBooks().size());
  }

  @Test
  void testFindAll_response_list_exact_size_1() {
    String expected = book.getName();
    when(bookRepository.findAllByName(any())).thenReturn(List.of(book));

    GetBooksResponse actual = bookService.findAll("null");
    assertEquals(1, actual.getBooks().size());
    assertAll(
        () -> assertEquals(1, actual.getBooks().size()),
        () -> assertEquals(expected,
            actual.getBooks().get(0).getName())
    );
  }

  @Test
  void findAllByAuthor_list_exact_size_1() {
    String expected = book.getAuthor().getName();
    when(bookRepository.findAllByAuthor_Name(any())).thenReturn(List.of(book));

    GetBooksResponse actual = bookService.findAllByAuthor("null");
    assertAll(
        () -> assertEquals(1, actual.getBooks().size()),
        () -> assertEquals(expected,
            actual.getBooks().get(0).getAuthor().getName())
    );
  }

  @Test
  void addReview_returns_result() {
    Review expected = Converter.convert(addReviewRequest);
    when(reviewRepository.insert(any(Review.class))).thenReturn(expected);
    when(bookRepository.findById(any())).thenReturn(Optional.of(book));
    when(reviewRepository.findAllByBookId(any())).thenReturn(List.of(review));

    AddReviewResponse actual = bookService.addReview(addReviewRequest);

    assertAll(
        () -> assertEquals(expected.getUsername(), actual.getUsername()),
        () -> assertNotNull(actual)
    );
  }

  @Test
  void addReview_should_throw_document_not_found() {
    when(bookRepository.findById(any())).thenReturn(Optional.empty());
    assertThrows(DocumentNotFoundException.class, () -> bookService.addReview(addReviewRequest));
  }

  @Test
  void delete_throws_document_not_found() {
    when(bookRepository.existsById(any())).thenReturn(false);
    assertThrows(DocumentNotFoundException.class,
        () -> bookService.delete(BOOK_ID));
  }

  @Test
  void getReviews() {
    List<Review> expected = List.of(review);
    when(reviewRepository.findAllByBookId(BOOK_ID)).thenReturn(expected);
    GetReviewsResponse actual = bookService.getReviews(BOOK_ID);
    assertAll(
        () -> assertEquals(expected.size(), actual.getReviews().size()),
        () -> assertEquals(expected.get(0).getUsername(), actual.getReviews().get(0).getUsername())
    );
  }
}
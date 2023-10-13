package com.example.letgohiringchallenge.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "booksdb")
@TypeAlias("Book")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

  @Id
  private String id;
  private String name;
  private Author author;
  private BigDecimal rating;
  private String genre;
  private int pages;
  private LocalDateTime insertDate;
  private LocalDateTime updateDate;
}

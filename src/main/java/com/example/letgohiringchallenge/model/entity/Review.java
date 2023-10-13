package com.example.letgohiringchallenge.model.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "reviewsdb")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {

  @Id
  private String id;
  private String bookId;
  private String username;
  private String comment;
  private int rating;
  private LocalDateTime insertDate;
  private LocalDateTime updateDate;
}

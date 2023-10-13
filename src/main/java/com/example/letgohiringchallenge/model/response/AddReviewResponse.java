package com.example.letgohiringchallenge.model.response;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class AddReviewResponse extends BaseHttpResponse {

  private String reviewId;
  private String bookId;
  private String username;
  private String comment;
  private int rating;
}

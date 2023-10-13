package com.example.letgohiringchallenge.model.response;

import com.example.letgohiringchallenge.model.entity.Author;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class RegisterBookResponse extends BaseHttpResponse {

  private String id;
  private String name;
  private Author author;
  private String genre;
  private int pages;

}

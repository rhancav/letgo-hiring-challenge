package com.example.letgohiringchallenge.model.response;

import com.example.letgohiringchallenge.model.entity.Book;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetBookResponse extends BaseHttpResponse {

  private Book book;
}

package com.example.letgohiringchallenge.model.response;

import com.example.letgohiringchallenge.model.entity.Book;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetBooksResponse extends BaseHttpResponse {

  List<Book> books;
}

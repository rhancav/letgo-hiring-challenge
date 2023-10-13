package com.example.letgohiringchallenge.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.HashMap;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseHttpResponse {

  boolean success = true;
  HashMap<String, String> errors;
  String message;
}

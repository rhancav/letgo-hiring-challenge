package com.example.letgohiringchallenge.handler;

import com.example.letgohiringchallenge.model.exception.DocumentNotFoundException;
import com.example.letgohiringchallenge.model.response.BaseHttpResponse;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<BaseHttpResponse> handleException(
      MethodArgumentNotValidException exception) {
    HashMap<String, String> errors = new HashMap<>();

    BindingResult bindingResult = exception.getBindingResult();

    bindingResult.getFieldErrors().forEach((e) -> errors.put(e.getField(), e.getDefaultMessage()));

    BaseHttpResponse baseHttpResponse = new BaseHttpResponse();
    baseHttpResponse.setErrors(errors);
    baseHttpResponse.setSuccess(false);
    return new ResponseEntity<>(baseHttpResponse, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<BaseHttpResponse> handleException(
      HttpMessageNotReadableException exception) {
    BaseHttpResponse baseHttpResponse = new BaseHttpResponse();
    baseHttpResponse.setSuccess(false);
    baseHttpResponse.setMessage(exception.getMessage());
    return new ResponseEntity<>(baseHttpResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<BaseHttpResponse> handleException(
      AccessDeniedException exception) {
    BaseHttpResponse baseHttpResponse = new BaseHttpResponse();
    baseHttpResponse.setMessage(exception.getMessage());
    baseHttpResponse.setSuccess(false);
    return new ResponseEntity<>(baseHttpResponse, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(DocumentNotFoundException.class)
  public ResponseEntity<BaseHttpResponse> handleException(
      DocumentNotFoundException exception) {
    BaseHttpResponse baseHttpResponse = new BaseHttpResponse();
    baseHttpResponse.setMessage(exception.getMessage());
    baseHttpResponse.setSuccess(false);
    return new ResponseEntity<>(baseHttpResponse, HttpStatus.NOT_FOUND);
  }

}

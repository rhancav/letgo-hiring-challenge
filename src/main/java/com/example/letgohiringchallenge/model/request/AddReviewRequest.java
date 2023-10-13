package com.example.letgohiringchallenge.model.request;


import com.example.letgohiringchallenge.model.exception.constant.Errors;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record AddReviewRequest(@NotBlank(message = Errors.FIELD_CANNOT_BE_BLANK) String bookId,
                               @NotBlank(message = Errors.FIELD_CANNOT_BE_BLANK) String username,
                               @NotBlank(message = Errors.FIELD_CANNOT_BE_BLANK) String comment,
                               @Max(value = 5, message = Errors.VIOLATES_INTEGER_RANGE) int rating) {

}

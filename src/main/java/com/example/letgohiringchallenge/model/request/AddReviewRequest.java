package com.example.letgohiringchallenge.model.request;


import com.example.letgohiringchallenge.model.exception.constant.Errors;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record AddReviewRequest(@Schema(example = "GASQR235") @NotBlank(message = Errors.FIELD_CANNOT_BE_BLANK) String bookId,
                               @Schema(example = "rhancav") @NotBlank(message = Errors.FIELD_CANNOT_BE_BLANK) String username,
                               @Schema(example = "Çok iyi") @NotBlank(message = Errors.FIELD_CANNOT_BE_BLANK) String comment,
                               @Schema(example = "3") @Max(value = 5, message = Errors.VIOLATES_INTEGER_RANGE) int rating) {

}

package com.example.letgohiringchallenge.model.request;

import com.example.letgohiringchallenge.model.entity.Author;
import com.example.letgohiringchallenge.model.exception.constant.Errors;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateBookRequest(@NotBlank(message = Errors.FIELD_CANNOT_BE_BLANK) String id,
                                @NotBlank(message = Errors.FIELD_CANNOT_BE_BLANK) String name,
                                @Valid @NotNull(message = Errors.FIELD_CANNOT_BE_BLANK) Author author,
                                @Min(value = 1, message = Errors.VIOLATES_INTEGER_RANGE) int pages,
                                @NotNull(message = Errors.FIELD_CANNOT_BE_BLANK) String genre) {

}

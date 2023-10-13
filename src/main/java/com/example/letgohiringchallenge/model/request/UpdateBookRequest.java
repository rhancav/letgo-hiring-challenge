package com.example.letgohiringchallenge.model.request;

import com.example.letgohiringchallenge.model.entity.Author;
import com.example.letgohiringchallenge.model.exception.constant.Errors;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateBookRequest(@Schema(example = "GASQR235") @NotBlank(message = Errors.FIELD_CANNOT_BE_BLANK) String id,
                                @Schema(example = "Kara Kitap") @NotBlank(message = Errors.FIELD_CANNOT_BE_BLANK) String name,
                                @Valid @NotNull(message = Errors.FIELD_CANNOT_BE_BLANK) Author author,
                                @Schema(example = "3525") @Min(value = 1, message = Errors.VIOLATES_INTEGER_RANGE) int pages,
                                @Schema(example = "Roman") @NotNull(message = Errors.FIELD_CANNOT_BE_BLANK) String genre) {

}

package com.example.letgohiringchallenge.model.request;

import com.example.letgohiringchallenge.model.entity.Author;
import com.example.letgohiringchallenge.model.exception.constant.Errors;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterBookRequest(
    @Schema(example = "Kara Kitap") @NotBlank(message = Errors.FIELD_CANNOT_BE_BLANK) @JsonProperty("name") String name,
    @Valid @NotNull(message = Errors.FIELD_CANNOT_BE_BLANK) @JsonProperty("author") Author author,
    @Schema(example = "352") @Min(value = 1, message = Errors.VIOLATES_INTEGER_RANGE) @JsonProperty("pages") int pages,
    @Schema(example = "Roman") @NotBlank(message = Errors.FIELD_CANNOT_BE_BLANK) @JsonProperty("genre") String genre) {

}

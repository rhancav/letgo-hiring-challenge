package com.example.letgohiringchallenge.model.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
  @Schema(example = "Hasan Ahmet")
  @Pattern(regexp = "^[\\p{L}\\s]+$", message = "Should be alphabetic only")
  private String name;
  @NotBlank(message = "Shouldn't be blank")
  @Schema(example = "vs vs")
  private String biography;
}

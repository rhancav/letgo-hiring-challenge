package com.example.letgohiringchallenge.model.entity;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
  @Pattern(regexp = "^[\\p{L}\\s]+$", message = "Should be alphabetic only")
  private String name;
  @NotBlank(message = "Shouldn't be blank")
  private String biography;
}

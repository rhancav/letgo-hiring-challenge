package com.example.letgohiringchallenge.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document
@Data
public class Book {
    @Id
    private Long id;
    private String name;
    private Author author;
    private ArrayList<Review> reviews;
}

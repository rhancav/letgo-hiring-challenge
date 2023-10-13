package com.example.letgohiringchallenge.model.response;

import com.example.letgohiringchallenge.model.entity.Review;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class GetReviewsResponse extends BaseHttpResponse {

  private List<Review> reviews;
}

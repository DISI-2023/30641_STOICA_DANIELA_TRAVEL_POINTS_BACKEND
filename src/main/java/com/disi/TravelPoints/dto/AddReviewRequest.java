package com.disi.TravelPoints.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddReviewRequest {
    private String comment;
    private Integer rating;
    private Long landmark_id;
}

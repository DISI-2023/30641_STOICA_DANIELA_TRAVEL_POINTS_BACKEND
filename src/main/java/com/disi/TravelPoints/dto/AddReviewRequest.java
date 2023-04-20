package com.disi.TravelPoints.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddReviewRequest {
    private String comment;
    private Integer rating;
    private Integer landmark_id;
}

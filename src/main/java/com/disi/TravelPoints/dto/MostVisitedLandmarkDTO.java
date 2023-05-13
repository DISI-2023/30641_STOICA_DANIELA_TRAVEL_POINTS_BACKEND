package com.disi.TravelPoints.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MostVisitedLandmarkDTO {
    private LandmarkDetails landmarkDetails;
    private Integer visitCount;
}

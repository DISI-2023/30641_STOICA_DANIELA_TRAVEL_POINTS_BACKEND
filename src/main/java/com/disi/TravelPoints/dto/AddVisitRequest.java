package com.disi.TravelPoints.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AddVisitRequest {
    private Long landmarkId;
    private LocalDateTime date;
}

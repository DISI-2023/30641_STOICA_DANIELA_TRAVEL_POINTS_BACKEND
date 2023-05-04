package com.disi.TravelPoints.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AddVisitRequest {
    private Long landmarkId;
    private LocalDate date;
}

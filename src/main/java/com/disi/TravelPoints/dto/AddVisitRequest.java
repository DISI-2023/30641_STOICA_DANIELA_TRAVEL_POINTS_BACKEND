package com.disi.TravelPoints.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class AddVisitRequest {
    private Long landmarkId;
    private LocalDateTime date;
}

package com.disi.TravelPoints.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MonthFrequencyVisitDTO {
    private String month;
    private Integer frequency;
}

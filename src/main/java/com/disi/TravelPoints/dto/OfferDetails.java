package com.disi.TravelPoints.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class OfferDetails {
    public OfferDetails(Long id, Timestamp start, Timestamp end, Float discount, Long landmark_id) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.discount = discount;
        this.landmark_id = landmark_id;
    }

    private Long id;
    private Timestamp start;
    private Timestamp end;
    private Float discount;
    private Long landmark_id;
}

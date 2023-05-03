package com.disi.TravelPoints.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;


@Data
@Builder
public class AddOfferRequest {
    private Timestamp start;
    private Timestamp end;
    private Float discount;
    private Long landmark_id;
}

package com.disi.TravelPoints.dto;

import lombok.*;

@Data
@Builder
public class LandmarkDetails {
    private long id;
    private String name;
    private String location;
    private byte[] image;
    private String textDescription;
    private byte[] audioDescription;
    private String category;
    private float price;
}

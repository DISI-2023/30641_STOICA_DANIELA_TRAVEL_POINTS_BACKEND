package com.disi.TravelPoints.dto;

import lombok.Data;

@Data
public class AuthenticateRequest {
    private String email;
    private String password;
}

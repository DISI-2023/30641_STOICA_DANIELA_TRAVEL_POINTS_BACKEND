package com.disi.TravelPoints.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class CustomException extends Exception {
    private HttpStatus status;
    private String message;
}
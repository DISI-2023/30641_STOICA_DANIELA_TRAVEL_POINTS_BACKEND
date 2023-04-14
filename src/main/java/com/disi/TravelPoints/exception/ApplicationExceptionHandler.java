package com.disi.TravelPoints.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@ControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, Object>> handleException(CustomException exception)  {
        Map<String, Object> errorResponse = Map.of(
                "message", exception.getMessage(),
                "status", exception.getStatus().value()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
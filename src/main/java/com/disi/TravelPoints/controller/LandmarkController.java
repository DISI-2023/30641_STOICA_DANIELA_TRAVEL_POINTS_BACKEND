package com.disi.TravelPoints.controller;

import com.disi.TravelPoints.dto.LandmarkDetails;
import com.disi.TravelPoints.service.LandmarkService;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/landmark")
@RequiredArgsConstructor
public class LandmarkController {
    private final LandmarkService landmarkService;

    @GetMapping()
    public ResponseEntity<List<LandmarkDetails>> getAll() {
        return ResponseEntity.ok(landmarkService.findAll());
    }
}

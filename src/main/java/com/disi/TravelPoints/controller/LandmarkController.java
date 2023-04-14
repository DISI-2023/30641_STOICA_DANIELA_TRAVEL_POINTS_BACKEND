package com.disi.TravelPoints.controller;

import com.disi.TravelPoints.service.LandmarkService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/landmark")
@RequiredArgsConstructor
public class LandmarkController {
    private final LandmarkService landmarkService;

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        landmarkService.deleteById(id);
    }
}

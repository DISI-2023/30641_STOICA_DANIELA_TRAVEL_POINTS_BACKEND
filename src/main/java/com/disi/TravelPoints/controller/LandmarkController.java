package com.disi.TravelPoints.controller;

import com.disi.TravelPoints.dto.AddLandmarkRequest;
import com.disi.TravelPoints.exception.CustomException;
import com.disi.TravelPoints.service.LandmarkService;
import lombok.*;
import org.springframework.http.HttpStatus;
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

    @PostMapping()
    public void add(@RequestBody AddLandmarkRequest request) throws CustomException {
        try {
            landmarkService.add(request);
        } catch (RuntimeException exception){
            throw CustomException
                    .builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("Insertion Failed")
                    .build();
        }
    }
}

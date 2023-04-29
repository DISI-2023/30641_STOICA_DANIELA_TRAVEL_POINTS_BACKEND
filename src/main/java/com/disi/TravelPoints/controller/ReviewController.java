package com.disi.TravelPoints.controller;

import com.disi.TravelPoints.dto.AddLandmarkRequest;
import com.disi.TravelPoints.dto.AddReviewRequest;
import com.disi.TravelPoints.exception.CustomException;
import com.disi.TravelPoints.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/review")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")

public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping()
    public void add(@RequestBody AddReviewRequest request) throws CustomException {
        try {
            reviewService.add(request);
        } catch (RuntimeException exception){
            throw CustomException
                    .builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("Insertion Failed")
                    .build();
        }
    }

}

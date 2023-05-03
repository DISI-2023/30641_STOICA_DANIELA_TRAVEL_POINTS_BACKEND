package com.disi.TravelPoints.controller;

import com.disi.TravelPoints.dto.AddLandmarkRequest;
import com.disi.TravelPoints.dto.AddOfferRequest;
import com.disi.TravelPoints.exception.CustomException;
import com.disi.TravelPoints.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/offer")
@RequiredArgsConstructor

public class OfferController {
    private final OfferService offerService;

    @PostMapping
    public void add(@RequestBody AddOfferRequest request) throws CustomException {
        try {
            offerService.add(request);
        } catch (RuntimeException exception){
            throw CustomException
                    .builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("Insertion Failed")
                    .build();
        }
    }
}

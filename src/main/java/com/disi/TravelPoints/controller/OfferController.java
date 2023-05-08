package com.disi.TravelPoints.controller;

import com.disi.TravelPoints.dto.AddOfferRequest;
import com.disi.TravelPoints.exception.CustomException;
import com.disi.TravelPoints.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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

    @GetMapping("/emails")
    public ResponseEntity<List<String>> getUsersEmailsForActiveOffers() {
        return ResponseEntity.ok(offerService.getUsersEmailsForActiveOffers());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) throws CustomException {
        try{
            offerService.deleteById(id);
        } catch (Exception exception){
            throw CustomException
                    .builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("Deletion Failed")
                    .build();
        }
    }
}

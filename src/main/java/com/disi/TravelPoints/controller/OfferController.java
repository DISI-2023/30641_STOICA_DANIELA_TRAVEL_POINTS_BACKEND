package com.disi.TravelPoints.controller;

import com.disi.TravelPoints.dto.AddOfferRequest;
import com.disi.TravelPoints.dto.OfferDetails;
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
    public Long add(@RequestBody AddOfferRequest request) throws CustomException {
        try {
            return offerService.add(request);
        } catch (RuntimeException exception){
            throw CustomException
                    .builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("Insertion Failed")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/emails")
    public ResponseEntity<List<String>> getUsersEmailsForActiveOffers(@RequestParam Long offerId) throws CustomException {
        return ResponseEntity.ok(offerService.getUsersEmailsForActiveOffers(offerId));
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
    
    @GetMapping("/all/{landmarkId}")
    public ResponseEntity<List<OfferDetails>> getOffersByLandmarkId(@PathVariable("landmarkId") Long landmarkId) {
        return ResponseEntity.ok(offerService.getOffer(landmarkId));
    }

    @PutMapping
    public void updateOffer(@RequestBody OfferDetails offer) throws CustomException {
        try {
            offerService.updateOffer(offer);
        } catch (Exception exception) {
            throw CustomException
                    .builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("Update Failed")
                    .build();
        }
    }
}

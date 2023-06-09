package com.disi.TravelPoints.service;

import com.disi.TravelPoints.dto.AddOfferRequest;
import com.disi.TravelPoints.dto.OfferDetails;
import com.disi.TravelPoints.exception.CustomException;
import com.disi.TravelPoints.model.Landmark;
import com.disi.TravelPoints.model.Offer;
import com.disi.TravelPoints.model.Wishlist;
import com.disi.TravelPoints.repository.LandmarkRepository;
import com.disi.TravelPoints.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;
    private final LandmarkRepository landmarkRepository;

    public List<OfferDetails> findAll() {
        return offerRepository.findAll().stream().map(
                offer -> new OfferDetails(
                        offer.getId(),
                        offer.getStart(),
                        offer.getEnd(),
                        offer.getDiscount(),
                        offer.getLandmark().getId()
                )
        ).collect(Collectors.toList());
    }

    public Long add(AddOfferRequest request) throws Exception {
        Landmark landmark = landmarkRepository.findById(request.getLandmark_id())
                .orElseThrow(() -> CustomException
                        .builder()
                        .status(HttpStatus.NOT_FOUND)
                        .message("Landmark not found")
                        .build());
        List<OfferDetails> offers = findAll();
        boolean canAdd = true;
        for (OfferDetails offer : offers) {
            if (Objects.equals(offer.getLandmark_id(), request.getLandmark_id())) {
                Timestamp currentDate = new Timestamp(System.currentTimeMillis());
                if (offer.getEnd().after(currentDate)) {
                    canAdd = false;
                    break;
                }
            }
        }
        if (!canAdd) {
            throw new Exception("Failed");
        }

        Offer offer = Offer
                .builder()
                .start(request.getStart())
                .end(request.getEnd())
                .discount(request.getDiscount())
                .landmark(landmark)
                .build();
        return offerRepository.save(offer).getId();

    }

    public List<String> getUsersEmailsForActiveOffers(Long offerId) throws CustomException {
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> CustomException
                        .builder()
                        .status(HttpStatus.NOT_FOUND)
                        .message("Landmark not found")
                        .build());
        if (offer.getEnd().toLocalDateTime().isBefore(LocalDateTime.now())) {
            return List.of();
        }

        final Set<Wishlist> wishlists = offer.getLandmark().getWishlists();

        return wishlists.stream()
                .map(wishlist -> wishlist.getUser().getEmail())
                .collect(Collectors.toList());
    }

    public void updateOffer(OfferDetails request) throws Exception {
        var offer =  offerRepository.findById(request.getId())
                .orElseThrow(() -> new Exception("Offer not found"));

        Timestamp currentDate = new Timestamp(System.currentTimeMillis());

        if(request.getEnd() != null){
            if(!request.getEnd().after(currentDate)) {
                throw new Exception("The end date cannot be in the past");
            }

            if(!request.getEnd().after(offer.getStart())) {
                throw new Exception("The start date cannot be before start date");
            }
            offer.setEnd(request.getEnd());
        }
        if(request.getDiscount() != null && request.getDiscount() > 0)
        {
            offer.setDiscount(request.getDiscount());
        }

        offerRepository.save(offer);
    }
  
    public void deleteById(Long id) {
        offerRepository.deleteById(id);
    }

    public List<OfferDetails> getOffer(Long landmarkId) {
        return offerRepository
                .findAllByLandmark_Id(landmarkId)
                .stream()
                .map(this::mapOfferToOfferDetails)
                .collect(Collectors.toList());
    }

    private OfferDetails mapOfferToOfferDetails(Offer offer) {
        return OfferDetails
                .builder()
                .id(offer.getId())
                .start(offer.getStart())
                .end(offer.getEnd())
                .discount(offer.getDiscount())
                .landmark_id(offer.getLandmark().getId())
                .build();
    }
}

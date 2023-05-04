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

    public void add(AddOfferRequest request) throws CustomException {
        Landmark landmark = landmarkRepository.findById(request.getLandmark_id())
                .orElseThrow(() -> CustomException
                        .builder()
                        .status(HttpStatus.NOT_FOUND)
                        .message("Landmark not found")
                        .build());
        List<OfferDetails> offers = findAll();
        boolean canAdd = true;
        for (OfferDetails offer : offers) {
            if (offer.getLandmark_id() == request.getLandmark_id()) {
                Timestamp currentDate = new Timestamp(System.currentTimeMillis());
                if (offer.getEnd().after(currentDate)){
                    canAdd = false;
                    break;
                }
            }
        }

        if (canAdd) {
            Offer offer = Offer
                    .builder()
                    .start(request.getStart())
                    .end(request.getEnd())
                    .discount(request.getDiscount())
                    .landmark(landmark)
                    .build();
            offerRepository.save(offer);
        }
    }

    public List<String> getUsersEmailsForActiveOffers() {
        final List<Offer> activeOffers = offerRepository.findAll().stream()
                .filter(offer -> offer.getEnd().toLocalDateTime().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());

        final List<Wishlist> wishlists = activeOffers.stream()
                .map(activeOffer -> activeOffer.getLandmark().getWishlists())
                .flatMap(Set::stream)
                .collect(Collectors.toList());

        return wishlists.stream()
                .map(wishlist -> wishlist.getUser().getEmail())
                .collect(Collectors.toList());
    }
}

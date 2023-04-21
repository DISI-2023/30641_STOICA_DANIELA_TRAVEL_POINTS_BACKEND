package com.disi.TravelPoints.service;

import com.disi.TravelPoints.dto.LandmarkDetails;
import com.disi.TravelPoints.dto.UserDetails;
import com.disi.TravelPoints.exception.CustomException;
import com.disi.TravelPoints.model.Landmark;
import com.disi.TravelPoints.model.Wishlist;
import com.disi.TravelPoints.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistService {
    private final WishlistRepository wishlistRepository;

    public List<LandmarkDetails> getWishlistForUser(Long id) throws Exception {
        Wishlist wishlist = wishlistRepository.findByUser_Id(id)
                .orElseThrow(() -> new Exception("Wishlist not found!"));

        return wishlist.getLandmarks().stream()
                .map(this::mapLandmarkToLandmarkDetails)
                .collect(Collectors.toList());
    }

    private LandmarkDetails mapLandmarkToLandmarkDetails(Landmark landmark) {
        return LandmarkDetails
                .builder()
                .id(landmark.getId())
                .name(landmark.getName())
                .location(landmark.getLocation())
                .image(landmark.getImage())
                .textDescription(landmark.getTextDescription())
                .audioDescription(landmark.getAudioDescription())
                .price(landmark.getPrice())
                .category(landmark.getCategory())
                .build();
    }
}

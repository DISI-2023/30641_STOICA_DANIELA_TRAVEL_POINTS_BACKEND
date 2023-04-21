package com.disi.TravelPoints.service;

import com.disi.TravelPoints.dto.LandmarkDetails;
import com.disi.TravelPoints.dto.UserDetails;
import com.disi.TravelPoints.exception.CustomException;
import com.disi.TravelPoints.model.Landmark;
import com.disi.TravelPoints.model.User;
import com.disi.TravelPoints.model.Wishlist;
import com.disi.TravelPoints.repository.LandmarkRepository;
import com.disi.TravelPoints.repository.UserRepository;
import com.disi.TravelPoints.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final LandmarkRepository landmarkRepository;

    public List<LandmarkDetails> getWishlistForUser(Long id) throws Exception {
        Wishlist wishlist = wishlistRepository.findByUser_Id(id)
                .orElseThrow(() -> new Exception("Wishlist not found!"));

        return wishlist.getLandmarks().stream()
                .map(this::mapLandmarkToLandmarkDetails)
                .collect(Collectors.toList());
    }

    public Long addLandmarkInWishlist(Long landmarkId, Long userId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found!"));
        Landmark landmark = landmarkRepository.findById(landmarkId)
                .orElseThrow(() -> new Exception("Landmark not found!"));

        Optional<Wishlist> wishlist = wishlistRepository.findByUser_Id(userId);
        if (wishlist.isEmpty()) {
            Wishlist newWishlist = new Wishlist();
            newWishlist.setUser(user);
            newWishlist.setLandmarks(Set.of(landmark));
            return wishlistRepository.save(newWishlist).getId();
        }

        Wishlist actualWishlist = wishlist.get();
        final Set<Landmark> landmarks = actualWishlist.getLandmarks();
        landmarks.add(landmark);
        actualWishlist.setLandmarks(landmarks);
        return actualWishlist.getId();
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

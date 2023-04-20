package com.disi.TravelPoints.controller;

import com.disi.TravelPoints.dto.LandmarkDetails;
import com.disi.TravelPoints.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/wishlist")
@RequiredArgsConstructor
public class WishlistController {
    private final WishlistService wishlistService;

    @GetMapping("/{id}")
    public ResponseEntity<List<LandmarkDetails>> getWishlistForUser(@PathVariable Long id) {
        return ResponseEntity.ok(wishlistService.getWishlistForUser(id));
    }
}

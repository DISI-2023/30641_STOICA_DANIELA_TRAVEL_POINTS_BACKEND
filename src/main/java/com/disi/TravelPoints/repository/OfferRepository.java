package com.disi.TravelPoints.repository;

import com.disi.TravelPoints.model.Offer;
import com.disi.TravelPoints.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
}

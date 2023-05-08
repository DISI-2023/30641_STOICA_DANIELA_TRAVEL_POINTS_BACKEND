package com.disi.TravelPoints.repository;

import com.disi.TravelPoints.model.Offer;
import com.disi.TravelPoints.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findAllByLandmark_Id(Long id);
}

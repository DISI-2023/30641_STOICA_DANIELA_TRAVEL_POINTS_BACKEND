package com.disi.TravelPoints.repository;

import com.disi.TravelPoints.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Wishlist findByUser_Id(Long id);
}

package com.disi.TravelPoints.repository;

import com.disi.TravelPoints.model.Landmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LandmarkRepository extends JpaRepository<Landmark, Long> {
    @Query(value = "SELECT l.text_description FROM landmarks l WHERE l.id = :id", nativeQuery = true)
    String getDescriptionById(@Param("id") Long id);

    @Query(value = "SELECT l.audio_description FROM landmarks l WHERE l.id = :id", nativeQuery = true)
    byte[] getAudioDescriptionById(@Param("id") Long id);
}

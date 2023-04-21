package com.disi.TravelPoints.service;

import com.disi.TravelPoints.dto.AddLandmarkRequest;
import com.disi.TravelPoints.dto.LandmarkDetails;
import com.disi.TravelPoints.model.Landmark;
import com.disi.TravelPoints.repository.LandmarkJdbcRepository;
import com.disi.TravelPoints.repository.LandmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LandmarkService {
    private final LandmarkRepository landmarkRepository;
    private final LandmarkJdbcRepository landmarkJdbcRepository;

    public void deleteById(long id) {
        landmarkRepository.deleteById(id);
    }

    public List<LandmarkDetails> findAll(String category, String location) {
        return landmarkJdbcRepository.getLandmarksByCategoryAndLocation(category, location);
    }

    public void add(AddLandmarkRequest request) {
        Landmark landmark = Landmark
                .builder()
                .name(request.getName())
                .location(request.getLocation())
                .image(request.getImage())
                .textDescription(request.getTextDescription())
                .audioDescription(request.getAudioDescription())
                .price(request.getPrice())
                .category(request.getCategory())
                .build();

        landmarkRepository.save(landmark);
    }

    public String getLandmarkDescriptionById(Long id) {
        return landmarkRepository.getDescriptionById(id);
    }

    public byte[] getLandmarkAudioDescriptionById(Long id) {
        return landmarkRepository.getAudioDescriptionById(id);
    }
}

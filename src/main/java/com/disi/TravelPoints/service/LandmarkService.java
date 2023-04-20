package com.disi.TravelPoints.service;

import com.disi.TravelPoints.dto.AddLandmarkRequest;
import com.disi.TravelPoints.model.Landmark;
import com.disi.TravelPoints.repository.LandmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LandmarkService {
    private final LandmarkRepository landmarkRepository;

    public void add(AddLandmarkRequest request){
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
}

package com.disi.TravelPoints.service;

import com.disi.TravelPoints.dto.LandmarkDetails;
import com.disi.TravelPoints.repository.LandmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LandmarkService {
    private final LandmarkRepository landmarkRepository;

    public List<LandmarkDetails> findAll(){
        return landmarkRepository
                .findAll()
                .stream()
                .map(landmark -> LandmarkDetails
                        .builder()
                        .id(landmark.getId())
                        .name(landmark.getName())
                        .location(landmark.getLocation())
                        .image(landmark.getImage())
                        .textDescription(landmark.getTextDescription())
                        .audioDescription(landmark.getAudioDescription())
                        .category(landmark.getCategory())
                        .price(landmark.getPrice())
                        .build()
                )
                .toList();
    }
}

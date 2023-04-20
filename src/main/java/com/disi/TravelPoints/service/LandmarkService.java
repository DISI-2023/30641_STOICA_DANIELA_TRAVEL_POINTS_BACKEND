package com.disi.TravelPoints.service;

import com.disi.TravelPoints.dto.LandmarkDetails;
import com.disi.TravelPoints.dto.AddLandmarkRequest;
import com.disi.TravelPoints.model.Landmark;
import com.disi.TravelPoints.repository.LandmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LandmarkService {
    private final LandmarkRepository landmarkRepository;

    public void deleteById(long id) {
        landmarkRepository.deleteById(id);
    }
    
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
}

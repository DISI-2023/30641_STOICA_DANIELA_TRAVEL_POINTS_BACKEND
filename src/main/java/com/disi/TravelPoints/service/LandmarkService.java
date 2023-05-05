package com.disi.TravelPoints.service;

import com.disi.TravelPoints.dto.AddLandmarkRequest;
import com.disi.TravelPoints.dto.LandmarkDetails;
import com.disi.TravelPoints.exception.CustomException;
import com.disi.TravelPoints.model.Landmark;
import com.disi.TravelPoints.repository.LandmarkJdbcRepository;
import com.disi.TravelPoints.repository.LandmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public Long update(LandmarkDetails request) throws CustomException {
        Landmark landmark = landmarkRepository.findById(request.getId())
                .orElseThrow(() -> CustomException
                        .builder()
                        .status(HttpStatus.NOT_FOUND)
                        .message("Landmark not found")
                        .build());

        landmark.setCategory(request.getCategory());
        landmark.setAudioDescription(request.getAudioDescription());
        landmark.setImage(request.getImage());
        landmark.setLocation(request.getLocation());
        landmark.setName(request.getName());
        landmark.setTextDescription(request.getTextDescription());
        landmark.setPrice(request.getPrice());
        landmarkRepository.save(landmark);

        return landmark.getId();
    }

    public String getLandmarkDescriptionById(Long id) {
        return landmarkRepository.getDescriptionById(id);
    }

    public byte[] getLandmarkAudioDescriptionById(Long id) {
        return landmarkRepository.getAudioDescriptionById(id);
    }

    public List<LandmarkDetails> getFirstFiveMostVisitedLandmarks() {
        return landmarkRepository.getFirstFiveMostVisitedLandmarks().stream()
                .map(this::mapLandmarkToLandmarkDetails)
                .collect(Collectors.toList());
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

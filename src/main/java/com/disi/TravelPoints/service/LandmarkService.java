package com.disi.TravelPoints.service;

import com.disi.TravelPoints.repository.LandmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LandmarkService {
    private final LandmarkRepository landmarkRepository;

    public void deleteById(long id) {
        landmarkRepository.deleteById(id);
    }
}

package com.disi.TravelPoints.service;

import com.disi.TravelPoints.model.Landmark;
import com.disi.TravelPoints.repository.LandmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LandmarkService {
    private final LandmarkRepository landmarkRepository;

    public List<Landmark> findAll(){
        return landmarkRepository.findAll();
    }
}

package com.disi.TravelPoints.service;

import com.disi.TravelPoints.dto.AddReviewRequest;
import com.disi.TravelPoints.dto.AddVisitRequest;
import com.disi.TravelPoints.exception.CustomException;
import com.disi.TravelPoints.model.Landmark;
import com.disi.TravelPoints.model.Review;
import com.disi.TravelPoints.model.Visit;
import com.disi.TravelPoints.repository.LandmarkRepository;
import com.disi.TravelPoints.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitService {
    private final VisitRepository visitRepository;
    private final LandmarkRepository landmarkRepository;

    public Long addVisit(AddVisitRequest request) throws CustomException {
        Landmark landmark = landmarkRepository.findById(request.getLandmarkId())
                .orElseThrow(() -> CustomException
                        .builder()
                        .status(HttpStatus.NOT_FOUND)
                        .message("Landmark not found")
                        .build());

        Visit visit = Visit
                .builder()
                .date(request.getDate())
                .landmark(landmark)
                .build();

        return visitRepository.save(visit).getId();
    }
}
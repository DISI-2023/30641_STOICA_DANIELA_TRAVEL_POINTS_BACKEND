package com.disi.TravelPoints.service;

import com.disi.TravelPoints.dto.AddReviewRequest;
import com.disi.TravelPoints.dto.AddVisitRequest;
import com.disi.TravelPoints.exception.CustomException;
import com.disi.TravelPoints.model.Landmark;
import com.disi.TravelPoints.model.Review;
import com.disi.TravelPoints.repository.LandmarkRepository;
import com.disi.TravelPoints.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final LandmarkRepository landmarkRepository;
    private final VisitService visitService;

    public void add(AddReviewRequest request) throws CustomException {
        Landmark landmark = landmarkRepository.findById(request.getLandmark_id())
                .orElseThrow(() -> CustomException
                        .builder()
                        .status(HttpStatus.NOT_FOUND)
                        .message("Landmark not found")
                        .build());

        Review review = Review
                .builder()
                .comment(request.getComment())
                .rating(request.getRating())
                .landmark(landmark)
                .build();

        reviewRepository.save(review);
        visitService.addVisit(new AddVisitRequest(landmark.getId(), LocalDateTime.now()));
    }

}

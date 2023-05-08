package com.disi.TravelPoints.service;

import com.disi.TravelPoints.dto.AddVisitRequest;
import com.disi.TravelPoints.dto.HourFrequencyVisitDTO;
import com.disi.TravelPoints.dto.MonthFrequencyVisitDTO;
import com.disi.TravelPoints.exception.CustomException;
import com.disi.TravelPoints.model.Landmark;
import com.disi.TravelPoints.model.Visit;
import com.disi.TravelPoints.repository.LandmarkRepository;
import com.disi.TravelPoints.repository.VisitJdbcRepository;
import com.disi.TravelPoints.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitService {
    private final VisitRepository visitRepository;
    private final VisitJdbcRepository visitJdbcRepository;
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

    public List<MonthFrequencyVisitDTO> getMonthFrequencyPerYear(String year, Long landmarkId) {
        return visitJdbcRepository.getMonthsFrequencyPerYear(year, landmarkId);
    }

    public List<HourFrequencyVisitDTO> getHourFrequencyPerDay(String year, String month, String day, long landmarkId) {
        return visitJdbcRepository.getHourFrequencyPerDay(year, month, day, landmarkId);
    }
}

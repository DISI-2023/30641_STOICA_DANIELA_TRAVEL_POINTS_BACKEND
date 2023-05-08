package com.disi.TravelPoints.controller;

import com.disi.TravelPoints.dto.AddVisitRequest;
import com.disi.TravelPoints.dto.HourFrequencyVisitDTO;
import com.disi.TravelPoints.dto.MonthFrequencyVisitDTO;
import com.disi.TravelPoints.exception.CustomException;
import com.disi.TravelPoints.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/visit")
@RequiredArgsConstructor
public class VisitController {
    private final VisitService visitService;

    @PostMapping
    public ResponseEntity<Long> addVisit(@RequestBody AddVisitRequest request) throws CustomException {
        try {
            return new ResponseEntity<>(visitService.addVisit(request), HttpStatus.CREATED);
        } catch (Exception exception) {
            throw CustomException
                    .builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message(exception.getMessage())
                    .build();
        }
    }

    @GetMapping("/day-frequency")
    public ResponseEntity<List<HourFrequencyVisitDTO>> geHourFrequencyPerDay(
            @RequestParam String year,
            @RequestParam String month,
            @RequestParam String day,
            @RequestParam Long landmarkId
    ) {
        return ResponseEntity.ok(visitService.getHourFrequencyPerDay(year, month, day, landmarkId));
    }

    @GetMapping("/year-frequency")
    public ResponseEntity<List<MonthFrequencyVisitDTO>> getMonthFrequencyPerYear(@RequestParam(defaultValue = "2023") String year, @RequestParam Long landmarkId) {
        return ResponseEntity.ok(visitService.getMonthFrequencyPerYear(year, landmarkId));
    }
}

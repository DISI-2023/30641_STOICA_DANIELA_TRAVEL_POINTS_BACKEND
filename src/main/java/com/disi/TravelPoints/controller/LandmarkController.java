package com.disi.TravelPoints.controller;


import com.disi.TravelPoints.dto.LandmarkDetails;
import com.disi.TravelPoints.dto.AddLandmarkRequest;
import com.disi.TravelPoints.exception.CustomException;
import com.disi.TravelPoints.service.LandmarkService;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/landmark")
@RequiredArgsConstructor

public class LandmarkController {
    private final LandmarkService landmarkService;

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        landmarkService.deleteById(id);
    }

    @GetMapping
    public ResponseEntity<List<LandmarkDetails>> getAll(@RequestParam(required = false) String category, @RequestParam(required = false) String location) {
        return ResponseEntity.ok(landmarkService.findAll(category, location));
    }

    @PostMapping
    public void add(@RequestBody AddLandmarkRequest request) throws CustomException {
        try {
            landmarkService.add(request);
        } catch (RuntimeException exception){
            throw CustomException
                    .builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("Insertion Failed")
                    .build();
        }
    }
    @PutMapping()
        public ResponseEntity<Long> update(@RequestBody LandmarkDetails request) throws CustomException {
        try {
            return ResponseEntity.ok(landmarkService.update(request));
        } catch (RuntimeException exception){
            throw CustomException
                    .builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("Update Failed")
                    .build();
        }
    }
    
    @GetMapping("/{id}/description")
    public String getLandmarkDescriptionById(@PathVariable Long id) {
        return landmarkService.getLandmarkDescriptionById(id);
    }

    @GetMapping("/{id}/audio-description")
    public byte[] getLandmarkAudioDescriptionById(@PathVariable Long id) {
        return landmarkService.getLandmarkAudioDescriptionById(id);
    }
}

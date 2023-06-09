package com.disi.TravelPoints.controller;

import com.disi.TravelPoints.dto.AuthenticateRequest;
import com.disi.TravelPoints.dto.RegisterRequest;
import com.disi.TravelPoints.dto.UserDetails;
import com.disi.TravelPoints.exception.CustomException;
import com.disi.TravelPoints.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")

public class UserController {
    private final UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<UserDetails> authenticate(@RequestBody AuthenticateRequest request) throws CustomException {
        try {
            var user = userService.authenticate(request);
            return ResponseEntity.ok(user);
        } catch (Exception exception) {
            throw CustomException
                    .builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message(exception.getMessage())
                    .build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody RegisterRequest request) throws CustomException {
        try {
            return new ResponseEntity<>(userService.register(request), HttpStatus.CREATED);
        } catch (Exception exception) {
            throw CustomException
                    .builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(exception.getMessage())
                    .build();
        }
    }
}

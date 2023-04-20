package com.disi.TravelPoints.service;

import com.disi.TravelPoints.dto.AuthenticateRequest;
import com.disi.TravelPoints.dto.UserDetails;
import com.disi.TravelPoints.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDetails authenticate(AuthenticateRequest request) throws Exception {
        var user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new Exception("Wrong email or password"));

        var passwordsMatch = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!passwordsMatch){
            throw new Exception("Wrong email or password");
        }

        return UserDetails
                .builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().getName())
                .build();
    }
}

package com.disi.TravelPoints.controller;

import com.disi.TravelPoints.Email.Email;
import com.disi.TravelPoints.service.EmailService;
import lombok.*;
import org.springframework.web.bind.annotation.*;
import javax.mail.*;

@RestController
@RequestMapping("api/v1/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;
    @PostMapping
    public void sendEmail(@RequestBody Email message) throws MessagingException {
        emailService.sendEmail(message);
    }
}


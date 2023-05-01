package com.disi.TravelPoints.controller;



import com.disi.TravelPoints.Email.Email;
import com.disi.TravelPoints.dto.LandmarkDetails;
import com.disi.TravelPoints.dto.AddLandmarkRequest;
import com.disi.TravelPoints.exception.CustomException;
import com.disi.TravelPoints.service.LandmarkService;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@RestController
@RequestMapping("api/v1/email")
@RequiredArgsConstructor
public class EmailController {

    @PostMapping
    public void sendEmail(@RequestBody Email message) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("tatar.andreea23@gmail.com", "cqqfxoiutrladjcy");
            }
        });

        Message emailMessage = new MimeMessage(session);
        emailMessage.setFrom(new InternetAddress(message.getFrom()));
        emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(message.getTo()));
        emailMessage.setSubject(message.getSubject());
        emailMessage.setText(message.getBody());

        Transport.send(emailMessage);
    }
}


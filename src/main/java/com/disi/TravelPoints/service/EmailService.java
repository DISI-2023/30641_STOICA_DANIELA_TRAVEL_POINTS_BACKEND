package com.disi.TravelPoints.service;

import com.disi.TravelPoints.Email.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class EmailService {
    public boolean sendEmail(Email message) throws MessagingException {
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
        return true;
    }
}

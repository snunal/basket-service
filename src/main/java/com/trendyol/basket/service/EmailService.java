package com.trendyol.basket.service;

import com.trendyol.basket.model.EmailMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailService {

    @Value("${constants.noreply-email}")
    private static String NOREPLY_EMAIL_ADDRESS;

    private final JavaMailSender mailSender;

    public void sendEmail(EmailMessage emailMessage, String emailAddress) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(NOREPLY_EMAIL_ADDRESS);
        message.setTo(emailAddress);
        message.setSubject(emailMessage.getSubject());
        message.setText(emailMessage.getEmailBodyText());
        mailSender.send(message);
    }

}

package edu.usdb.cs.karousti.emailing;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class EmailingService {

    private final JavaMailSender javaMailSender;

    public EmailingService(JavaMailSenderImpl mailSender) {
        this.javaMailSender = mailSender;
    }

    public void sendConfirmationEmail() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String customerEmail = authentication.getName();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(customerEmail);
        mailMessage.setSubject("Order Confirmation");
        mailMessage.setText("Your order has been confirmed!");
        javaMailSender.createMimeMessage();

    }

}

package com.example.email_sender_app.controllers;

import com.example.email_sender_app.Models.EmailRequest;
import com.example.email_sender_app.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emails")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        emailService.sendEmail(emailRequest);
        return ResponseEntity.ok("Email sent successfully to " + String.join(", ", emailRequest.getRecipients()));
    }

    @PostMapping("/send-multiple")
    public ResponseEntity<String> sendEmails(@RequestBody EmailRequest emailRequest) {
        emailService.sendEmail(emailRequest); // Utilizing the updated sendEmail method that handles multiple recipients
        return ResponseEntity.ok("Emails sent successfully to all recipients.");
    }
}

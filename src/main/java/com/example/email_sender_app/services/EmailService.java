package com.example.email_sender_app.services;

import com.example.email_sender_app.Entities.Email;
import com.example.email_sender_app.Models.EmailRequest;

import java.util.List;

public interface EmailService {
    void sendEmail(EmailRequest emailRequest);
    String createEmailBody(String recruiterName, String recipient);
    void sendEmailsSeparately(List<String> recipients, String subject);
    public List<Email> getAllEmails();
    
    
}

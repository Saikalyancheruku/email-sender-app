package com.example.email_sender_app.schedulers;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.hibernate.boot.jaxb.JaxbLogger_.logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.email_sender_app.Entities.Email;
import com.example.email_sender_app.Models.EmailRequest;
import com.example.email_sender_app.services.EmailService;
import com.example.email_sender_app.services.EmailUtiltesService;

public class EmailScheduler {
	 private final EmailService emailService;
	 private final Logger logger = LoggerFactory.getLogger(EmailScheduler.class);

	   @Autowired
	   public EmailScheduler(EmailService emailService) {
	       this.emailService = emailService;
	   }

	   @Scheduled(cron = "0 0 9 * * ?") 
	   public void sendDailyEmails() {
	        try {
	            List<Email> emailsToSend = emailService.getAllEmails(); // Fetch all emails

	            for (Email email : emailsToSend) {
	                // Create EmailRequest based on your Email entity if needed
	            	String subject = "Innovative Solutions, Seamless Code – Software Engineer at Your Service"; // Modify this to get the desired subject
	                String body = emailService.createEmailBody(EmailUtiltesService.extractNameFromEmail(email.getRecipient()), email.getRecipient()); // Modify this accordingly

	            	EmailRequest emailRequest = new EmailRequest();
	                emailRequest.setRecipients(List.of(email.getRecipient())); // Assuming you have a method to get recipients
	                
	                // Send the email using the existing sendEmail method
	                emailService.sendEmail(emailRequest);
	                logger.info("Email sent to: " + email.getRecipient());
	            }
	        } catch (Exception e) {
	            logger.error("Error sending daily emails: " + e.getMessage(), e);
	        }
	    }
}

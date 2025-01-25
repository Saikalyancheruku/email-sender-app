package com.example.email_sender_app.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.email_sender_app.Entities.Email;
import com.example.email_sender_app.Models.EmailRequest;
import com.example.email_sender_app.Repositories.EmailRepository;

import jakarta.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    private final EmailRepository emailRepository;
    // Specify your resume source folder path
    private final String resumeSourceFolder = "D:/RecruitMailer/email-sender-app/src/main/resources/attachments/";

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender,EmailRepository emailRepository) {
       
        this.mailSender = mailSender;
        this.emailRepository = emailRepository;
    }
    
    @Override
    public void sendEmail(EmailRequest emailRequest) {
        // Validate input
        List<String> recipients = emailRequest.getRecipients();
        String subject ="Innovative Solutions, Seamless Code – Software Engineer at Your Service";
        
        // Send email with attachment to each recipient
        sendEmailsSeparately(recipients, subject);
    }
    public void saveEmail(Email email) {
        emailRepository.save(email);
    }
    @Override
    public List<Email> getAllEmails() {
        return emailRepository.findAll();
    }
    public void sendEmailsSeparately(List<String> recipients, String subject) {
        String[] recruiterNames = EmailUtiltesService.extractNamesFromEmails(recipients);
        
        for (int i = 0; i < recipients.size(); i++) {
            String recipient = recipients.get(i);
            String body = createEmailBody(recruiterNames[i], recipient);

            // Call the method to send the email to each recipient
            sendEmailWithAttachment(recipient, subject, body, "KalyanResume.pdf"); // Specify the file name of the resume
        }
    }

    @Override
    public String createEmailBody(String recruiterName, String recipient) {
        // Create a personalized email body
        return String.format(
            "Hi %s,\n\n" +
            "I hope this message finds you well. I am reaching out to express my interest in opportunities that align with my background as a Software Engineer. " +
            "With over 1.5 years of experience, I have developed a diverse technical skill set that includes Spring, Spring Boot, Angular, backend services, SQL development, " +
            "and database management. My expertise also spans frontend technologies such as JavaScript, TypeScript, HTML5, and CSS3, which enables me to deliver robust, full-stack solutions.\n\n" +
            "I have consistently demonstrated the ability to write high-quality, efficient code while adhering to tight deadlines and collaborating effectively with cross-functional teams. " +
            "My experience working with Oracle SQL, MySQL, MongoDB, and REST APIs has equipped me with the technical knowledge required to handle complex backend challenges, while also ensuring the scalability and performance of applications.\n\n" +
            "In addition to my technical capabilities, I have a strong passion for continuous learning and professional development. I stay updated with the latest trends in technology to ensure I am delivering innovative solutions in this rapidly evolving field.\n\n" +
            "I would be honored if you could consider my resume for any current or future opportunities that require a candidate with my skills and experience. " +
            "I am confident that my technical abilities, coupled with my commitment to improvement, would make me a valuable addition to your team.\n\n" +
            "Thank you for your time and consideration. I look forward to the possibility of contributing to your organization.\n\n" +
            "Best regards,\n" +
            "Cheruku Sai Kalyan",
            recruiterName
        );
    }

    private void sendEmailWithAttachment(String recipient, String subject, String body, String attachmentFileName) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true); // Set true for multipart

            helper.setTo(recipient);
            helper.setSubject(subject);
            helper.setText(body);

            // Specify the path to the attachment
            File file = new File(resumeSourceFolder + attachmentFileName);
            logger.info("Attempting to attach file: " + file.getAbsolutePath());
            if (file.exists()) {
                helper.addAttachment(attachmentFileName, file);
                logger.info("Attachment added successfully: " + attachmentFileName);
            } else {
                logger.error("Attachment file not found: " + file.getAbsolutePath());
                return; // Skip sending the email if the file is not found
            }

            mailSender.send(message);
            logger.info("Email sent successfully to: " + recipient);
        } catch (Exception e) {
            logger.error("Failed to send email to " + recipient, e);
        }
    }

	
}

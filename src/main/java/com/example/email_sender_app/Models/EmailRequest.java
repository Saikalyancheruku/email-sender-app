package com.example.email_sender_app.Models;

import java.util.List;

public class EmailRequest {
   // private String recipient;
	 private List<String> recipients;
	private String subject;
    private String body;

    // Getters and Setters
    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

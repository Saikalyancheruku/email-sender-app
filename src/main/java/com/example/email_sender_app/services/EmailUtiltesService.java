package com.example.email_sender_app.services;

import java.util.List;

public class EmailUtiltesService {
    public static String extractNameFromEmail(String recipient) {
        // Assuming the email format is "firstname.lastname@example.com"
        String namePart = recipient.split("@")[0]; // Get the part before '@'
        String[] nameParts = namePart.split("\\."); // Split by '.'
        if (nameParts.length >= 2) {
            return capitalize(nameParts[0]) + " " + capitalize(nameParts[1]); // Capitalize first and last name
        }
        return capitalize(namePart); // Fallback to just the part before '@'
    }

    public static String[] extractNamesFromEmails(List<String> recipients) {
        return recipients.stream()
                .map(EmailUtiltesService::extractNameFromEmail)
                .toArray(String[]::new); // Convert to array for easier handling
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
}

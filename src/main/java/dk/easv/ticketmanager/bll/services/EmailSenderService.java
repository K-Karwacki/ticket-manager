package dk.easv.ticketmanager.bll.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import dk.easv.ticketmanager.utils.Env;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;

public class EmailSenderService {

    private static final String API_KEY = Env.get("API_KEY");
    private static final String FROM_EMAIL = Env.get("FROM_EMAIL");
    private static final String API_URL = "https://api.sendgrid.com/v3/mail/send";

    // Public method to send email with attachment
    public void sendTicket(String recipient, File file, String subject) throws IOException {
        String encodedFile = Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath()));
        JSONObject attachment = new JSONObject()
                .put("content", encodedFile)
                .put("filename", file.getName())
                .put("type", Files.probeContentType(file.toPath()));

        sendEmail(recipient, subject, "Please find the attached file.", attachment);
    }

    // Private method with shared email-sending logic
    public void sendEmail(String recipient, String subject, String message, JSONObject attachment) throws IOException {
        JSONObject emailJson = new JSONObject();
        emailJson.put("from", new JSONObject().put("email", FROM_EMAIL));
        emailJson.put("subject", subject);

        JSONObject to = new JSONObject().put("email", recipient);
        emailJson.append("personalizations", new JSONObject().append("to", to));

        emailJson.append("content", new JSONObject()
                .put("type", "text/plain")
                .put("value", message));

        if (attachment != null) {
            emailJson.append("attachments", attachment);
        }

        HttpURLConnection connection = (HttpURLConnection) new URL(API_URL).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            os.write(emailJson.toString().getBytes());
            os.flush();
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == 202) {
            System.out.println("Email sent successfully!");
        } else {
            System.out.println("Failed to send email. Response code: " + responseCode);
        }
    }
}
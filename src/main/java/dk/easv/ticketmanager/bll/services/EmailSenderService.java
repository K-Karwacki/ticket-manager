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


public class EmailSender {

    private static final String API_KEY =  Env.get("API_KEY");
    private static final String FROM_EMAIL = Env.get("FROM_EMAIL");

    public void sendEmail(String recipient, File file, String subject) throws IOException {
        String url = "https://api.sendgrid.com/v3/mail/send";

        byte[] fileContent = Files.readAllBytes(file.toPath());
        String encodedFile = Base64.getEncoder().encodeToString(fileContent);

        JSONObject emailJson = new JSONObject();
        emailJson.put("from", new JSONObject().put("email", FROM_EMAIL));
        emailJson.put("subject", subject);

        JSONObject to = new JSONObject();
        to.put("email", recipient);
        emailJson.append("personalizations", new JSONObject().append("to", to));

        emailJson.append("content", new JSONObject()
                .put("type", "text/plain")
                .put("value", "Please find the attached file."));

        JSONObject attachment = new JSONObject();
        attachment.put("content", encodedFile);
        attachment.put("filename", file.getName());
        attachment.put("type", Files.probeContentType(file.toPath()));
        emailJson.append("attachments", attachment);

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
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

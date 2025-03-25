package dk.easv.ticketmanager.bll;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;

public class HubSpotEmailSender {

    private static final String API_KEY = "eu1-2522-5b14-433f-af67-f164d6dccca2"; // Replace with your HubSpot API key

    /**
     * Logs an email engagement in HubSpot using the Engagements API with an API key.
     * Note: This logs the email in HubSpot but may not send it unless tied to a workflow.
     * @param toEmail The recipient's email address.
     * @param subject The email subject.
     * @param body The email body (HTML or plain text).
     * @throws IOException If the API request fails.
     */
    public static void sendEmail(String toEmail, String subject, String body) throws IOException {
        OkHttpClient client = new OkHttpClient();

        // Build the engagement JSON payload
        JSONObject payload = new JSONObject();
        JSONObject engagement = new JSONObject();
        engagement.put("type", "EMAIL");
        engagement.put("timestamp", System.currentTimeMillis());
        payload.put("engagement", engagement);

        JSONObject metadata = new JSONObject();
        metadata.put("fromEmail", "sender@example.com"); // Replace with your sender email
        metadata.put("subject", subject);
        metadata.put("html", body); // Use "text" for plain text instead of HTML

        JSONArray toArray = new JSONArray();
        JSONObject recipient = new JSONObject();
        recipient.put("email", toEmail);
        toArray.put(recipient);
        metadata.put("to", toArray);

        payload.put("metadata", metadata);

        // Build the request
        RequestBody requestBody = RequestBody.create(payload.toString(), MediaType.parse("application/json"));
        String url = "https://api.hubapi.com/engagements/v1/engagements?hapikey=" + API_KEY;
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .header("Content-Type", "application/json")
                .build();

        // Execute the request
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Failed to create email engagement: " + response.code() + " - " + response.body().string());
            }
        }
    }

    // Example usage
    public static void main(String[] args) {
        try {
            sendEmail("pawelchrostek22@gmail.com", "Test Subject", "<h1>Hello!</h1><p>This is a test email.</p>");
            System.out.println("Email engagement created successfully!");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
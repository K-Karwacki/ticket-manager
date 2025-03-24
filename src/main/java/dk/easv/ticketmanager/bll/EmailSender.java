package dk.easv.ticketmanager.bll;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.File;
import java.util.Properties;

public class EmailSender {
    public static void sendEmail(String recipient, String subject, File attachment) {
        final String senderEmail = "ticketmanagerpaki@gmail.com";
        final String senderPassword = "neua mutj wypy jsbu";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);

            // Email body
            MimeBodyPart textPart = new MimeBodyPart();

            // Attachment
            MimeBodyPart attachmentPart = new MimeBodyPart();
//            attachmentPart.attachFile(attachment);

            // Combine both parts
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            // Send email
            Transport.send(message);
            System.out.println("Email sent successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        sendEmail("pawelchrostek22@gmail.com", "Test", null);
    }
}

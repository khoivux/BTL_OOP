package com.javaweb.app.service;

import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    static String senderEmail = "lamhttb@gmail.com";
    static String senderPassword = "mvrt jcal enpl cyzj";

    public static void sendOtpEmail(String recipientEmail, String otp) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject("OTP xác minh tài khoản");
        message.setText("Mã OTP của bạn là: " + otp + ". Mã này sẽ hết hạn sau 10 phút.");

        Transport.send(message);
        System.out.println("OTP đã được gửi tới email: " + recipientEmail);
    }

    public static String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Mã OTP 6 chữ số
        return String.valueOf(otp);
    }
}

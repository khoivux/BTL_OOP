package com.javaweb.app.controller;

import com.javaweb.app.entity.User;
import com.javaweb.app.repository.UserRepository;
import com.javaweb.app.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/auth")
public class ForgetController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email không tồn tại");
        }

        String otp = EmailService.generateOtp(); // Tạo OTP
        try {
            EmailService.sendOtpEmail(email, otp); // Gửi OTP qua email
            return ResponseEntity.ok(otp); // Trả OTP về client
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Gửi email thất bại");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String email, @RequestParam String newPassword) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email không tồn tại");
        }

        String encryptedPassword = encryptPassword(newPassword);
        user.setPassword(encryptedPassword);
        userRepository.save(user);

        return ResponseEntity.ok("Đổi mật khẩu thành công");
    }

    private String encryptPassword(String password) {
        // Giả sử có logic mã hóa ở đây, có thể sử dụng BCrypt hoặc một phương thức mã hóa khác
        return password; // Thay thế bằng mã hóa thực tế
    }
}

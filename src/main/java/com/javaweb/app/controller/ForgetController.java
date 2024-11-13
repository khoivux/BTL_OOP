package com.javaweb.app.controller;

import com.javaweb.app.entity.User;
import com.javaweb.app.repository.UserRepository;
import com.javaweb.app.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
public class ForgetController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return "Email không tồn tại";
        }

        String otp = EmailService.generateOtp();
        user.setOtp(otp);
        user.setOtpExpiry(LocalDateTime.now().plusMinutes(10));
        userRepository.save(user);

        try {
            EmailService.sendOtpEmail(email, otp);
            return "OTP đã được gửi tới email của bạn";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Gửi email thất bại";
        }
    }

    @PostMapping("/verify-otp")
    public boolean verifyOtp(@RequestParam String email, @RequestParam String otp) {
        User user = userRepository.findByEmail(email);
        return user.getOtp() != null && user.getOtp().equals(otp) && !user.getOtpExpiry().isBefore(LocalDateTime.now());
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String email, @RequestParam String newPassword) {
        User user = userRepository.findByEmail(email);
        String encryptedPassword = encryptPassword(newPassword);
        user.setPassword(encryptedPassword);
        user.setOtp(null);
        user.setOtpExpiry(null);
        userRepository.save(user);

        return "Đổi mật khẩu thành công";
    }

    private String encryptPassword(String password) {
        // Giả sử có logic mã hóa ở đây, có thể sử dụng BCrypt hoặc một phương thức mã hóa khác
        return password; // Chỉ là ví dụ, hãy thay thế bằng mã hóa thực tế
    }


}

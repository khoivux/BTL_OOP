package com.javaweb.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String showHistoryPage() {
        return "profile"; // Trả về file history.html trong thư mục templates
    }
}
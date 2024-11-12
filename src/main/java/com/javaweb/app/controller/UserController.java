package com.javaweb.app.controller;

import com.javaweb.app.entity.User;
import com.javaweb.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ResponseEntity handleLogin(
            @RequestParam String email,
            @RequestParam String password) {
        try {
            User user = userService.authUser(email, password);
            return ResponseEntity.ok(new LoginResponse(user.getUserName()));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // Tạo lớp LoginResponse để gửi tên người dùng
    public class LoginResponse {
        private String name;

        public LoginResponse(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    @PostMapping("/register")
    public ResponseEntity handleRegister(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password
    ) {
        try {
            userService.registerUser(username, email, password);
            return new ResponseEntity<>("Success register!", HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

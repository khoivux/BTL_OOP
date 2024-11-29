package com.javaweb.app.controller;

import com.javaweb.app.entity.User;
import com.javaweb.app.service.UserService;
import jakarta.servlet.http.HttpSession;
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

//    @Autowired
//    private HttpSession session;

    @GetMapping
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ResponseEntity handleLogin(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session) {
        try {
            User user = userService.authUser(email, password);
            session.setAttribute("user", user);
            return ResponseEntity.ok(new LoginResponse(user.getId(), user.getUserName()));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // Tạo lớp LoginResponse để gửi tên người dùng
    public class LoginResponse {
        private Long id;
        private String name;

        public LoginResponse(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }
        public String getName() {
            return name;
        }
    }

    @PostMapping("/register")
    public ResponseEntity handleRegister(
            @RequestParam String username,
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String phoneNumber
    ) {
        try {
            userService.registerUser(username, fullName, email, password, phoneNumber);
            return new ResponseEntity<>("Success register!", HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("user_profile")
    public ModelAndView userProfile(HttpSession session) {
        User user = (User) session.getAttribute("user");
        System.out.println(user.getUserName());
        ModelAndView model = new ModelAndView("user_profile");
        model.addObject("user", user);
        return model;
    }

    @PostMapping("update_profile")
    public ResponseEntity updateProfile(
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String phoneNumber,
            @RequestParam String address,
            HttpSession session
    ) {
        try {
            User user = (User) session.getAttribute("user");
            userService.updateProfile(user.getId(), fullName, email, phoneNumber, address);
            // update session
            session.setAttribute("user", userService.userRepository.getById(user.getId()));
            return new ResponseEntity<>("Cập nhật thông tin thành công!", HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

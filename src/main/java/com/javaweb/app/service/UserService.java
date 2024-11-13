package com.javaweb.app.service;

import com.javaweb.app.entity.User;
import com.javaweb.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    // admin login
    public User authAdmin(String email, String password) {
        User admin = userRepository.findByEmail(email);
        //List<User> list = userRepository.findAll();
        if(admin != null) {
            if(admin.getUserPassword().equals(password) && admin.getUserRole().equals("admin")) {
                return admin;
            }
            return null;
        }
        return null;
    }

    //user login
    public User authUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if(user != null) {
            if(user.getUserPassword().equals(password)) {
                return user;
            }
            throw new RuntimeException("Mật khẩu không chính xác");
        }
        throw new RuntimeException("Tài khoản không tồn tại");
    }

    //user register
    public void registerUser(String username, String fullName, String email, String password, String phoneNumber) {
        User newUser = new User(username, fullName , email, password, phoneNumber);
        if(userRepository.findByUsername(username) != null) {
            throw new RuntimeException("Username đã tồn tại!");
        }
        if(userRepository.findByEmail(email) != null) {
            throw new RuntimeException("Email đã tồn tại!");
        }
        userRepository.save(newUser);
    }
}

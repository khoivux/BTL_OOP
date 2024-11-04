package com.javaweb.app.service;

import com.javaweb.app.entity.User;
import com.javaweb.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    // admin login
    public User authAdmin(String email, String password) {
        User admin = userRepository.findByEmail(email);
        if(admin != null) {
            if(admin.getUserPassword().equals(password) && admin.getUserRole().equals("admin")) {
                return admin;
            }
            return null;
        }
        return null;
    }
}

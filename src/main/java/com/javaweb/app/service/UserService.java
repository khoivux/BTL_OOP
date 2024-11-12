package com.javaweb.app.service;

import com.javaweb.app.dto.UserDTO;
import com.javaweb.app.entity.User;
import com.javaweb.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;
    @Autowired
    private UserRepository userRepository1;

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
    public void registerUser(String username, String email, String password) {
        User newUser = new User(username, email, password);
        if(userRepository.findByUsername(username) != null) {
            throw new RuntimeException("Username đã tồn tại!");
        }
        if(userRepository.findByEmail(email) != null) {
            throw new RuntimeException("Email đã tồn tại!");
        }
        userRepository.save(newUser);
    }

    public List<UserDTO> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for(User user : users) {
            UserDTO userDTO = new UserDTO();
            if (user.getUserRole().equals("user")) {
                userDTO.setId(user.getUserID());
                userDTO.setUsername(user.getUserName());
                userDTO.setEmail(user.getUserEmail());
                userDTOS.add(userDTO);
            }
        }
        return userDTOS;
    }


    // xoa 1 user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}

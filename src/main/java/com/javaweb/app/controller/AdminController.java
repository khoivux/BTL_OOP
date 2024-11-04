package com.javaweb.app.controller;

import com.javaweb.app.dto.HomestayDto;
import com.javaweb.app.entity.User;
import com.javaweb.app.enums.Province;
import com.javaweb.app.model.HomestaySearchResponse;
import com.javaweb.app.repository.HomestayRepository;
import com.javaweb.app.service.HomestayService;
import com.javaweb.app.service.UserService;
import com.javaweb.app.service.impl.HomestayServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    public HomestayServiceImpl homestayServiceImpl;
    @Autowired
    public HomestayService homestayService;
    @Autowired
    public HomestayRepository homestayRepository;

    @GetMapping()
    public ModelAndView adminLogin() {
        return new ModelAndView("admin/loginAdmin");
    }

    @PostMapping("/login")
    public ModelAndView handleLogin(@RequestParam("email") String email,
                                    @RequestParam("password") String password,
                                    HttpSession session){
        if(userService.authAdmin(email, password) != null) {
            session.setAttribute("admin", true); // Đánh dấu là đã đăng nhập
            return new ModelAndView("admin/homestay");
        } else {
            ModelAndView modelAndView = new ModelAndView("admin/loginAdmin");
            modelAndView.addObject("error", "Invalid username or password");
            return modelAndView;
        }
    }

    @GetMapping("/homestay-list") // Trang quản lý Homestay
    public ModelAndView adminHomestayPage(@RequestParam Map<String, Object> params) {
        int cnt = 1;
        List<HomestaySearchResponse> list = homestayService.findByFilter(params);
        ModelAndView model = new ModelAndView("admin/homestay");
        model.addObject("homestays", list);
        return model;
    }

    @GetMapping("/homestay-edit") // Trang thêm mới homestay
    public ModelAndView addHomestayPage() {
        return new ModelAndView("admin/add");
    }

    @GetMapping("/homestay-edit/{id}") // Trang cập nhật homestay
    public ModelAndView updateHomestayPage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("admin/update");
        HomestayDto homestayDto = homestayService.findHomestayById(id);
        modelAndView.addObject("homestay", homestayDto);
        return modelAndView;
    }
}

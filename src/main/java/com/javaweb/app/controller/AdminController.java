package com.javaweb.app.controller;

import com.javaweb.app.dto.BookingDTO;
import com.javaweb.app.service.BookingService;
import org.springframework.ui.Model;
import com.javaweb.app.dto.HomestayDto;

import com.javaweb.app.dto.HomestayResponseDTO;
import com.javaweb.app.dto.UserDTO;
import com.javaweb.app.entity.User;
import com.javaweb.app.repository.HomestayRepository;
import com.javaweb.app.service.FacilitiesService;
import com.javaweb.app.service.HomestayService;
import com.javaweb.app.service.ProvinceService;
import com.javaweb.app.service.UserService;
import com.javaweb.app.service.impl.HomestayServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    public HomestayServiceImpl homestayServiceImpl;
    @Autowired
    public HomestayService homestayService;
    @Autowired
    public HomestayRepository homestayRepository;
    @Autowired
    public FacilitiesService facilitiesService;
    @Autowired
    public ProvinceService provinceService;
    @Autowired
    public UserService userService;
    @Autowired
    public BookingService bookingService;

    @GetMapping()
    public ModelAndView adminLogin(HttpSession session) {
        if (session.getAttribute("admin") != null) {
            List<HomestayResponseDTO> list = homestayService.findAll();
            ModelAndView model = new ModelAndView("admin/homestay");
            model.addObject("homestays", list);
            return model;
        }
        return new ModelAndView("admin/loginAdmin");
    }

    @PostMapping("/manage")
    public ModelAndView handleLogin(@RequestParam("email") String email,
                                    @RequestParam("password") String password,
                                    HttpSession session){
        if(userService.authAdmin(email, password) != null) {
            session.setAttribute("admin", true); // Đánh dấu là đã đăng nhập
            ModelAndView modelAndView = new ModelAndView("admin/homestay");
            List<HomestayResponseDTO> list = homestayService.findAll();
            modelAndView.addObject("homestays", list);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("admin/loginAdmin");
            modelAndView.addObject("error", "Sai tài khoản hoặc mật khẩu");
            return modelAndView;
        }
    }


    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.removeAttribute("admin");
        return new ModelAndView("admin/loginAdmin");
    }


    @GetMapping("/homestay-list") // Trang quản lý Homestay
    public ModelAndView adminHomestayPage(@RequestParam Map<String, Object> params,
                                          @RequestParam(required = false) List<Long> homestayFacilities,
                                          HttpSession session) {
        if(session.getAttribute("admin") != null){
            List<HomestayResponseDTO> list = homestayService.findAll();// Lấy từ homestay database
            ModelAndView model = new ModelAndView("admin/homestay"); // Thêm HTML
            model.addObject("homestays", list); // Thêm model
            return model;
        }
        return new ModelAndView("admin/loginAdmin");
    }


    @GetMapping("/homestay-edit") // Trang thêm mới homestay
    public ModelAndView addHomestayPage(HttpSession session) {
        if(session.getAttribute("admin") != null){
            ModelAndView modelAndView = new ModelAndView("admin/add");
            modelAndView.addObject("facilities", facilitiesService.findAll());
            modelAndView.addObject("provinces", provinceService.findAll());

           // modelAndView.addObject("rooms",)
            return modelAndView;
        }
        return new ModelAndView("admin/loginAdmin");
    }

    @GetMapping("/homestay-edit/{id}") // Trang cập nhật homestay
    public ModelAndView updateHomestayPage(
            @PathVariable("id") Long id,
            HttpSession session) {
        if(session.getAttribute("admin") != null){
            ModelAndView modelAndView = new ModelAndView("admin/update");
            HomestayResponseDTO homestayDto = homestayService.findHomestayById(id);
            modelAndView.addObject("facilities", facilitiesService.findAll());
            modelAndView.addObject("provinces", provinceService.findAll());
            modelAndView.addObject("homestay", homestayDto);
            return modelAndView;
        }
        return new ModelAndView("admin/loginAdmin");
    }

    @GetMapping("/user") // Trang quản lý người dùng
    public ModelAndView showAdminUserPage() {
        List<UserDTO> users = userService.getAllUser(); // Lấy danh sách người dùng
        ModelAndView model = new ModelAndView("admin/user"); // Tạo ModelAndView
        model.addObject("users", users); // Gửi danh sách người dùng vào model
        return model;
    }

    @GetMapping("/user-paymenthistory/{id}")
    public ModelAndView showPaymentHistory(@PathVariable Long id) {
        List<BookingDTO> bookings = bookingService.getPaymentHistory(id);
        ModelAndView model = new ModelAndView("admin/user-paymenthistory");
        model.addObject("paymentHistories", bookings);
        return model;
    }
}

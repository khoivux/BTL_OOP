package com.javaweb.app.controller;

import com.javaweb.app.dto.HomestayDto;

import com.javaweb.app.dto.HomestayResponseDTO;
import com.javaweb.app.repository.HomestayRepository;
import com.javaweb.app.service.HomestayService;
import com.javaweb.app.service.UserService;
import com.javaweb.app.service.impl.HomestayServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public UserService userService;

//    @GetMapping // Trang quản trị
//    public ModelAndView adminPage(@RequestParam Map<String, Object> params,
//                                     @RequestParam(required = false) List<Long> homestayFacilities) {
//       // List<HomestayResponseDTO> list = homestayService.finFilter(params, homestayFacilities);
//        List<HomestayResponseDTO> list = homestayService.findAll();
//        ModelAndView model = new ModelAndView("admin/homestay");
//        model.addObject("homestays", list);
//        return model;
//    }
    @GetMapping()
    public ModelAndView adminLogin() {
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
            modelAndView.addObject("error", "Invalid username or password");
            return modelAndView;
        }
    }
    @GetMapping("/homestay-list") // Trang quản lý Homestay
    public ModelAndView adminHomestayPage(@RequestParam Map<String, Object> params,
                                          @RequestParam(required = false) List<Long> homestayFacilities) {
        List<HomestayResponseDTO> list = homestayService.findByFilter(params, homestayFacilities); // Lấy từ homestay database
        ModelAndView model = new ModelAndView("admin/homestay"); // Thêm HTML
        model.addObject("homestays", list); // Thêm model
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

package com.javaweb.app.controller;

import com.javaweb.app.dto.BookingDTO;
import com.javaweb.app.dto.HomestayCreateDTO;
import com.javaweb.app.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class
AdminController {
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

    // Đăng nhập Admin
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
    // Trang chủ Admin
    @PostMapping("/manage")
    public ModelAndView handleLogin(@RequestParam(required = false) Map<String, Object> params,
                                    @RequestParam("email") String email,
                                    @RequestParam("password") String password,
                                    HttpSession session){
        if(userService.authAdmin(email, password) != null) {
            session.setAttribute("admin", true); // Đánh dấu là đã đăng nhập
            ModelAndView modelAndView = new ModelAndView("admin/homestay");
            List<HomestayResponseDTO> list = homestayService.findByFilter(params, null);
            modelAndView.addObject("homestays", list);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("admin/loginAdmin");
            modelAndView.addObject("error", "Sai tài khoản hoặc mật khẩu");
            return modelAndView;
        }
    }

    // Đăng xuất
    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.removeAttribute("admin");
        return new ModelAndView("admin/loginAdmin");
    }

    // Trang quản lý Homestay
    @GetMapping("/homestay-list")
    public ModelAndView adminHomestayPage(@RequestParam Map<String, Object> params,
                                          @RequestParam(required = false) List<Long> homestayFacilities,
                                          HttpSession session) {
        if(session.getAttribute("admin") != null){
            List<HomestayResponseDTO> list = homestayService.findByFilter(params, null);
            ModelAndView model = new ModelAndView("admin/homestay");
            model.addObject("homestays", list);
            return model;
        }
        return new ModelAndView("admin/loginAdmin");


    }

    // Trang thêm mới homestay
    @GetMapping("/homestay-edit")
    public ModelAndView addHomestayPage(HttpSession session) {
        if(session.getAttribute("admin") != null){
            ModelAndView modelAndView = new ModelAndView("admin/add");
            modelAndView.addObject("facilities", facilitiesService.findAll());
            modelAndView.addObject("provinces", provinceService.findAll());
            return modelAndView;
        }
        return new ModelAndView("admin/loginAdmin");
    }
    // Trang cập nhật homestay
    @GetMapping("/homestay-edit/{id}")
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

    // Thêm mới homestay
    @PostMapping("/homestay-add")
    public ResponseEntity<String> addHomestay(@ModelAttribute HomestayCreateDTO homestayCreateDTO,
                                              RedirectAttributes redirectAttributes,
                                              HttpSession session) {
        try {
            HomestayResponseDTO homestayResponseDTO = homestayService.createHomestay(homestayCreateDTO);
            return ResponseEntity.ok("Thêm mới homestay thành công!");
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    // Cập nhật homestay
    @PostMapping("/homestay-update")
    public  ResponseEntity<String> updateHomestay(@ModelAttribute HomestayCreateDTO homestayCreateDTO,
                                                  HttpSession session) {
        try {
            homestayService.updateHomestay(homestayCreateDTO);
            return ResponseEntity.ok("Cập nhật homestay thành công!");
        } catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Xóa homestay
    @PostMapping("/homestay-delete/{id}")
    public RedirectView deleteHomestayById(@PathVariable Long id,
                                                     HttpSession session) {
            homestayService.deleteHomestay(id);
            return new RedirectView("/admin/homestay-list");
    }

    // READ
    @GetMapping("/homestay/{id}")
    public HomestayResponseDTO getById(@PathVariable Long id,
                                       HttpSession session){
        return homestayService.findHomestayById(id);
    }
    // Xóa User
    @PostMapping("/user-delete/{id}")
    public RedirectView deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return new RedirectView("/admin/user");
    }
    // Trang quản lý User
    @GetMapping("/user")
    public ModelAndView showAdminUserPage() {
        List<UserDTO> users = userService.getAllUser();
        ModelAndView model = new ModelAndView("admin/user");
        model.addObject("users", users);
        return model;
    }
    // Trang lịch sử giao dịch
    @GetMapping("/user-paymenthistory/{id}")
    public ModelAndView showPaymentHistory(@PathVariable Long id) {
        List<BookingDTO> bookings = bookingService.getPaymentHistory(id);
        ModelAndView model = new ModelAndView("admin/user-paymenthistory");
        model.addObject("paymentHistories", bookings);
        return model;
    }
    // Cập nhật trạng thái
    @PostMapping("/user-paymenthistory/{id}")
    public ResponseEntity<String> updateBookingStatus(@PathVariable("id") Long id, @RequestBody Map<String, String> body) {
        try {
            String status = body.get("status");
            bookingService.updateBookingStatus(id, status);
            return ResponseEntity.ok("Cập nhật trạng thái thành công!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Cập nhật trạng thái thất bại!");
        }
    }

//    @PostMapping("/booking/{id}") // Xóa user theo id
//    public RedirectView deleteUserById(@PathVariable Long id) {
//        userService.deleteUser(id);
//        return new RedirectView("/admin/user");
//    }
}

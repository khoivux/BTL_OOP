package com.javaweb.app.controller;


import com.javaweb.app.dto.HomestayCreateDTO;
import com.javaweb.app.dto.HomestayResponseDTO;
import com.javaweb.app.dto.HomestayDto;
import com.javaweb.app.dto.UserDTO;
import com.javaweb.app.entity.User;
import com.javaweb.app.repository.HomestayRepository;
import com.javaweb.app.service.BookingService;
import com.javaweb.app.service.HomestayService;
import com.javaweb.app.service.UserService;
import com.javaweb.app.service.impl.HomestayServiceImpl;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;

@RestController
public class HomestayController {
    @Autowired
    public HomestayService homestayService;
    @Autowired
    public HomestayRepository homestayRepository;
    @Autowired
    public UserService userService;
    @Autowired
    public BookingService bookingService;

    // CREATE
    @PostMapping("/admin/homestay-add")
    public ResponseEntity<HomestayResponseDTO> addHomestay(@ModelAttribute HomestayCreateDTO homestayCreateDTO) {
        HomestayResponseDTO homestayResponseDTO = homestayService.createHomestay(homestayCreateDTO);
        return ResponseEntity.ok(homestayResponseDTO);
    }
    // UPDATE
    @PutMapping("/admin/homestay-update")
    public ResponseEntity<HomestayDto> updateHomestay(@RequestBody HomestayDto updateHomestayDto) {
        HomestayDto homestayDto = homestayService.updateHomestay(updateHomestayDto.getId(), updateHomestayDto);
        return ResponseEntity.ok(homestayDto);
    }

    // DELETE
    @PostMapping("/admin/homestay-delete")
    public void deleteHomestayByIdIn(@RequestParam List<Long> ids) {
        homestayService.deleteHomestays(ids);
    }

    @PostMapping("/admin/homestay-delete/{id}") // Xóa homestay theo id
    public RedirectView deleteHomestayById(@PathVariable Long id) {
        homestayService.deleteHomestay(id);
        return new RedirectView("/admin/homestay-list");
    }

    // READ
    @GetMapping("/admin/homestay/{id}")
    public HomestayResponseDTO getById(@PathVariable Long id){
        return homestayService.findHomestayById(id);
    }
    // Xoa User
    @PostMapping("/admin/user-delete/{id}") // Xóa user theo id
    public RedirectView deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return new RedirectView("/admin/user");
    }
    // xoa lich su booking
    @PostMapping("/admin/user-paymenthistory-delete/{id}")// Xóa booking theo id
    public RedirectView deleteBookingById(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return new RedirectView("/admin/user-paymenthistory/{id}");
    }
}

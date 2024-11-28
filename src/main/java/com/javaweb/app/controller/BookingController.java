package com.javaweb.app.controller;

import com.javaweb.app.dto.BookingDTO;

import com.javaweb.app.entity.BookingEntity;
import com.javaweb.app.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Controller
public class BookingController {
    @Autowired
    public BookingService bookingService;

    @PostMapping(value = "/booking")
    public BookingDTO createBooking(@RequestBody BookingDTO bookingDTO) {
        return bookingService.createBooking(bookingDTO);
    }
    // Hiển thị tất cả bookings
    @GetMapping("/history/{userId}")
    public ModelAndView showBookingHistory(@PathVariable Long userId) {
        List<BookingDTO> bookings = bookingService.getBookingsByUser_Id(userId);
        ModelAndView model = new ModelAndView("history");
        model.addObject("bookings", bookings);  // Truyền danh sách booking vào model
        return model;
    }
    // xoa booking
    @PostMapping("/history/delete/{id}")
    public RedirectView deleteBookingById(@PathVariable Long id) {
        // Giả sử bạn lấy userId từ booking đã xóa
        Long userId = bookingService.getUserIdByBookingId(id); // Hoặc từ cách khác
        bookingService.deleteBooking(id); // Xóa booking
        return new RedirectView("/history/" + userId); // Chuyển hướng về lịch sử của người dùng
    }
    @PostMapping("/admin/user-paymenthistory/{id}")
    public ResponseEntity<String> updateBookingStatus(@PathVariable("id") Long id, @RequestBody Map<String, String> body) {
        try {
            // Lấy trạng thái từ body
            String status = body.get("status");
            // Gọi service để cập nhật trạng thái
            bookingService.updateBookingStatus(id, status);
            return ResponseEntity.ok("Cập nhật trạng thái thành công!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Cập nhật trạng thái thất bại!");
        }
    }
}

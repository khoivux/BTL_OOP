package com.javaweb.app.controller;


import com.javaweb.app.dto.HomestayCreateDTO;
import com.javaweb.app.dto.HomestayResponseDTO;
import com.javaweb.app.dto.HomestayDto;
import com.javaweb.app.repository.HomestayRepository;
import com.javaweb.app.service.BookingService;
import com.javaweb.app.service.HomestayService;
import com.javaweb.app.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
    // UPDATE
    @PostMapping("/admin/homestay-update")
    public ResponseEntity<HomestayDto> updateHomestay(@ModelAttribute HomestayCreateDTO homestayCreateDTO,
                                                      HttpSession session) {

       // HomestayDto homestayDto = homestayService.updateHomestay(updateHomestayDto.getId(), updateHomestayDto);
        return ResponseEntity.ok(null);
    }

    // DELETE
    @PostMapping("/admin/homestay-delete/{id}") // Xóa homestay theo id
    public RedirectView deleteHomestayById(@PathVariable Long id,
                                           RedirectAttributes redirectAttributes,
                                           HttpSession session) {
        homestayService.deleteHomestay(id);
        redirectAttributes.addFlashAttribute("message", "Homestay đã được xóa.");
        return new RedirectView("/admin/homestay-list");
    }

    // READ
    @GetMapping("/admin/homestay/{id}")
    public HomestayResponseDTO getById(@PathVariable Long id,
                                       HttpSession session){
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

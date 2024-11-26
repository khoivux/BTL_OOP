package com.javaweb.app.controller;


import com.javaweb.app.dto.HomestayCreateDTO;
import com.javaweb.app.dto.HomestayResponseDTO;
import com.javaweb.app.dto.HomestayDto;
import com.javaweb.app.exception.FileNotValidException;
import com.javaweb.app.repository.HomestayRepository;
import com.javaweb.app.service.HomestayService;
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

    // CREATE
    @PostMapping("/admin/homestay-add")
    public ResponseEntity<String> addHomestay(@ModelAttribute HomestayCreateDTO homestayCreateDTO,
                                    RedirectAttributes redirectAttributes,
                                    HttpSession session) {
        try {
            HomestayResponseDTO homestayResponseDTO = homestayService.createHomestay(homestayCreateDTO);
            return ResponseEntity.ok("Thêm mới homestay thành công!");
        }
        catch (FileNotValidException e) {
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
    public ResponseEntity<String> deleteHomestayById(@PathVariable Long id,
                                           HttpSession session) {
        try {
            homestayService.deleteHomestay(id);
            return ResponseEntity.ok("Xóa homestay thành công!");
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // READ
    @GetMapping("/admin/homestay/{id}")
    public HomestayResponseDTO getById(@PathVariable Long id,
                                       HttpSession session){
        return homestayService.findHomestayById(id);
    }
}

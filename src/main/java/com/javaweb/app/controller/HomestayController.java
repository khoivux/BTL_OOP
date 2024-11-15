package com.javaweb.app.controller;


import com.javaweb.app.dto.HomestayCreateDTO;
import com.javaweb.app.dto.HomestayResponseDTO;
import com.javaweb.app.dto.HomestayDto;
import com.javaweb.app.repository.HomestayRepository;
import com.javaweb.app.service.HomestayService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
    public RedirectView addHomestay(@ModelAttribute HomestayCreateDTO homestayCreateDTO,
                                    RedirectAttributes redirectAttributes,
                                    HttpSession session) {
        HomestayResponseDTO homestayResponseDTO = homestayService.createHomestay(homestayCreateDTO);
        redirectAttributes.addFlashAttribute("message", "Homestay đã được thêm.");
        return new RedirectView("/admin/homestay-list");
    }
    // UPDATE
    @PostMapping("/admin/homestay-update")
    public ResponseEntity<HomestayDto> updateHomestay(@RequestBody HomestayCreateDTO homestayCreateDTO,
                                                      HttpSession session) {

        int cnt = 1;
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
    public HomestayResponseDTO getById(@PathVariable Long id){
        return homestayService.findHomestayById(id);
    }
}

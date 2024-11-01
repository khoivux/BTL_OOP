package com.javaweb.app.controller;


import com.javaweb.app.model.HomestaySearchResponse;
import com.javaweb.app.dto.HomestayDto;
import com.javaweb.app.repository.HomestayRepository;
import com.javaweb.app.service.HomestayService;
import com.javaweb.app.service.impl.HomestayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;

@RestController
public class                                                                                 HomestayController {
    @Autowired
    public HomestayServiceImpl homestayServiceImpl;
    @Autowired
    public HomestayService homestayService;
    @Autowired
    public HomestayRepository homestayRepository;

    // CREATE
    @PostMapping("/admin/homestay-add")  // Thêm mới homestay
    public ResponseEntity<HomestayDto> addHomestay(@RequestBody HomestayDto homestayDto) {
        HomestayDto addedHomestay = homestayService.createHomestay(homestayDto);
        return ResponseEntity.ok(homestayDto);
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
    public ResponseEntity<HomestayDto> getById(@PathVariable Long id){
        HomestayDto homestayDto = homestayService.findHomestayById(id);
        return ResponseEntity.ok(homestayDto);
    }

    @GetMapping("/getall")
    public List<HomestaySearchResponse> getAllFIlter(@RequestParam  Map<String, Object> params) {
        List<HomestaySearchResponse> list = homestayService.findByFilter(params);
        return list;
    }
}

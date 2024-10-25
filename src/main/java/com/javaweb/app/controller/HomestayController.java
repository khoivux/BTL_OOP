package com.javaweb.app.controller;


import com.javaweb.app.model.HomestaySearchResponse;
import com.javaweb.app.dto.HomestayDto;
import com.javaweb.app.repository.HomestayRepository;
import com.javaweb.app.service.HomestayService;
import com.javaweb.app.service.impl.HomestayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class HomestayController {
    @Autowired
    public HomestayServiceImpl homestayServiceImpl;
    @Autowired
    public HomestayService homestayService;
    @Autowired
    public HomestayRepository homestayRepository;

    @GetMapping
    public ModelAndView adminPage() {
        return new ModelAndView("admin/homestay");
    }

    @GetMapping("/homestay")
    public ModelAndView adminHomestayPage() {
        List<HomestaySearchResponse> list = homestayService.findAll();
        ModelAndView model = new ModelAndView("admin/homestay");
        model.addObject("homestays", list);
        return model;
    }

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<HomestayDto> createHomestay(@RequestBody HomestayDto homestayDto) {
        HomestayDto savedHomestay = homestayService.createHomestay(homestayDto);
        return new ResponseEntity<>(savedHomestay, HttpStatus.CREATED);
    }
    @GetMapping("/add-homestay")
    public ModelAndView addHomestayPage() {
       // HomestayDto savedHomestay = homestayService.createHomestay(homestayDto);
        return new ModelAndView("admin/add_homestay");
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<HomestayDto> updateHomestay(@PathVariable("id") Long id,
                                                      @RequestBody HomestayDto updateHomestayDto) {
        HomestayDto homestayDto = homestayService.updateHomestay(id, updateHomestayDto);
        return ResponseEntity.ok(homestayDto);
    }

    // DELETE
    @PostMapping("/homestay/delete")
    public void deleteHomestayByIdIn(@RequestParam List<Long> ids) {
        homestayService.deleteHomestays(ids);
    }

    @PostMapping("/homestay/delete/{id}")
    public RedirectView deleteHomestayById(@PathVariable Long id) {
        homestayService.deleteHomestay(id);
        return new RedirectView("/admin/homestay");
    }

}

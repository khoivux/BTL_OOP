package com.javaweb.app.controller;

import com.javaweb.app.dto.HomestayDto;
import com.javaweb.app.model.HomestaySearchResponse;
import com.javaweb.app.repository.HomestayRepository;
import com.javaweb.app.service.HomestayService;
import com.javaweb.app.service.impl.HomestayServiceImpl;
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

    @GetMapping // Trang quản trị
    public ModelAndView adminPage(@RequestParam Map<String, Object> params,
                                     @RequestParam List<Long> homestayFacilities,
                                        @RequestParam List<Long> roomFacilities) {
        List<HomestaySearchResponse> list = homestayService.findByFilter(params, homestayFacilities, roomFacilities);
        ModelAndView model = new ModelAndView("admin/homestay");
        model.addObject("homestays", list);
        return model;
    }

    @GetMapping("/homestay-list") // Trang quản lý Homestay
    public ModelAndView adminHomestayPage(@RequestParam Map<String, Object> params,
                                          @RequestParam List<Long> homestayFacilities,
                                            @RequestParam List<Long> roomFacilities) {
        int cnt = 1;
        List<HomestaySearchResponse> list = homestayService.findByFilter(params, homestayFacilities, roomFacilities);
        ModelAndView model = new ModelAndView("admin/homestay");
        model.addObject("homestays", list);
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

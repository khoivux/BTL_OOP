package com.javaweb.app.controller;

import com.javaweb.app.model.HomestaySearchResponse;
import com.javaweb.app.service.HomestayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    public HomestayService homestayService;

    @GetMapping(value = "/")
    public ModelAndView homePage() {
        return new ModelAndView("home");
    }

    @GetMapping(value = "/search")
    public ModelAndView searchPage(@RequestParam Map<String, Object> params) {
        List<HomestaySearchResponse> list = homestayService.findAll();
        ModelAndView model = new ModelAndView("search");
        model.addObject("homestays", list);
        return model;
    }
}

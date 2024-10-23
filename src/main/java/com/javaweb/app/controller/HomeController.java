package com.javaweb.app.controller;

import com.javaweb.app.dto.HomestayDto;
import com.javaweb.app.service.HomestayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    public HomestayService homestayService;

    @GetMapping(value = "/")
    public ModelAndView homePage() {
        return new ModelAndView("home");
    }

    @GetMapping(value = "/search")
    public ModelAndView searchPage() {
        List<HomestayDto> list = homestayService.findAll();
        ModelAndView model = new ModelAndView("search");
         model.addObject("homestays", list);
        return model;
    }

    @GetMapping(value = "/login")
    public ModelAndView loginPage() {
        ModelAndView model = new ModelAndView("login");
        //model.addObject();
        return model;
    }

    @GetMapping(value = "/register")
    public ModelAndView registerPage() {
        ModelAndView model = new ModelAndView("register");
        //model.addObject();
        return model;
    }
}

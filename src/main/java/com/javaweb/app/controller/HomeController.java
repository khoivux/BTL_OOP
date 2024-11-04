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

//    @GetMapping(value = "/search")
//    public ModelAndView searchPage(@RequestParam Map<String, Object> params) {
//        List<HomestaySearchResponse> list = homestayService.findByFilter(params, , );
//        ModelAndView model = new ModelAndView("search");
//        model.addObject("homestays", list);
//        return model;
//    }

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

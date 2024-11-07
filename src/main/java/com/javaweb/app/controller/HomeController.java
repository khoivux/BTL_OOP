package com.javaweb.app.controller;

import com.javaweb.app.dto.HomestayFacilitiesDTO;
import com.javaweb.app.dto.HomestayResponseDTO;
import com.javaweb.app.service.FacilitiesService;
import com.javaweb.app.service.HomestayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    public HomestayService homestayService;
//    @Autowired
//    public FacilitiesService facilitiesService;
    @GetMapping(value = "/")
    public ModelAndView homePage() {
        return new ModelAndView("home");
    }

    @GetMapping(value = "/search")
    public ModelAndView searchPage(@RequestParam Map<String, Object> params,
                                   @RequestParam(required = false) List<Long> homestayFacilities) {

        List<HomestayResponseDTO> list = homestayService.findByFilter(params, homestayFacilities);
        ModelAndView model = new ModelAndView("search");

        List<Long> selectedFacilities = (homestayFacilities != null) ? homestayFacilities : new ArrayList<>();
        model.addObject("selectedFacilities", selectedFacilities);

//        List<HomestayFacilitiesDTO> facilities = facilitiesService.findAll();
//        model.addObject("facilities", facilities);

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

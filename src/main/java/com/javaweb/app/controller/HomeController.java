package com.javaweb.app.controller;

import com.javaweb.app.dto.FacilitiesDTO;
import com.javaweb.app.dto.HomestayResponseDTO;
import com.javaweb.app.dto.ServiceDTO;
import com.javaweb.app.service.FacilitiesService;
import com.javaweb.app.service.HomestayService;
import com.javaweb.app.service.ServiceService;
import com.javaweb.app.utils.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    public HomestayService homestayService;
    @Autowired
    public FacilitiesService facilitiesService;
    @Autowired
    public ServiceService serviceService;


    @GetMapping(value = "/")
    public ModelAndView homePage() {
        return new ModelAndView("home");
    }

    @GetMapping(value = "/search")
    public ModelAndView searchPage(@RequestParam Map<String, Object> params,
                                   @RequestParam(required = false) List<Long> facilities,
                                   @RequestParam(required = false) List<Long> rooms,
                                   @RequestParam(required = false) List<Long> services) {

        List<HomestayResponseDTO> homestays = homestayService.findByFilter(params, facilities);

        // Khởi tạo model và trả về view
        ModelAndView model = new ModelAndView("search");

        // Lấy tất cả tiện nghi và dịch vụ, và các lựa chọn đã chọn
        model.addObject("selectedFacilities", facilities != null ? facilities : new ArrayList<>());
        model.addObject("selectedRooms", rooms != null ? rooms : new ArrayList<>());
        model.addObject("selectedServices", services != null ? services : new ArrayList<>());


        model.addObject("services", serviceService.findAll());
        model.addObject("facilities", facilitiesService.findAll());

        // Thêm ngày check-in, check-out và danh sách homestays vào model
        model.addObject("checkInDate", MapUtil.getObject(params, "checkinDate", String.class));
        model.addObject("checkOutDate", MapUtil.getObject(params, "checkoutDate", String.class));
        model.addObject("params", params);
        model.addObject("homestays", homestays);
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

package com.javaweb.app.controller;

import com.javaweb.app.dto.HomestayResponseDTO;
import com.javaweb.app.entity.User;
import com.javaweb.app.exception.DateNotValidException;
import com.javaweb.app.service.FacilitiesService;
import com.javaweb.app.service.HomestayService;
import com.javaweb.app.utils.DateUtil;
import com.javaweb.app.utils.MapUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class WebController {
    @Autowired
    public HomestayService homestayService;
    @Autowired
    public FacilitiesService facilitiesService;


    @GetMapping(value = "/")
    public ModelAndView homePage(HttpSession session) {
        ModelAndView model = new ModelAndView("home");
        List<HomestayResponseDTO> homestays = homestayService.findAll();

        int totalSize = homestays.size();
        int groupSize = (int) Math.ceil((double) totalSize / 3);

        List<List<HomestayResponseDTO>> groupedHomestays = new ArrayList<>();
        for (int i = 0; i < totalSize; i += groupSize) {
            groupedHomestays.add(homestays.subList(i, Math.min(i + groupSize, totalSize)));
        }

        model.addObject("groupedHomestays", groupedHomestays);
        return model;
    }

    @GetMapping(value = "/search")
    public ModelAndView searchPage(@RequestParam Map<String, Object> params,
                                   @RequestParam(required = false) List<Long> facilities,
                                   HttpSession session) {

        User user = (User) session.getAttribute("user");
        // Khởi tạo model và trả về view
        ModelAndView model = new ModelAndView("search");
        // Lấy các field đã chọn
        model.addObject("selectedFacilities", facilities != null ? facilities : new ArrayList<>());
        model.addObject("facilities", facilitiesService.findAll());

        String checkInDate = MapUtil.getObject(params, "checkinDate", String.class);
        String checkOutDate = MapUtil.getObject(params, "checkoutDate", String.class);

        // Thêm ngày check-in, check-out và danh sách homestays vào model
        model.addObject("checkInDate", checkInDate);
        model.addObject("checkOutDate", checkOutDate);
        model.addObject("params", params);

        List<HomestayResponseDTO> homestays = new ArrayList<>();
        try {
            Boolean checkDate = DateUtil.isValid(checkInDate, checkOutDate);
            homestays = homestayService.findByFilter(params, facilities);
        } catch (DateNotValidException e) {
            homestays = homestayService.findAll();  // Nếu có lỗi, lấy tất cả homestay
            model.addObject("errorMessage", e.getMessage());
        }
        model.addObject("homestays", homestays);  // Thêm homestay vào model
        return model;
    }

    @GetMapping(value = "/login")
    public ModelAndView loginPage(HttpSession session) {
        //model.addObject();
        return new ModelAndView("login");
    }

    @GetMapping(value = "/register")
    public ModelAndView registerPage(HttpSession session) {
        //model.addObject();
        return new ModelAndView("register");
    }

    @GetMapping(value = "/forget")
    public ModelAndView forgetPage() {
        ModelAndView model = new ModelAndView("forget");
        //model.addObject();
        return model;
    }

    @GetMapping(value = "/history")
    public ModelAndView historyPage() {
        ModelAndView model = new ModelAndView("history");
        //model.addObject();
        return model;
    }
}

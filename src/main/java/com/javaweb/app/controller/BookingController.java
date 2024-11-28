package com.javaweb.app.controller;

import com.javaweb.app.dto.BookingDTO;


import com.javaweb.app.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.javaweb.app.dto.HomestayResponseDTO;
import com.javaweb.app.entity.User;

import com.javaweb.app.service.HomestayService;
import com.javaweb.app.utils.DateUtil;
import com.javaweb.app.utils.MapUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;


@RestController
@RequestMapping("/booking")

public class BookingController {
    @Autowired
    public BookingService bookingService;
    @Autowired
    public HomestayService homestayService;

    @PostMapping("/form")
    public ModelAndView informBooking(@RequestParam Map<String, Object> params,
                                      HttpSession session) {
        LocalDate checkInDate = DateUtil.strToDate(MapUtil.getObject(params, "checkInDate", String.class));
        LocalDate checkOutDate = DateUtil.strToDate(MapUtil.getObject(params, "checkOutDate", String.class));
        Long stayDays = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        HomestayResponseDTO homestay = homestayService.findHomestayById(MapUtil.getObject(params, "homestayId", Long.class));

        User user = (User) session.getAttribute("user");

        ModelAndView modelAndView = new ModelAndView("booking");
        modelAndView.addObject("user", user);
        modelAndView.addObject("checkInDate", checkInDate);
        modelAndView.addObject("checkOutDate", checkOutDate);
        modelAndView.addObject("homestay", homestay);
        modelAndView.addObject("facilities", homestay.getFacilities());
        modelAndView.addObject("stayDays", stayDays);
        modelAndView.addObject("rent_price", stayDays * homestay.getPrice());
        return modelAndView;
    }
    @PostMapping("/invoice")
    public ModelAndView Booking(@RequestParam Map<String, Object> params,
                                HttpSession session) {
        User user = (User) session.getAttribute("user");
        BookingDTO bookingDTO = bookingService.createBooking(params, user.getId(), session);
        session.setAttribute("bookingTmp", bookingDTO);
        ModelAndView modelAndView = new ModelAndView("invoice");
        modelAndView.addObject("booking", bookingDTO);
        modelAndView.addObject("homestay", bookingDTO.getHomestay());
        return modelAndView;
    }
}

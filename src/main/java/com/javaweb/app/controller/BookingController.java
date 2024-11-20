package com.javaweb.app.controller;

import com.javaweb.app.dto.BookingDTO;
import com.javaweb.app.dto.HomestayDto;
import com.javaweb.app.dto.HomestayResponseDTO;
import com.javaweb.app.entity.BookingEntity;
import com.javaweb.app.entity.HomestayEntity;
import com.javaweb.app.repository.BookingRepository;
import com.javaweb.app.repository.HomestayRepository;
import com.javaweb.app.repository.UserRepository;
import com.javaweb.app.service.BookingService;
import com.javaweb.app.service.HomestayService;
import com.javaweb.app.utils.DateUtil;
import com.javaweb.app.utils.MapUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    public BookingService bookingService;
    @Autowired
    public HomestayService homestayService;

    @PostMapping("/create")
    public BookingDTO Booking(@RequestParam Map<String, Object> params,
                              HttpSession session) {
        BookingDTO bookingDTO = bookingService.createBooking(params);
        return null;
    }
    @PostMapping("/form")
    public ModelAndView informBooking(@RequestParam Map<String, Object> params,
                                      HttpSession session) {
        LocalDate checkInDate = DateUtil.strToDate(MapUtil.getObject(params, "checkInDate", String.class));
        LocalDate checkOutDate = DateUtil.strToDate(MapUtil.getObject(params, "checkOutDate", String.class));
        Long stayDays = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        HomestayResponseDTO homestay = homestayService.findHomestayById(MapUtil.getObject(params, "homestayId", Long.class));

        Long id = (Long) session.getAttribute("userId");
        ModelAndView modelAndView = new ModelAndView("booking");
        modelAndView.addObject("checkInDate", checkInDate);
        modelAndView.addObject("checkOutDate", checkOutDate);
        modelAndView.addObject("homestay", homestay);
        modelAndView.addObject("facilities", homestay.getFacilities());
        modelAndView.addObject("stayDays", stayDays);
        modelAndView.addObject("rent_price", stayDays * homestay.getPrice());
        return modelAndView;
    }
}

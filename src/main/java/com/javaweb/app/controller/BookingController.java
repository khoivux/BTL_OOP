package com.javaweb.app.controller;

import com.javaweb.app.dto.BookingDTO;

import com.javaweb.app.dto.HomestayDto;
import com.javaweb.app.dto.HomestayResponseDTO;
import com.javaweb.app.entity.BookingEntity;
import com.javaweb.app.entity.HomestayEntity;
import com.javaweb.app.entity.User;
import com.javaweb.app.exception.DateNotValidException;

import com.javaweb.app.service.BookingService;
import com.javaweb.app.service.HomestayService;
import com.javaweb.app.utils.DateUtil;
import com.javaweb.app.utils.MapUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
        String checkInDateStr = MapUtil.getObject(params, "checkInDate", String.class);
        String checkOutDateStr = MapUtil.getObject(params, "checkOutDate", String.class);
        HomestayResponseDTO homestay = homestayService.findHomestayById(MapUtil.getObject(params, "homestayId", Long.class));
        try {
            Boolean checkDate = DateUtil.isValid(checkInDateStr, checkOutDateStr);
            LocalDate checkInDate = DateUtil.strToDate(MapUtil.getObject(params, "checkInDate", String.class));
            LocalDate checkOutDate = DateUtil.strToDate(MapUtil.getObject(params, "checkOutDate", String.class));

            bookingService.validDateBooking(homestay.getId(), checkInDate, checkOutDate);

            Long stayDays = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
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
        catch (DateNotValidException e) {
            ModelAndView modelAndView = new ModelAndView("forward:/homestay/" + homestay.getId());
            modelAndView.addObject("homestay", homestay);
            modelAndView.addObject("facilities", homestay.getFacilities());
            modelAndView.addObject("rooms", homestay.getRooms());
            modelAndView.addObject("errorMessage", e.getMessage());
            return modelAndView;
        }
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
    @PostMapping("/save")
    public RedirectView Booking(HttpSession session) {
        BookingDTO bookingDTO = (BookingDTO) session.getAttribute("bookingTmp");
        bookingService.saveBooking(bookingDTO);
        return new RedirectView("/booking/history");
    }
}

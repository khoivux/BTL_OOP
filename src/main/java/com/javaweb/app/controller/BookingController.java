package com.javaweb.app.controller;

import com.javaweb.app.dto.BookingDTO;


import com.javaweb.app.dto.HomestayResponseDTO;
import com.javaweb.app.entity.User;
import com.javaweb.app.exception.DateNotValidException;
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

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
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
        modelAndView.addObject("deposit", ((Long)bookingDTO.getTotal() / 2));
        return modelAndView;
    }


    @GetMapping(value = "/history")
    public ModelAndView historyPage(HttpSession session) {
        ModelAndView model = new ModelAndView("history");
        User user = (User) session.getAttribute("user");
        List<BookingDTO> bookings = bookingService.findBookingByUserId(user.getId());
        model.addObject("bookings",bookings);
        return model;
    }

    @PostMapping("/history/booking-detail/{id}")
    public ModelAndView getBookingDetailApi(@PathVariable Long id,
                                        HttpSession session) {
        ModelAndView model = new ModelAndView("booking-detail");
        BookingDTO booking = bookingService.findById(id);
        model.addObject("booking",booking);
        return model;
    }

    @PostMapping("/history/booking-cancel/{id}")
    public RedirectView cancelBooking(@PathVariable Long id,
                                RedirectAttributes redirectAttributes,
                                HttpSession session) {
        bookingService.cancelBookingById(id);
        redirectAttributes.addFlashAttribute("message", "Hủy giao dịch thành công!");
        return new RedirectView("/booking/history"); // Quay về danh sách
    }
    @PostMapping("/save")
    public RedirectView Booking(HttpSession session) {
        BookingDTO bookingDTO = (BookingDTO) session.getAttribute("bookingTmp");
        bookingService.saveBooking(bookingDTO);
        return new RedirectView("/booking/history");
    }
    @PostMapping("/delete/{id}")
    public RedirectView deleteBooking(@PathVariable Long id,
                                      HttpSession session) {
        bookingService.deleteBookingById(id);
        return new RedirectView("/admin/user-paymenthistory/" + id);
    }
}

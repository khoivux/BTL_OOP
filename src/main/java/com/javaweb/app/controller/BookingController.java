package com.javaweb.app.controller;

import com.javaweb.app.dto.BookingDTO;
import com.javaweb.app.dto.HomestayResponseDTO;
import com.javaweb.app.entity.User;
import com.javaweb.app.service.BookingService;
import com.javaweb.app.service.HomestayService;
import com.javaweb.app.utils.DateUtil;
import com.javaweb.app.utils.MapUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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

//    @PostMapping("/create")
//    public BookingDTO Booking(@RequestParam Map<String, Object> params,
//                              HttpSession session) {
//        User user = (User) session.getAttribute("user");
//        int cnt = 0;
//        BookingDTO bookingDTO = bookingService.createBooking(params, user.getId());
//        return null;
//    }
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
}

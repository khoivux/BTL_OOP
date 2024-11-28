package com.javaweb.app.controller;


import com.javaweb.app.dto.HomestayCreateDTO;
import com.javaweb.app.dto.HomestayResponseDTO;
import com.javaweb.app.dto.HomestayDto;
import com.javaweb.app.exception.FileNotValidException;
import com.javaweb.app.repository.HomestayRepository;
import com.javaweb.app.service.BookingService;
import com.javaweb.app.service.HomestayService;
import com.javaweb.app.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RestController
@RequestMapping("/homestay")
public class HomestayController {
    @Autowired
    public HomestayService homestayService;
    @Autowired
    public HomestayRepository homestayRepository;
    @Autowired
    public UserService userService;
    @Autowired
    public BookingService bookingService;
    @PostMapping("/{id}")
    public ModelAndView getProductById(@PathVariable Long id,
                                       @RequestParam(required = false) Map<String, String> params,
                                       HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("product");

        modelAndView.addObject("checkInDate", params.get("checkinDate"));
        modelAndView.addObject("checkOutDate", params.get("checkoutDate"));

        HomestayResponseDTO homestayResponseDTO = homestayService.findHomestayById(id);
        modelAndView.addObject("homestay", homestayResponseDTO);
        modelAndView.addObject("facilities", homestayResponseDTO.getFacilities());
        modelAndView.addObject("rooms", homestayResponseDTO.getRooms());
        return modelAndView;
    }
}

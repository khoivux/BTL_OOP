package com.javaweb.app.mapper;

import com.javaweb.app.model.HomestaySearchRequest;

import com.javaweb.app.utils.MapUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
public class HomestayRequestMapper {
    public HomestaySearchRequest mapToHomestaySearchRequest(Map<String, Object> params) {
        LocalDate checkInDate = LocalDate.parse(MapUtil.getObject(params, "checkInDate", String.class), DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate checkOutDate = LocalDate.parse(MapUtil.getObject(params, "checkOutDate", String.class), DateTimeFormatter.ISO_LOCAL_DATE);
        return new HomestaySearchRequest.Builder()
                .setName(MapUtil.getObject(params, "name", String.class))
                .setAddress(MapUtil.getObject(params, "address", String.class))
                .setPriceFrom(MapUtil.getObject(params, "priceFrom", Long.class))
                .setPriceTo(MapUtil.getObject(params, "priceTo", Long.class))
                .setRating(MapUtil.getObject(params, "rating", Long.class))
                .setProvinceId(MapUtil.getObject(params, "provinceId", Long.class))
                .setCheckInDate(checkInDate)
                .setCheckOutDate(checkOutDate)
                .build();
    }
}

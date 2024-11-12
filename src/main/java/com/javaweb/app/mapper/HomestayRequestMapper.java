package com.javaweb.app.mapper;

import com.javaweb.app.dto.HomestaySearchRequestDTO;

import com.javaweb.app.utils.MapUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Component
public class HomestayRequestMapper {
    // Chuyển từ Param sang HomestaySearchRequest
    public HomestaySearchRequestDTO mapToHomestayRequest(Map<String, Object> params,
                                                         List<Long> facilitiess,
                                                         List<Long> rooms,
                                                         List<Long> services) {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        // Cho các LocalDate là null nếu Date nhận từ params là null hoặc rỗng
        String checkInDateStr = MapUtil.getObject(params, "checkInDate", String.class);
        String checkOutDateStr = MapUtil.getObject(params, "checkOutDate", String.class);

        LocalDate checkInDate = (checkInDateStr != null && !checkInDateStr.isEmpty())
                ? LocalDate.parse(checkInDateStr, formatter)
                : null;

        LocalDate checkOutDate = (checkOutDateStr != null && !checkOutDateStr.isEmpty())
                ? LocalDate.parse(checkOutDateStr, formatter)
                : null;

        return new HomestaySearchRequestDTO.Builder()
                .setId(MapUtil.getObject(params, "id", Long.class))
                .setName(MapUtil.getObject(params, "name", String.class))
                .setAddress(MapUtil.getObject(params, "address", String.class))
                .setPriceFrom(MapUtil.getObject(params, "priceFrom", Long.class))
                .setPriceTo(MapUtil.getObject(params, "priceTo", Long.class))
                .setRating(MapUtil.getObject(params, "rating", Long.class))
                .setProvinceId(MapUtil.getObject(params, "provinceId", Long.class))
                .setCheckInDate(checkInDate)
                .setCheckOutDate(checkOutDate)
                .setFacilities(facilitiess)
                .setRooms(rooms)
                .setServices(services)
                .build();
    }
}

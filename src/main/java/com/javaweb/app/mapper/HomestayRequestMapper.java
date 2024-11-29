package com.javaweb.app.mapper;

import com.javaweb.app.dto.HomestaySearchRequestDTO;

import com.javaweb.app.utils.DateUtil;
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
                                                         List<Long> facilitiess
                                                     ) {
        return new HomestaySearchRequestDTO.Builder()
                .id(MapUtil.getObject(params, "id", Long.class))
                .name(MapUtil.getObject(params, "name", String.class))
                .address(MapUtil.getObject(params, "address", String.class))
                .priceFrom(MapUtil.getObject(params, "priceFrom", Long.class))
                .priceTo(MapUtil.getObject(params, "priceTo", Long.class))
                .provinceId(MapUtil.getObject(params, "provinceId", Long.class))
                .checkInDate(DateUtil.strToDate(MapUtil.getObject(params, "checkinDate", String.class)))
                .checkOutDate(DateUtil.strToDate(MapUtil.getObject(params, "checkoutDate", String.class)))
                .capacity(MapUtil.getObject(params, "capacity", Long.class))
                .facilities(facilitiess)
                .build();
    }
}

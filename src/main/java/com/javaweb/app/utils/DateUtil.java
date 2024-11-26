package com.javaweb.app.utils;

import com.javaweb.app.exception.DateNotValidException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static LocalDate strToDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return (date != null && !date.isEmpty())
                ? LocalDate.parse(date, formatter)
                : null;
    }
    public static Boolean isValid(String checkInDateStr, String checkOutDateStr) {
            LocalDate checkInDate = strToDate(checkInDateStr);
            LocalDate checkOutDate = strToDate(checkOutDateStr);
            if(checkInDate == null && checkOutDate == null)
                return true;
            if((checkInDate == null && checkOutDate != null) || (checkOutDate == null && checkInDate != null))
                throw new DateNotValidException("Cần nhập cả ngày nhận và trả phòng!");
            if(checkOutDate.isBefore(checkInDate))
                throw new DateNotValidException("Ngày trả phòng cần phải sau ngày nhận phòng!");
            if(checkInDate.isBefore(LocalDate.now()))
                throw new DateNotValidException("Ngày nhận phòng không hợp lệ");
            return true;
    }
}

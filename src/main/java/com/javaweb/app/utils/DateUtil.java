package com.javaweb.app.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static LocalDate strToDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return (date != null && !date.isEmpty())
                ? LocalDate.parse(date, formatter)
                : null;
    }
    public static String isValid(String checkInDateStr, String checkOutDateStr) {
            LocalDate checkInDate = strToDate(checkInDateStr);
            LocalDate checkOutDate = strToDate(checkOutDateStr);
            if(checkInDate == null && checkOutDate == null)
                return null;
            if((checkInDate == null && checkOutDate != null) || (checkOutDate == null && checkInDate != null))
                throw new RuntimeException("Cần nhập cả ngày nhận và trả phòng!");
            if(checkOutDate.isBefore(checkInDate))
                throw new RuntimeException("Ngày trả phòng cần phải sau ngày nhận phòng!");
            return null;
    }
}

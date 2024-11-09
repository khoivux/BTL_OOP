package com.javaweb.app.repository.impl;

import com.javaweb.app.entity.HomestayEntity;
import com.javaweb.app.model.HomestaySearchRequest;
import com.javaweb.app.repository.HomestayRepositoryCustom;
import com.javaweb.app.utils.StringUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Primary
@PropertySource("classpath:application.properties")
public class HomestayRepositoryImpl implements HomestayRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;


    public static void joinTable(HomestaySearchRequest homestaySearchRequest,
                                 StringBuilder sql) {


        // Join vào bảng booking
        LocalDate checkIn = homestaySearchRequest.getCheckInDate();
        LocalDate checkOut = homestaySearchRequest.getCheckOutDate();
        if(checkIn != null && checkOut != null) {
            sql.append("LEFT JOIN booking ON h.id = booking.homestay_id \n");
        }

        // Join vào bảng tiện nghi homestay
        List<Long> homestayFacilities = homestaySearchRequest.getHomestayFacilities();
        if(homestayFacilities != null && !homestayFacilities.isEmpty()) {
            sql.append("JOIN homestay_facilities hfac ON h.id = hfac.homestay_id \n" +
                       "JOIN homestayfacilities fac ON hfac.facilities_id = fac.id \n");
        }

        List<Long> rooms = homestaySearchRequest.getRooms();
        if(rooms != null && !rooms.isEmpty()) {
            sql.append("JOIN homestay_room hr ON h.id = hr_id \n" +
                    "JOIN room ON hr.room_id = room.id \n");
        }

        String address = homestaySearchRequest.getAddress();
        if(StringUtil.isValid(address)) {
            sql.append(("JOIN province pro ON h.id = pro.homestay_id \n"));
        }

    }

    public static void queryNormal(HomestaySearchRequest homestaySearchRequest,
                                   StringBuilder where) {
        try {
            Field[] fields = HomestaySearchRequest.class.getDeclaredFields();
            // Lấy tất cả các field của request cho vào một mảng
            for (Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if (!fieldName.startsWith("price") && !fieldName.endsWith("Date")) {
                    Object value = item.get(homestaySearchRequest);
                    if (value != null && value != "") {
                        if (item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer")) {
                            where.append("AND h." + fieldName + " = " + value + "\n");
                        }
                        else if (item.getType().getName().equals("java.lang.String")) {
                            where.append("AND h." + fieldName + " like '%" + value + "%' \n");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void querySpecial(HomestaySearchRequest homestaySearchRequest,
                                    StringBuilder where) {

        // Tìm theo giá phòng
        Long priceTo = homestaySearchRequest.getPriceTo();
        Long priceFrom = homestaySearchRequest.getPriceFrom();

        if (priceTo != null)
            where.append("AND h.price <= " + priceTo + "\n");
        if (priceFrom != null)
            where.append("AND h.price >= " + priceFrom + "\n");

        // Kiểm tra ngày checkin checkout của các booking mỗi homestay
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate checkIn = homestaySearchRequest.getCheckInDate();
        LocalDate checkOut = homestaySearchRequest.getCheckOutDate();
        if(checkIn != null && checkOut != null) {
            where.append("AND ('" + checkIn.format(formatter) + "' > booking.checkout_date \n" +
                         "OR '" + checkOut.format(formatter) + "' < booking.checkin_date \n" +
                         "OR booking.homestay_id IS NULL) \n");
        }

        // Tìm theo tiện nghi Homestay
        List<Long> homestayFacilities = homestaySearchRequest.getHomestayFacilities();
        if(homestayFacilities != null && !homestayFacilities.isEmpty()) {
            where.append("AND fac.id IN ");
            String listId = homestayFacilities.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", ", "(", ")")); // Chuyển list về dạng (a, b, c)
            where.append(listId + "\n");
        }
        List<Long> rooms = homestaySearchRequest.getRooms();
        if(rooms != null && !rooms.isEmpty()) {
            where.append("AND room.id IN ");
            String listId = rooms.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", ", "(", ")")); // Chuyển list về dạng (a, b, c)
            where.append(listId + "\n");
        }

        String address = homestaySearchRequest.getAddress();
        if(StringUtil.isValid(address)) {
            where.append(("AND pro.name like '%" + address + "%'\n"));
        }
    }

    public List<HomestayEntity> findByFilter(HomestaySearchRequest homestaySearchRequest) {
        StringBuilder sql = new StringBuilder("SELECT h.* FROM homestay h \n");
        List<HomestayEntity> result = new ArrayList<>();
        StringBuilder where = new StringBuilder("WHERE 1 = 1 \n");

        // Xử lý Join Table
        joinTable(homestaySearchRequest,  sql);
        // Xử lý câu lệnh không cần Join Table, chỉ cần like/=
        queryNormal(homestaySearchRequest, where);
        // Xử lý câu lệnh đặc biệt như cần Join Tablle, cần <, >, IN, NOT IN,...
        querySpecial(homestaySearchRequest, where);
        sql.append(where).append("GROUP BY h.id");

        Query query = entityManager.createNativeQuery(sql.toString(), HomestayEntity.class);
        return query.getResultList();
    }
}

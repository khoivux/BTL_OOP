package com.javaweb.app.repository.impl;

import com.javaweb.app.entity.HomestayEntity;
import com.javaweb.app.dto.HomestaySearchRequestDTO;
import com.javaweb.app.repository.HomestayRepositoryCustom;
import com.javaweb.app.utils.StringUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
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


    public static void joinTable(HomestaySearchRequestDTO homestaySearchRequestDTO,
                                 StringBuilder sql) {


        // Join vào bảng booking
        LocalDate checkIn = homestaySearchRequestDTO.getCheckInDate();
        LocalDate checkOut = homestaySearchRequestDTO.getCheckOutDate();
        if(checkIn != null && checkOut != null) {
            sql.append("LEFT JOIN booking ON h.id = booking.homestay_id \n");
        }

        // Join vào bảng tiện nghi homestay
        List<Long> homestayFacilities = homestaySearchRequestDTO.getHomestayFacilities();
        if(homestayFacilities != null && !homestayFacilities.isEmpty()) {
            sql.append("JOIN homestay_facilities hfac ON h.id = hfac.homestay_id \n" +
                       "JOIN homestayfacilities fac ON hfac.facilities_id = fac.id \n");
        }

        String address = homestaySearchRequestDTO.getAddress();
        if(StringUtil.isValid(address)) {
            sql.append(("JOIN province pro ON h.province_id = pro.id \n"));
        }

    }

    public static void queryNormal(HomestaySearchRequestDTO homestaySearchRequestDTO,
                                   StringBuilder where) {
        try {
            Field[] fields = HomestaySearchRequestDTO.class.getDeclaredFields();
            // Lấy tất cả các field của request cho vào một mảng
            for (Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if (!fieldName.startsWith("price") && !fieldName.endsWith("Date") && !fieldName.startsWith("addre") && !fieldName.startsWith("capa")) {
                    Object value = item.get(homestaySearchRequestDTO);
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

    public static void querySpecial(HomestaySearchRequestDTO homestaySearchRequestDTO,
                                    StringBuilder where) {
        // Tìm theo số lượng
        Long capacity = homestaySearchRequestDTO.getCapacity();
        if(capacity != null) {
            where.append("AND h.capacity >= " + capacity + "\n");
        }

        // Tìm theo giá phòng
        Long priceTo = homestaySearchRequestDTO.getPriceTo();
        Long priceFrom = homestaySearchRequestDTO.getPriceFrom();

        if (priceTo != null)
            where.append("AND h.price <= " + priceTo + "\n");
        if (priceFrom != null)
            where.append("AND h.price >= " + priceFrom + "\n");

        // Kiểm tra ngày checkin checkout của các booking mỗi homestay
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate checkIn = homestaySearchRequestDTO.getCheckInDate();
        LocalDate checkOut = homestaySearchRequestDTO.getCheckOutDate();
        if (checkIn != null && checkOut != null) {
            where.append("AND NOT EXISTS ( \n" +
                    "    SELECT 1 \n" +
                    "    FROM booking b \n" +
                    "    WHERE b.homestay_id = h.id \n" +
                    "    AND b.status != 'Đã hủy'" +
                    "    AND b.status != 'Đã thanh toán'" +
                    "    AND NOT ( \n" +
                    "        '" + checkIn.format(formatter) + "' > b.checkout_date \n" +
                    "        OR '" + checkOut.format(formatter) + "' < b.checkin_date \n" +
                    "    ) \n" +
                    ") \n");
        }

        // Tìm theo tiện nghi Homestay
        List<Long> homestayFacilities = homestaySearchRequestDTO.getHomestayFacilities();
        if(homestayFacilities != null && !homestayFacilities.isEmpty()) {
            where.append("AND fac.id IN ");
            String listId = homestayFacilities.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", ", "(", ")")); // Chuyển list về dạng (a, b, c)
            where.append(listId + "\n");
        }

        String address = homestaySearchRequestDTO.getAddress();
        if(StringUtil.isValid(address)) {
            where.append(("AND ( pro.name like '%" + address + "%' \n" +
                           "OR h.address like '%" + address + "%') \n"));
        }
    }

    public List<HomestayEntity> findByFilter(HomestaySearchRequestDTO homestaySearchRequestDTO) {
        StringBuilder sql = new StringBuilder("SELECT h.* FROM homestay h \n");
        List<HomestayEntity> result = new ArrayList<>();
        StringBuilder where = new StringBuilder("WHERE 1 = 1 \n");

        // Xử lý Join Table
        joinTable(homestaySearchRequestDTO,  sql);
        // Xử lý câu lệnh không cần Join Table, chỉ cần like/=
        queryNormal(homestaySearchRequestDTO, where);
        // Xử lý câu lệnh đặc biệt như cần Join Tablle, cần <, >, IN, NOT IN,...
        querySpecial(homestaySearchRequestDTO, where);

        sql.append(where).append("GROUP BY h.id \n" +
                                "HAVING 1 = 1 \n");

        List<Long> homestayFacilities = homestaySearchRequestDTO.getHomestayFacilities();
        if(homestayFacilities != null && !homestayFacilities.isEmpty()) {
            sql.append("AND COUNT(DISTINCT fac.id) = " + homestayFacilities.size() + "\n");
        }

        Query query = entityManager.createNativeQuery(sql.toString(), HomestayEntity.class);
        return query.getResultList();
    }
}

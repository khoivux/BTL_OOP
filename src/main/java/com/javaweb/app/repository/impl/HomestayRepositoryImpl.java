package com.javaweb.app.repository.impl;

import com.javaweb.app.entity.HomestayEntity;
import com.javaweb.app.model.HomestaySearchRequest;
import com.javaweb.app.repository.HomestayRepositoryCustom;
import com.javaweb.app.utils.StringUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
@PropertySource("classpath:application.properties")
public class HomestayRepositoryImpl implements HomestayRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public static void joinTable(HomestaySearchRequest homestaySearchRequest, StringBuilder sql) {

    }

    public static void queryNormal(HomestaySearchRequest homestaySearchRequest, StringBuilder where) {
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
                            where.append(" AND h." + fieldName + " = " + value);
                        } else if (item.getType().getName().equals("java.lang.String")) {
                            where.append(" AND h." + fieldName + " like '%" + value + "%' ");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void querySpecial(HomestaySearchRequest homestaySearchRequest, StringBuilder where) {
        Long priceTo = homestaySearchRequest.getPriceTo();
        Long priceFrom = homestaySearchRequest.getPriceFrom();
        if (priceTo != null)
            where.append(" AND h.price <= " + priceTo);
        if (priceFrom != null)
            where.append(" AND h.price >= " + priceFrom);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String checkIn = homestaySearchRequest.getCheckInDate().format(formatter);
        String checkOut = homestaySearchRequest.getCheckOutDate().format(formatter);

        if(StringUtil.isValid(checkIn) && StringUtil.isValid(checkOut)) {
            where.append(" AND h.id NOT IN (" +
                         " SELECT b.homestay_id FROM booking b" +
                         " WHERE b.checkin_date < '" + checkIn +
                         "' AND b.checkout_date > '" + checkOut +"')");
        }
    }

    public List<HomestayEntity> findByFilter(HomestaySearchRequest homestaySearchRequest) {
        StringBuilder sql = new StringBuilder("SELECT h.* FROM homestay h ");
        List<HomestayEntity> result = new ArrayList<>();
        StringBuilder where = new StringBuilder(" WHERE 1 = 1");

        joinTable(homestaySearchRequest, sql);
        queryNormal(homestaySearchRequest, where);
        querySpecial(homestaySearchRequest, where);
        sql.append(where).append(" GROUP BY h.id");

        Query query = entityManager.createNativeQuery(sql.toString(), HomestayEntity.class);
        return query.getResultList();
    }
}

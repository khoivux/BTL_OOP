package com.javaweb.app.service.impl;

import com.javaweb.app.dto.BookingDTO;
import com.javaweb.app.entity.BookingEntity;
import com.javaweb.app.repository.BookingRepository;
import com.javaweb.app.repository.HomestayRepository;
import com.javaweb.app.repository.UserRepository;
import com.javaweb.app.service.BookingService;
import com.javaweb.app.mapper.BookingMapper;
import org.modelmapper.ModelMapper;
import com.javaweb.app.utils.DateUtil;
import com.javaweb.app.utils.MapUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Objects;

@Service // Thêm annotation @Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    // Inject BookingRepository và BookingMapper
    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }
    @Autowired
    public HomestayRepository homestayRepository;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public ModelMapper modelMapper;

    public BookingDTO createBooking(Map<String, Object> params,
                                    Long userId,
                                    HttpSession session) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setUser(userRepository.getById(userId));
        bookingDTO.setCustomerName(MapUtil.getObject(params, "customerName", String.class));
        bookingDTO.setCustomerEmail(MapUtil.getObject(params, "customerEmail", String.class));
        bookingDTO.setCustomerPhone(MapUtil.getObject(params, "customerPhone", String.class));
        bookingDTO.setHomestay(homestayRepository.getById(Objects.requireNonNull(MapUtil.getObject(params, "homestayId", Long.class))));
        bookingDTO.setStatus("Đang thực hiện");
        bookingDTO.setCheckInDate(DateUtil.strToDate(MapUtil.getObject(params, "checkInDate", String.class)));
        bookingDTO.setCheckOutDate(DateUtil.strToDate(MapUtil.getObject(params, "checkOutDate", String.class)));
        bookingDTO.setBookingTime(LocalDateTime.now());

        long daysBetween = ChronoUnit.DAYS.between(bookingDTO.getCheckInDate(), bookingDTO.getCheckOutDate());
        bookingDTO.setStayDuration(daysBetween);
        bookingDTO.setTotal(daysBetween * bookingDTO.getHomestay().getPrice());

        return bookingDTO;
    }


    @Override
    public List<BookingEntity> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUser_Id(userId);
    }

    @Override
    public List<BookingDTO> getPaymentHistory(Long userId) {
        // Lấy danh sách các BookingEntity từ DB
        int a=1;

        List<BookingEntity> bookings = bookingRepository.findByUser_Id(userId);
        if (bookings.isEmpty()) {
            return Collections.emptyList();
        }
        int cnt=0;
        // Chuyển đổi thành BookingDTO
        return bookings.stream()
                .map(bookingMapper::mapToBookingDTO) // Dùng BookingMapper để chuyển đổi
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public List<BookingEntity> getAllBookings() {
        return bookingRepository.findAll(); // Lấy tất cả booking từ cơ sở dữ liệu
    }

    public List<BookingDTO> getBookingsByUser_Id(Long userId) {
        List<BookingEntity> bookings = bookingRepository.findByUser_Id(userId);
        // Chuyển đổi BookingEntity thành BookingDTO
        return bookings.stream()
                .map(booking -> bookingMapper.mapToBookingDTO(booking)) // Giả sử bạn có mapper
                .collect(Collectors.toList());
    }

    @Override
    public Long getUserIdByBookingId(Long bookingId) {
        BookingEntity booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking != null) {
            return booking.getUser().getUserID();
        }
        return null;
    }
    @Override
    public BookingDTO findById(Long id)
    {
        BookingDTO bookingDTO = modelMapper.map(bookingRepository.getById(id), BookingDTO.class);
        return bookingDTO;
    }

    @Override
    public void updateBookingStatus(Long id, String status) {
        BookingEntity booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            booking.setStatus(status);
            bookingRepository.save(booking);
        }
    }
}

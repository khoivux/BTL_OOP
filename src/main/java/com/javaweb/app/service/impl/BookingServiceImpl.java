package com.javaweb.app.service.impl;

import com.javaweb.app.dto.BookingDTO;
import com.javaweb.app.entity.BookingEntity;
import com.javaweb.app.repository.BookingRepository;
import com.javaweb.app.repository.HomestayRepository;
import com.javaweb.app.repository.UserRepository;
import com.javaweb.app.service.BookingService;
import com.javaweb.app.mapper.BookingMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public BookingDTO createBooking(BookingDTO bookingDTO) {
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setUser(userRepository.getById(bookingDTO.getUser_id()));
        bookingEntity.setHomestay(homestayRepository.getById(bookingDTO.getHomestay_id()));
        bookingEntity.setStatus("Đã cọc");
        bookingEntity.setCheckInDate(bookingDTO.getCheckInDate());
        bookingEntity.setCheckOutDate(bookingDTO.getCheckOutDate());
        bookingEntity.setBookingTime(LocalDateTime.now());
        bookingRepository.save(bookingEntity);
        return bookingDTO;
    }

    @Override
    public List<BookingDTO> getBookingbyId(Long id) {
        return List.of();
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
                .map(bookingMapper::toDTO) // Dùng BookingMapper để chuyển đổi
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
    @Override
    public BookingDTO getBookingById(Long bookingId) {
        // Lấy booking theo id từ cơ sở dữ liệu
        Optional<BookingEntity> bookingOptional = bookingRepository.findById(bookingId);

        if (bookingOptional.isPresent()) {
            BookingEntity booking = bookingOptional.get();

            // Chuyển đổi BookingEntity thành BookingDTO
            return new BookingDTO(
                    booking.getId(),
                    booking.getUser().getUserEmail(),// Lấy email người dùng
                    booking.getUser().getUserPhone(),// Lấy số điện thoại người dùng
                    booking.getHomestay().getName(), // Lấy tên homestay
                    booking.getHomestay().getPrice(), // Lấy giá homestay
                    booking.getUser().getFullName(),// Lấy tên người dùng// Lấy email người dùng
                    booking.getCheckInDate(),
                    booking.getCheckOutDate(),
                    booking.getBookingTime(),
                    booking.getStatus());
        } else {
            // Trả về null hoặc thông báo lỗi nếu không tìm thấy booking
            return null;
        }
    }

    @Override
    public List<BookingEntity> getAllBookings() {
        return bookingRepository.findAll(); // Lấy tất cả booking từ cơ sở dữ liệu
    }
    public List<BookingDTO> getBookingsByUser_Id(Long userId) {
        List<BookingEntity> bookings = bookingRepository.findByUser_Id(userId);
        // Chuyển đổi BookingEntity thành BookingDTO
        return bookings.stream()
                .map(booking -> bookingMapper.toDTO(booking)) // Giả sử bạn có mapper
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
    public BookingDTO getBookingDetails(Long bookingId) {
        BookingEntity booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking == null) {
            return null;
        }
        return convertToDTO(booking);
    }
    private BookingDTO convertToDTO(BookingEntity bookingEntity) {
        BookingDTO dto = new BookingDTO();
        dto.setId(bookingEntity.getId());
        dto.setCheckInDate(bookingEntity.getCheckInDate());
        dto.setCheckOutDate(bookingEntity.getCheckOutDate());
        dto.setBookingTime(bookingEntity.getBookingTime());
        dto.setStatus(bookingEntity.getStatus());
        dto.setHomestayName(bookingEntity.getHomestay().getName());
        dto.setHomestayPrice(bookingEntity.getHomestay().getPrice());
        dto.setUserFullName(bookingEntity.getUser().getFullName());
        dto.setUserEmail(bookingEntity.getUser().getUserEmail());
        dto.setUserPhone(bookingEntity.getUser().getUserPhone());

        // Map thêm các trường dữ liệu khác nếu cần
        return dto;
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

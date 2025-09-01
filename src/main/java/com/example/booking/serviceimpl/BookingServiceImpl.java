package com.example.booking.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.booking.dto.BookingDTO;
import com.example.booking.entity.Booking;
import com.example.booking.mapper.BookingMapper;
import com.example.booking.repository.BookingRepository;
import com.example.booking.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingMapper bookingMapper;

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Booking booking = bookingMapper.toBooking(bookingDTO);
        Booking saved = bookingRepository.save(booking);
        return bookingMapper.toBookingDTO(saved);
    }

    @Override
    public BookingDTO updateBooking(BookingDTO bookingDTO) {
        if (bookingDTO.getBookingId() == null) {
            throw new IllegalArgumentException("Booking ID cannot be null for update");
        }
        Booking booking = bookingMapper.toBooking(bookingDTO);
        Booking updated = bookingRepository.save(booking);
        return bookingMapper.toBookingDTO(updated);
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookingMapper.toBookingDTOList(bookings);
    }

    @Override
    public BookingDTO getBookingById(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));
        return bookingMapper.toBookingDTO(booking);
    }

    @Override
    public void deleteBooking(Long bookingId) {
        if (!bookingRepository.existsById(bookingId)) {
            throw new RuntimeException("Booking not found with id: " + bookingId);
        }
        bookingRepository.deleteById(bookingId);
    }
}

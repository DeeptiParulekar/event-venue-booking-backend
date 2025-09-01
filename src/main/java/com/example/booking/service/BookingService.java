package com.example.booking.service;

import java.util.List;

import com.example.booking.dto.BookingDTO;

public interface BookingService {

	BookingDTO createBooking(BookingDTO bookingDTO);

	BookingDTO updateBooking(BookingDTO bookingDTO);

	List<BookingDTO> getAllBookings();

	BookingDTO getBookingById(Long bookingId);

	void deleteBooking(Long bookingId);

}

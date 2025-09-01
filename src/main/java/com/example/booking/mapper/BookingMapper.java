package com.example.booking.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.booking.dto.BookingDTO;
import com.example.booking.entity.Booking;
import com.example.booking.entity.User;
import com.example.booking.entity.Venue;

@Component
public class BookingMapper {

	// DTO -> Entity
	public Booking toBooking(BookingDTO dto) {
		if (dto == null)
			return null;

		Booking booking = new Booking();
		booking.setBookingId(dto.getBookingId());

		// map user & venue by only id
		User user = new User();
		user.setUserId(dto.getUserId());
		booking.setUser(user);

		Venue venue = new Venue();
		venue.setVenueId(dto.getVenueId());
		booking.setVenue(venue);

		booking.setBookingDate(dto.getBookingDate());
		booking.setStatus(dto.getStatus());
		booking.setTotalAmount(dto.getTotalAmount());
		return booking;
	}

	// Entity -> DTO
	public BookingDTO toBookingDTO(Booking booking) {
		if (booking == null)
			return null;

		BookingDTO dto = new BookingDTO();
		dto.setBookingId(booking.getBookingId());
		dto.setUserId(booking.getUser() != null ? booking.getUser().getUserId() : null);
		dto.setVenueId(booking.getVenue() != null ? booking.getVenue().getVenueId() : null);
		dto.setBookingDate(booking.getBookingDate());
		dto.setStatus(booking.getStatus());
		dto.setTotalAmount(booking.getTotalAmount());
		return dto;
	}

	// List<Entity> -> List<DTO>
	public List<BookingDTO> toBookingDTOList(List<Booking> bookings) {
		List<BookingDTO> dtos = new ArrayList<>();
		for (Booking booking : bookings) {
			dtos.add(toBookingDTO(booking));
		}
		return dtos;
	}

	// List<DTO> -> List<Entity>
	public List<Booking> toBookingList(List<BookingDTO> dtos) {
		List<Booking> entities = new ArrayList<>();
		for (BookingDTO dto : dtos) {
			entities.add(toBooking(dto));
		}
		return entities;
	}
}

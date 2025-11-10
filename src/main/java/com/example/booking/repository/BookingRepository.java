package com.example.booking.repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.booking.entity.Booking;
import com.example.booking.entity.Venue;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

//	@Query("select b from Booking b where b.bookingDate >= :from order by b.bookingDate desc")
//	List<Booking> findUpcoming(@Param("from") Date from);
//
//	boolean existsByVenueAndBookingDate(Venue venue, Date bookingDate);
//
//	List<Booking> findByUser_Email(String email);

	// Fetch upcoming bookings from a specific date
//	@Query("select b from Booking b where b.bookingDate >= :from order by b.bookingDate desc")
//	List<Booking> findUpcoming(@Param("from") LocalDate from);
//
//	// Find bookings by user email
//	List<Booking> findByUser_Email(String email);
//
//	// Find booking by venue name and booking date
//	Optional<Booking> findByVenue_VenueNameAndBookingDate(String venueName, LocalDate bookingDate);
//
//	// Check if a venue is booked on a specific date
//	boolean existsByVenueAndBookingDate(Venue venue, LocalDate bookingDate);
//
//	Collection<Venue> findByBookingDate(LocalDate date);

	
	 // Fetch upcoming bookings from a specific date
    @Query("select b from Booking b where b.bookingDate >= :from order by b.bookingDate desc")
    List<Booking> findUpcoming(@Param("from") LocalDate from);

    // Find bookings by user email
    List<Booking> findByUser_Email(String email);

    // Find booking by venue name and booking date
    Optional<Booking> findByVenue_VenueNameAndBookingDate(String venueName, LocalDate bookingDate);

    // Check if a venue is booked on a specific date
    boolean existsByVenueAndBookingDate(Venue venue, LocalDate bookingDate);

    // ✅ Corrected method: fetch bookings by date (not Venue directly)
    List<Booking> findByBookingDate(LocalDate bookingDate);
}

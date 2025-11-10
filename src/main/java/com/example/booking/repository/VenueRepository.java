package com.example.booking.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.booking.entity.Venue;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {

	@Query("SELECT v FROM Venue v WHERE LOWER(TRIM(v.venueName)) = LOWER(TRIM(:name))")
	Optional<Venue> findByNameIgnoreCaseTrimmed(@Param("name") String name);

	@Query(value = "SELECT COUNT(*) FROM eventbooking.venue", nativeQuery = true)
	long countAllVenues();

	// Example logic: venue is available if NOT already booked for that date
	@Query("SELECT v FROM Venue v WHERE v.id NOT IN "
			+ "(SELECT b.venue.id FROM Booking b WHERE b.bookingDate = :date)")
	List<Venue> findAvailableVenues(LocalDate date);

	 // Return Optional<Venue> instead of Object
    Optional<Venue> findByVenueName(String venueName);

	Optional<Venue> findByVenueNameIgnoreCase(String venueName);

}

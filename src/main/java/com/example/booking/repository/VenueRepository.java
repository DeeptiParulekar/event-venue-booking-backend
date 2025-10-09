package com.example.booking.repository;

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

}

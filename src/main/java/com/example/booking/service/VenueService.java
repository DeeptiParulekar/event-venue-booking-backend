package com.example.booking.service;

import java.util.List;

import com.example.booking.dto.VenueDTO;

public interface VenueService {

	VenueDTO createVenue(VenueDTO venueDTO);

	VenueDTO updateVenue(VenueDTO venueDTO);

	List<VenueDTO> getAllVenues();

	VenueDTO getVenueById(Long venueId);

	void deleteVenue(Long venueId);


}

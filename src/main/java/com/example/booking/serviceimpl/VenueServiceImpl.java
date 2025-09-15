package com.example.booking.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.booking.dto.VenueDTO;
import com.example.booking.entity.Venue;
import com.example.booking.mapper.VenueMapper;
import com.example.booking.repository.VenueRepository;
import com.example.booking.service.VenueService;

@Service
public class VenueServiceImpl implements VenueService {

	@Autowired
	private VenueRepository venueRepository;

	@Autowired
	private VenueMapper venueMapper;

	@Override
	public VenueDTO createVenue(VenueDTO venueDTO) {
		Venue venue = venueMapper.toVenue(venueDTO);
		Venue savedVenue = venueRepository.save(venue);
		return venueMapper.toVenueDTO(savedVenue);
	}

	@Override
	public VenueDTO updateVenue(VenueDTO venueDTO) {
		Optional<Venue> existingVenueOpt = venueRepository.findById(venueDTO.getVenueId());
		if (existingVenueOpt.isPresent()) {
			Venue existingVenue = existingVenueOpt.get();
			existingVenue.setVenueName(venueDTO.getVenueName());
			existingVenue.setType(venueDTO.getType());
			existingVenue.setAddress(venueDTO.getAddress());
			existingVenue.setCapacity(venueDTO.getCapacity());
			existingVenue.setPricePerDay(venueDTO.getPricePerDay());
			existingVenue.setCity(venueDTO.getCity());
			existingVenue.setContactpersonName(venueDTO.getContactpersonName());
			existingVenue.setContactpersonNumber(venueDTO.getContactpersonNumber());
			existingVenue.setPincode(venueDTO.getPincode());
			existingVenue.setState(venueDTO.getState());

			Venue updatedVenue = venueRepository.save(existingVenue);
			return venueMapper.toVenueDTO(updatedVenue);
		}
		return null; // Or throw custom exception if not found
	}

	@Override
	public List<VenueDTO> getAllVenues() {
		List<Venue> venues = venueRepository.findAll();
		return venueMapper.toVenueDTOList(venues);
	}

	@Override
	public VenueDTO getVenueById(Long venueId) {
		Optional<Venue> venueOpt = venueRepository.findById(venueId);
		return venueOpt.map(venueMapper::toVenueDTO).orElse(null);
	}

	@Override
	public void deleteVenue(Long venueId) {
		if (venueRepository.existsById(venueId)) {
			venueRepository.deleteById(venueId);
		} else {
			throw new RuntimeException("Venue not found with id: " + venueId);
		}
	}
}

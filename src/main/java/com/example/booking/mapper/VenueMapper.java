package com.example.booking.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.booking.dto.VenueDTO;
import com.example.booking.entity.Venue;

@Component
public class VenueMapper {

    // DTO -> Entity
    public Venue toVenue(VenueDTO dto) {
        if (dto == null)
            return null;

        Venue venue = new Venue();
        venue.setVenueId(dto.getVenueId());
        venue.setVenueName(dto.getVenueName());
        venue.setType(dto.getType());
        venue.setAddress(dto.getAddress());
        venue.setCapacity(dto.getCapacity());
        venue.setPricePerDay(dto.getPricePerDay());
        venue.setContactpersonName(dto.getContactpersonName());
        venue.setContactpersonNumber(dto.getContactpersonNumber());
        venue.setCity(dto.getCity());
        venue.setState(dto.getState());
        venue.setPincode(dto.getPincode());
        return venue;
    }

    // Entity -> DTO
    public VenueDTO toVenueDTO(Venue venue) {
        if (venue == null)
            return null;

        VenueDTO dto = new VenueDTO();
        dto.setVenueId(venue.getVenueId());
        dto.setVenueName(venue.getVenueName());
        dto.setType(venue.getType());
        dto.setAddress(venue.getAddress());
        dto.setCapacity(venue.getCapacity());
        dto.setPricePerDay(venue.getPricePerDay());
        dto.setContactpersonName(venue.getContactpersonName());
        dto.setContactpersonNumber(venue.getContactpersonNumber());
        dto.setCity(venue.getCity());
        dto.setState(venue.getState());
        dto.setPincode(venue.getPincode());
        return dto;
    }

    // List<Entity> -> List<DTO>
    public List<VenueDTO> toVenueDTOList(List<Venue> venues) {
        List<VenueDTO> dtos = new ArrayList<>();
        if (venues != null) {
            for (Venue venue : venues) {
                dtos.add(toVenueDTO(venue));
            }
        }
        return dtos;
    }

    // List<DTO> -> List<Entity>
    public List<Venue> toVenueList(List<VenueDTO> dtos) {
        List<Venue> entities = new ArrayList<>();
        if (dtos != null) {
            for (VenueDTO dto : dtos) {
                entities.add(toVenue(dto));
            }
        }
        return entities;
    }
}

package com.example.booking.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.booking.dto.VenueDTO;
import com.example.booking.service.VenueService;
import com.example.booking.payloads.ErrorDetails; // make sure you have this class

@RestController
@RequestMapping("/api/venues")
@CrossOrigin(origins = "http://localhost:3000")
public class VenueController {

    @Autowired
    private VenueService venueService;

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<?> createVenue(@RequestBody VenueDTO venueDTO) {
        try {
            VenueDTO created = venueService.createVenue(venueDTO);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDetails(new Date(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update", produces = "application/json")
    public ResponseEntity<?> updateVenue(@RequestParam Long venueId, @RequestBody VenueDTO venueDTO) {
        try {
            venueDTO.setVenueId(venueId); // ensure venueId is set
            VenueDTO updated = venueService.updateVenue(venueDTO);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDetails(new Date(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    public ResponseEntity<?> getAllVenues() {
        try {
            List<VenueDTO> list = venueService.getAllVenues();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDetails(new Date(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getById", produces = "application/json")
    public ResponseEntity<?> getVenueById(@RequestParam Long venueId) {
        try {
            VenueDTO dto = venueService.getVenueById(venueId);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDetails(new Date(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/delete", produces = "application/json")
    public ResponseEntity<?> deleteVenue(@RequestParam Long venueId) {
        try {
            venueService.deleteVenue(venueId);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Deleted Successfully");
            response.put("deleted venueId", venueId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Not Deleted, there is some error");
            errorResponse.put("error", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

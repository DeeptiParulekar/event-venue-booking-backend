package com.example.booking.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking.dto.BookingDTO;
import com.example.booking.payloads.ErrorDetails;
import com.example.booking.service.BookingService;

@RestController
@RequestMapping("api/booking")
@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping(value = "/createBooking", produces = "application/json")
	public ResponseEntity<?> createBooking(@RequestBody BookingDTO bookingDTO) {
		try {
			BookingDTO created = bookingService.createBooking(bookingDTO);
			return new ResponseEntity<>(created, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorDetails(new Date(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/updateBooking", produces = "application/json")
	public ResponseEntity<?> updateBooking(@RequestParam Long bookingId, @RequestBody BookingDTO bookingDTO) {
		try {
			BookingDTO updated = bookingService.updateBooking(bookingDTO); // Or call update if you have it
			return new ResponseEntity<>(updated, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorDetails(new Date(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/getAllBookings", produces = "application/json")
	public ResponseEntity<?> getAllBookings() {
		try {
			List<BookingDTO> list = bookingService.getAllBookings();
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorDetails(new Date(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getBookingById", produces = "application/json")
	public ResponseEntity<?> getBookingById(@RequestParam Long bookingId) {
		try {
			BookingDTO dto = bookingService.getBookingById(bookingId);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorDetails(new Date(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/deleteBooking", produces = "application/json")
	public ResponseEntity<?> deleteBooking(@RequestParam Long bookingId) {
		try {
			bookingService.deleteBooking(bookingId); // implement this in service
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Deleted Successfully");
			response.put("deleted bookingId", bookingId);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("message", "Not Deleted, there is some error");
			errorResponse.put("error", e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

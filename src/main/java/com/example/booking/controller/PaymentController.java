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

import com.example.booking.dto.PaymentDTO;
import com.example.booking.service.PaymentService;
import com.example.booking.payloads.ErrorDetails;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping(value = "/create", produces = "application/json")
	public ResponseEntity<?> createPayment(@RequestBody PaymentDTO paymentDTO) {
		try {
			PaymentDTO created = paymentService.createPayment(paymentDTO);
			return new ResponseEntity<>(created, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorDetails(new Date(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/update", produces = "application/json")
	public ResponseEntity<?> updatePayment(@RequestParam Long paymentId, @RequestBody PaymentDTO paymentDTO) {
		try {
			PaymentDTO updated = paymentService.updatePayment(paymentId, paymentDTO);
			return new ResponseEntity<>(updated, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorDetails(new Date(), e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/getAll", produces = "application/json")
	public ResponseEntity<?> getAllPayments() {
		try {
			List<PaymentDTO> list = paymentService.getAllPayments();
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorDetails(new Date(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getById", produces = "application/json")
	public ResponseEntity<?> getPaymentById(@RequestParam Long paymentId) {
		try {
			PaymentDTO dto = paymentService.getPaymentById(paymentId);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorDetails(new Date(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/delete", produces = "application/json")
	public ResponseEntity<?> deletePayment(@RequestParam Long paymentId) {
		try {
			paymentService.deletePayment(paymentId);
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Deleted Successfully");
			response.put("deleted paymentId", paymentId);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("message", "Not Deleted, there is some error");
			errorResponse.put("error", e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

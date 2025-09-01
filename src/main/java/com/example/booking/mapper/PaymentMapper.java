package com.example.booking.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.booking.dto.PaymentDTO;
import com.example.booking.entity.Booking;
import com.example.booking.entity.Payment;

@Component
public class PaymentMapper {

	// DTO -> Entity
	public Payment toPayment(PaymentDTO dto) {
		if (dto == null)
			return null;

		Payment payment = new Payment();
		payment.setPaymentId(dto.getPaymentId());

		// Only set booking by ID (avoid recursion)
		if (dto.getBookingId() != null) {
			Booking booking = new Booking();
			booking.setBookingId(dto.getBookingId());
			payment.setBooking(booking);
		}

		payment.setAmount(dto.getAmount());
		payment.setPaymentMethod(dto.getPaymentMethod());
		payment.setPaymentStatus(dto.getPaymentStatus());
		payment.setPaymentDate(dto.getPaymentDate());

		return payment;
	}

	// Entity -> DTO
	public PaymentDTO toPaymentDTO(Payment payment) {
		if (payment == null)
			return null;

		PaymentDTO dto = new PaymentDTO();
		dto.setPaymentId(payment.getPaymentId());
		dto.setBookingId(payment.getBooking() != null ? payment.getBooking().getBookingId() : null);
		dto.setAmount(payment.getAmount());
		dto.setPaymentMethod(payment.getPaymentMethod());
		dto.setPaymentStatus(payment.getPaymentStatus());
		dto.setPaymentDate(payment.getPaymentDate());

		return dto;
	}

	// List<Entity> -> List<DTO>
	public List<PaymentDTO> toPaymentDTOList(List<Payment> payments) {
		List<PaymentDTO> dtos = new ArrayList<>();
		for (Payment payment : payments) {
			dtos.add(toPaymentDTO(payment));
		}
		return dtos;
	}

	// List<DTO> -> List<Entity>
	public List<Payment> toPaymentList(List<PaymentDTO> dtos) {
		List<Payment> entities = new ArrayList<>();
		for (PaymentDTO dto : dtos) {
			entities.add(toPayment(dto));
		}
		return entities;
	}
}

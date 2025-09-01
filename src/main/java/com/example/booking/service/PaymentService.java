package com.example.booking.service;

import java.util.List;

import com.example.booking.dto.PaymentDTO;

public interface PaymentService {

	PaymentDTO createPayment(PaymentDTO paymentDTO);

	PaymentDTO updatePayment(Long paymentId, PaymentDTO paymentDTO);

	List<PaymentDTO> getAllPayments();

	PaymentDTO getPaymentById(Long paymentId);

	void deletePayment(Long paymentId);

}

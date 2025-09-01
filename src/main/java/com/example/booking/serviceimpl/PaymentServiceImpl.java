package com.example.booking.serviceimpl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.booking.dto.PaymentDTO;
import com.example.booking.entity.Payment;
import com.example.booking.mapper.PaymentMapper;
import com.example.booking.repository.PaymentRepository;
import com.example.booking.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public PaymentDTO createPayment(PaymentDTO dto) {
        Payment payment = paymentMapper.toPayment(dto);
        Payment saved = paymentRepository.save(payment);
        return paymentMapper.toPaymentDTO(saved);
    }

    @Override
    public PaymentDTO updatePayment(Long paymentId, PaymentDTO dto) {
        Optional<Payment> optional = paymentRepository.findById(paymentId);
        if (optional.isPresent()) {
            Payment payment = optional.get();
            payment.setAmount(dto.getAmount());
            payment.setPaymentMethod(dto.getPaymentMethod());
            payment.setPaymentStatus(dto.getPaymentStatus());
            payment.setPaymentDate(dto.getPaymentDate());
            Payment updated = paymentRepository.save(payment);
            return paymentMapper.toPaymentDTO(updated);
        }
        throw new RuntimeException("Payment not found with id " + paymentId);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return paymentMapper.toPaymentDTOList(payments);
    }

    @Override
    public PaymentDTO getPaymentById(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found with id " + paymentId));
        return paymentMapper.toPaymentDTO(payment);
    }

    @Override
    public void deletePayment(Long paymentId) {
        if (!paymentRepository.existsById(paymentId)) {
            throw new RuntimeException("Payment not found with id " + paymentId);
        }
        paymentRepository.deleteById(paymentId);
    }
}

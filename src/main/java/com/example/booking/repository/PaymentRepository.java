package com.example.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.booking.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	@Query("select coalesce(sum(p.amount), 0) from Payment p where p.paymentStatus = 'SUCCESS'")
	Double totalRevenue();
}

package com.example.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.booking.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

//	@Query("select coalesce(sum(p.amount), 0) from Payment p where p.paymentStatus = 'SUCCESS'")
//	Double totalRevenue();

//	@Query(value = "SELECT COALESCE(SUM(amount),0) FROM payment WHERE payment_status='SUCCESS' AND user_email=:email", nativeQuery = true)
//	Double totalRevenueByUser(@Param("email") String email);

	// Total revenue for all users
	@Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE p.paymentStatus = 'SUCCESS'")
	Double totalRevenue();

	// Total revenue for a specific user using booking.userEmail
//	@Query("SELECT COALESCE(SUM(p.amount),0) " + "FROM Payment p "
//			+ "WHERE p.paymentStatus = 'SUCCESS' AND p.booking.userEmail = :email")
//	Double totalRevenueByUser(@Param("email") String email);
//	
	@Query("SELECT COALESCE(SUM(p.amount),0) FROM Payment p WHERE p.paymentStatus = 'SUCCESS' AND p.booking.user.email = :email")
	Double totalRevenueByUser(@Param("email") String email);


}

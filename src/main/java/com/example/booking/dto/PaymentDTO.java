package com.example.booking.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "paymentId", "bookingId", "venueId", "bookingDate", "status" })
public class PaymentDTO {

	@JsonProperty("paymentId")
	private long paymentId;

	@JsonProperty("bookingId")
	private Long bookingId;

	@JsonProperty("amount")
	private double amount;

	@JsonProperty("paymentMethod")
	private String paymentMethod; // CARD, UPI, NETBANKING

	@JsonProperty("paymentStatus")
	private String paymentStatus; // SUCCESS, FAILED, REFUNDED

	@JsonProperty("paymentDate")
	private Date paymentDate;

	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

}

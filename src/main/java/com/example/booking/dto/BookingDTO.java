package com.example.booking.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "bookingId", "userId", "userName", "venueId", "venueName", "bookingDate", "status", "totalAmount",
		"userEmail" })
public class BookingDTO {

	@JsonProperty("bookingId")
	private Long bookingId;

	@JsonProperty("userId")
	private long userId;

	@JsonProperty("userName")
	private String userName;

//	@JsonProperty("venueId")
//	private long venueId;

	@JsonProperty("venueName")
	private String venueName;

	@JsonProperty("bookingDate")
	private Date bookingDate;

	@JsonProperty("status")
	private String status; // PENDING, CONFIRMED, CANCELLED

	@JsonProperty("totalAmount")
	private double totalAmount;

	@JsonProperty("userEmail")
	private String userEmail;

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

//	public long getVenueId() {
//		return venueId;
//	}
//
//	public void setVenueId(long venueId) {
//		this.venueId = venueId;
//	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}

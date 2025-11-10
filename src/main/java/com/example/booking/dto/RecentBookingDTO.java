//package com.example.booking.dto;
//
//import java.time.LocalDate;
//import java.util.Date;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;
//
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder({ "bookingId", "userEmail", "venueName", "bookingDate", "status" })
//public class RecentBookingDTO {
//
//	@JsonProperty("bookingId")
//	private Long bookingId;
//
//	@JsonProperty("userEmail")
//	private String userEmail;
//
//	@JsonProperty("venueName")
//	private String venueName;
//
//	@JsonProperty("bookingDate")
//	private LocalDate bookingDate;
//
//	@JsonProperty("status")
//	private String status;
//
//	public Long getBookingId() {
//		return bookingId;
//	}
//
//	public void setBookingId(Long bookingId) {
//		this.bookingId = bookingId;
//	}
//
//	public String getUserEmail() {
//		return userEmail;
//	}
//
//	public void setUserEmail(String userEmail) {
//		this.userEmail = userEmail;
//	}
//
//	public String getVenueName() {
//		return venueName;
//	}
//
//	public void setVenueName(String venueName) {
//		this.venueName = venueName;
//	}
//
//	public LocalDate getBookingDate() {
//		return bookingDate;
//	}
//
//	public void setBookingDate(LocalDate bookingDate) {
//		this.bookingDate = bookingDate;
//	}
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//}

package com.example.booking.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "bookingId", "userEmail", "venueName", "bookingDate", "status", "totalAmount" })
public class RecentBookingDTO {

	@JsonProperty("bookingId")
	private Long bookingId;

	@JsonProperty("userEmail")
	private String userEmail;

	@JsonProperty("venueName")
	private String venueName;

	@JsonProperty("bookingDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // ✅ ensures proper date format in JSON
	private LocalDate bookingDate;

	@JsonProperty("status")
	private String status;

	@JsonProperty("totalAmount")
	private double totalAmount;

	// ✅ No-arg constructor (needed for JSON serialization/deserialization)
	public RecentBookingDTO() {
	}

	// ✅ Constructor used in your stream map call
	public RecentBookingDTO(Long bookingId, String userEmail, String venueName, LocalDate bookingDate, String status,
			double totalAmount) {
		this.bookingId = bookingId;
		this.userEmail = userEmail;
		this.venueName = venueName;
		this.bookingDate = bookingDate;
		this.status = status;
		this.totalAmount = totalAmount;
	}

	// ✅ Getters and Setters
	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
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

}

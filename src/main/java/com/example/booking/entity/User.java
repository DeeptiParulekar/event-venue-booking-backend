package com.example.booking.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.booking.audit.Auditable;

@Entity
@Table(name = "users", schema = "eventbooking")
public class User extends Auditable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private Long userId;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "role")
	private String role; // CUSTOMER, ADMIN

	@Column(name = "resetToken")
	private String resetToken;

	@Column(name = "reset_token_expiry")
	@Temporal(TemporalType.TIMESTAMP)
	private Date resetTokenExpiry;

	@Column(name = "userName")
	private String userName;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public Date getResetTokenExpiry() {
		return resetTokenExpiry;
	}

	public void setResetTokenExpiry(Date resetTokenExpiry) {
		this.resetTokenExpiry = resetTokenExpiry;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}

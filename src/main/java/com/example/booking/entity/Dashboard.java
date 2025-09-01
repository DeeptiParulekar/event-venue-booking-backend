package com.example.booking.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.booking.audit.Auditable;

@Entity
@Table(name = "dashboard", schema = "eventbooking")
public class Dashboard extends Auditable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dashboardId")
	private Long dashboardId;

	@Column(name = "totalVenues")
	private long totalVenues;

	@Column(name = "totalBookings")
	private long totalBookings;

	@Column(name = "upcomingBookings")
	private long upcomingBookings;

	@Column(name = "revenue")
	private double revenue;

	@Column(name = "calculatedAt")
	private Date calculatedAt; // when metrics were generated

	public Long getDashboardId() {
		return dashboardId;
	}

	public void setDashboardId(Long dashboardId) {
		this.dashboardId = dashboardId;
	}

	public long getTotalVenues() {
		return totalVenues;
	}

	public void setTotalVenues(long totalVenues) {
		this.totalVenues = totalVenues;
	}

	public long getTotalBookings() {
		return totalBookings;
	}

	public void setTotalBookings(long totalBookings) {
		this.totalBookings = totalBookings;
	}

	public long getUpcomingBookings() {
		return upcomingBookings;
	}

	public void setUpcomingBookings(long upcomingBookings) {
		this.upcomingBookings = upcomingBookings;
	}

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}

	public Date getCalculatedAt() {
		return calculatedAt;
	}

	public void setCalculatedAt(Date calculatedAt) {
		this.calculatedAt = calculatedAt;
	}

}

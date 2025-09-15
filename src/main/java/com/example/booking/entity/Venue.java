package com.example.booking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.booking.audit.Auditable;

@Entity
@Table(name = "venue", schema = "eventbooking")
public class Venue extends Auditable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "venueId")
	private Long venueId;

	@Column(name = "venueName")
	private String venueName;

	@Column(name = "type")
	private String type; // HALL, RESORT, CATERER, DECORATOR

	@Column(name = "address")
	private String address;

	@Column(name = "capacity")
	private int capacity;

	@Column(name = "pricePerDay")
	private double pricePerDay;

	@Column(name = "contactpersonName")
	private String contactpersonName;

	@Column(name = "contactpersonNumber")
	private String contactpersonNumber;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "pincode")
	private String pincode;

	public Long getVenueId() {
		return venueId;
	}

	public void setVenueId(Long venueId) {
		this.venueId = venueId;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public String getContactpersonName() {
		return contactpersonName;
	}

	public void setContactpersonName(String contactpersonName) {
		this.contactpersonName = contactpersonName;
	}

	public String getContactpersonNumber() {
		return contactpersonNumber;
	}

	public void setContactpersonNumber(String contactpersonNumber) {
		this.contactpersonNumber = contactpersonNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

}

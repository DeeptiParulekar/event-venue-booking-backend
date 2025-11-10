package com.example.booking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "venueId", "venueName", "type", "address", "capacity", "pricePerDay", "contactpersonName",
		"contactpersonNumber", "city", "state", "pincode", "location" })
public class VenueDTO {

	@JsonProperty("venueId")
	private long venueId;

	@JsonProperty("venueName")
	private String venueName;

	@JsonProperty("type")
	private String type; // HALL, RESORT, CATERER, DECORATOR

	@JsonProperty("address")
	private String address;

	@JsonProperty("capacity")
	private int capacity;

	@JsonProperty("contactpersonName")
	private String contactpersonName;

	@JsonProperty("contactpersonNumber")
	private String contactpersonNumber;

	@JsonProperty("city")
	private String city;

	@JsonProperty("state")
	private String state;

	@JsonProperty("pincode")
	private String pincode;

	@JsonProperty("pricePerDay")
	private double pricePerDay;

	@JsonProperty("location")
	private String location;

	public long getVenueId() {
		return venueId;
	}

	public void setVenueId(long venueId) {
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

	public double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}

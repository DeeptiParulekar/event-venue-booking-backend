package com.example.booking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "venueId", "name", "type", "address", "capacity", "pricePerDay" })
public class VenueDTO {

	@JsonProperty("venueId")
	private long venueId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("type")
	private String type; // HALL, RESORT, CATERER, DECORATOR

	@JsonProperty("address")
	private String address;

	@JsonProperty("capacity")
	private int capacity;

	@JsonProperty("pricePerDay")
	private double pricePerDay;

	public long getVenueId() {
		return venueId;
	}

	public void setVenueId(long venueId) {
		this.venueId = venueId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}

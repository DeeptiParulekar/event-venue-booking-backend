package com.example.booking.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "totalVenues",
    "totalBookings",
    "upcomingBookings",
    "totalAmountSpent",
    "totalCancellations",
    "recentBookings"
})
public class UserDashboardDTO {

    @JsonProperty("totalVenues")
    private long totalVenues;

    @JsonProperty("totalBookings")
    private long totalBookings;

    @JsonProperty("upcomingBookings")
    private long upcomingBookings;

    @JsonProperty("totalAmountSpent")
    private double totalAmountSpent;

    @JsonProperty("totalCancellations")
    private long totalCancellations;

    @JsonProperty("recentBookings")
    private List<RecentBookingDTO> recentBookings;

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

    public double getTotalAmountSpent() {
        return totalAmountSpent;
    }

    public void setTotalAmountSpent(double totalAmountSpent) {
        this.totalAmountSpent = totalAmountSpent;
    }

    public long getTotalCancellations() {
        return totalCancellations;
    }

    public void setTotalCancellations(long totalCancellations) {
        this.totalCancellations = totalCancellations;
    }

    public List<RecentBookingDTO> getRecentBookings() {
        return recentBookings;
    }

    public void setRecentBookings(List<RecentBookingDTO> recentBookings) {
        this.recentBookings = recentBookings;
    }
}

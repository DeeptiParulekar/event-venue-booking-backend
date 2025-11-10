package com.example.booking.serviceimpl;

import com.example.booking.entity.Booking;
import com.example.booking.entity.Venue;
import com.example.booking.repository.BookingRepository;
import com.example.booking.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.regex.*;

@Service
public class AIAssistantService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private VenueRepository venueRepository;

	public String handleUserPrompt(String prompt) {
		prompt = prompt.toLowerCase().trim();

		// 💬 Greeting
		if (prompt.equals("hi") || prompt.equals("hello") || prompt.contains("hey")) {
			return "Hey there! 👋 How can I help you with your event or venue booking today?";
		}

		// 🎯 Extract date
		LocalDate date = parseDate(prompt);
		if (date == null) {
			return "⚠️ Sorry, I couldn't understand the date. Please type like '11th November' or 'next Monday'.";
		}

		// 🏠 Extract venue name (if present)
		String venueName = extractVenue(prompt);

		// 🗓 Check availability
		if (prompt.contains("available") || prompt.contains("which venue")) {
			List<Venue> allVenues = venueRepository.findAll();
			List<String> availableVenues = new ArrayList<>();

			for (Venue v : allVenues) {
				boolean booked = bookingRepository.existsByVenueAndBookingDate(v, date);
				if (!booked) {
					availableVenues.add(v.getVenueName());
				}
			}

			if (availableVenues.isEmpty()) {
				return "❌ Sorry, no venues available on " + formatDate(date) + ".";
			} else {
				return "✅ Here are the available venues on " + formatDate(date) + ":\n- "
						+ String.join("\n- ", availableVenues);
			}
		}

		// 🏷 Book a venue
		if (prompt.contains("book") || prompt.contains("reserve")) {
			if (venueName == null) {
				return "⚠️ Please mention which venue you want to book.";
			}

			Optional<Venue> venueOpt = venueRepository.findByVenueNameIgnoreCase(venueName);
			if (venueOpt.isEmpty()) {
				return "⚠️ OOPs, the venue '" + venueName + "' does not exist!";
			}

			Venue venue = venueOpt.get();
			boolean alreadyBooked = bookingRepository.existsByVenueAndBookingDate(venue, date);
			if (alreadyBooked) {
				return "⚠️ OOOPPPSS!!, " + venue.getVenueName() + " is already booked on " + formatDate(date) + ".";
			}

			// Save booking
			Booking booking = new Booking();
			booking.setVenue(venue);
			booking.setBookingDate(date);
			bookingRepository.save(booking);

			return "✅ Yayy!!, your booking for " + venue.getVenueName() + " on " + formatDate(date) + " is confirmed!";
		}

		return "I’m your Event & Venue Assistant 🤖 — try asking about venues, bookings, or availability!";
	}

	// ✅ DATE PARSER (Paste your given method here)
	private LocalDate parseDate(String prompt) {
		if (prompt == null || prompt.isBlank())
			return null;

		prompt = prompt.toLowerCase().replaceAll("[\"']", "").trim();
		LocalDate today = LocalDate.now();

		if (prompt.contains("today"))
			return today;
		if (prompt.contains("tomorrow"))
			return today.plusDays(1);

		// 🟢 Handle weekdays like "next Monday"
		for (DayOfWeek day : DayOfWeek.values()) {
			if (prompt.contains(day.name().toLowerCase())) {
				int todayVal = today.getDayOfWeek().getValue();
				int targetVal = day.getValue();
				int daysDiff = (targetVal - todayVal + 7) % 7;
				daysDiff = daysDiff == 0 ? 7 : daysDiff;
				return today.plusDays(daysDiff);
			}
		}

		// 🟢 Match "12th November", "15 December", "12-November", etc.
		Pattern pattern = Pattern.compile(
				"(\\d{1,2})(st|nd|rd|th)?[\\s\\-]*(january|february|march|april|may|june|july|august|september|october|november|december)");
		Matcher matcher = pattern.matcher(prompt);

		if (matcher.find()) {
			String day = matcher.group(1);
			String month = matcher.group(3);
			// Capitalize month correctly (November, December, etc.)
			String monthCap = month.substring(0, 1).toUpperCase() + month.substring(1);
			String formattedDate = day + " " + monthCap + " " + today.getYear(); // 🟢 add year here

			try {
				DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive()
						.appendPattern("d MMMM yyyy").toFormatter(Locale.ENGLISH);

				LocalDate parsedDate = LocalDate.parse(formattedDate, formatter);
				if (parsedDate.isBefore(today))
					parsedDate = parsedDate.plusYears(1);
				return parsedDate;

			} catch (DateTimeParseException e) {
				System.err.println("❌ Date parsing failed for: " + formattedDate);
				e.printStackTrace();
				return null;
			}
		}

		return null;
	}

	// ✅ Helper: extract venue name
	private String extractVenue(String prompt) {
		List<String> venues = venueRepository.findAll().stream().map(v -> v.getVenueName().toLowerCase()).toList();

		for (String v : venues) {
			if (prompt.contains(v))
				return v;
		}
		return null;
	}

	// ✅ Helper: format date
	private String formatDate(LocalDate date) {
		return date.format(DateTimeFormatter.ofPattern("d MMMM", Locale.ENGLISH));
	}
}

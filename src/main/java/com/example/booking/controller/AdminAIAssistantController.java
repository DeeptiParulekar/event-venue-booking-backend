package com.example.booking.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class AdminAIAssistantController {

	@PostMapping("/admin")
	public ResponseEntity<String> adminAssistant(@RequestBody Map<String, String> req) {
		String prompt = req.get("prompt").toLowerCase();
		String response;

		if (prompt.contains("revenue")) {
			response = "Total revenue this month is ₹2,35,000 (approx). 💰";
		} else if (prompt.contains("bookings")) {
			response = "There are 25 confirmed bookings this week.";
		} else if (prompt.contains("venue")) {
			response = "Top venues: Grand Hall, Royal Palace, and Lotus Banquet.";
		} else {
			response = "I’m your admin assistant 🤖 — you can ask about revenue, bookings, or performance.";
		}

		return ResponseEntity.ok(response);
	}
}

package com.example.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.booking.serviceimpl.AIAssistantService;

import java.util.Map;

@RestController
@RequestMapping("/ai")
public class AIAssistantController {

	@Autowired
	private AIAssistantService aiAssistantService;

	@PostMapping("/user")
	public ResponseEntity<String> handleUserPrompt(@RequestBody Map<String, String> req) {
		String reply = aiAssistantService.handleUserPrompt(req.get("prompt"));
		return ResponseEntity.ok(reply);
	}
}

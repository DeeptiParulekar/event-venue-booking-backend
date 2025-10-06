package com.example.booking.controller;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking.dto.DashboardDTO;
import com.example.booking.payloads.ErrorDetails;
import com.example.booking.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:3000")
public class DashboardController {

    @Autowired private DashboardService dashboardService;

//    @GetMapping(value = "/metrics", produces = "application/json")
//    public ResponseEntity<?> getMetrics() {
//        try {
//            DashboardDTO metrics = dashboardService.getMetrics();
//            return new ResponseEntity<>(metrics, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(new ErrorDetails(new Date(), e.getMessage()),
//                    HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }



    // Admin Dashboard
    @GetMapping(value = "/metrics", produces = "application/json")
    public ResponseEntity<?> getAdminMetrics() {
        try {
            DashboardDTO metrics = dashboardService.getMetrics(); // full metrics for admin
            return ResponseEntity.ok(metrics);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDetails(new Date(), e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // User Dashboard
    @GetMapping(value = "/user-metrics", produces = "application/json")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")  // allow both	
    public ResponseEntity<?> getUserMetrics() {
        try {
            DashboardDTO metrics = dashboardService.getUserMetrics(); // limited metrics for user
            return ResponseEntity.ok(metrics);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDetails(new Date(), e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

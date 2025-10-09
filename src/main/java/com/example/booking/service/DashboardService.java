package com.example.booking.service;

import com.example.booking.dto.DashboardDTO;
import com.example.booking.dto.UserDashboardDTO;

public interface DashboardService {

	DashboardDTO getMetrics();

	UserDashboardDTO getUserMetrics();

}

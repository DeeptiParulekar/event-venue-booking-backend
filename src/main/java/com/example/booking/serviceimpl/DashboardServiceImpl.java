package com.example.booking.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.booking.dto.DashboardDTO;
import com.example.booking.dto.RecentBookingDTO;
import com.example.booking.entity.Booking;
import com.example.booking.repository.BookingRepository;
import com.example.booking.repository.PaymentRepository;
import com.example.booking.repository.VenueRepository;
import com.example.booking.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private VenueRepository venueRepository;
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public DashboardDTO getMetrics() {
		DashboardDTO dto = new DashboardDTO();
		dto.setTotalVenues(venueRepository.count());
		dto.setTotalBookings(bookingRepository.count());
		dto.setUpcomingBookings(bookingRepository.findUpcoming(new Date()).size());
		dto.setRevenue(Optional.ofNullable(paymentRepository.totalRevenue()).orElse(0.0));

		Page<Booking> recent = new PageImpl<>(bookingRepository
				.findAll(PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "bookingDate"))).getContent());

		List<RecentBookingDTO> recents = recent.getContent().stream().map(b -> {
			RecentBookingDTO r = new RecentBookingDTO();
			r.setBookingId(b.getBookingId());
			r.setUserEmail(b.getUser() != null ? b.getUser().getEmail() : null);
			r.setVenueName(b.getVenue() != null ? b.getVenue().getVenueName() : null);
			r.setBookingDate(b.getBookingDate());
			r.setStatus(b.getStatus());
			return r;
		}).collect(Collectors.toList());

		dto.setRecentBookings(recents);
		return dto;
	}
}

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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.booking.dto.DashboardDTO;
import com.example.booking.dto.RecentBookingDTO;
import com.example.booking.dto.UserDashboardDTO;
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

//	@Override
//	public DashboardDTO getUserMetrics() {
//	    DashboardDTO dto = new DashboardDTO();
//
//	    // Get the current logged-in user (assuming Spring Security)
//	    String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
//
//	    // Fetch user-specific bookings
//	    List<Booking> userBookings = bookingRepository.findByUser_Email(currentUserEmail);
//
//	    dto.setTotalBookings(userBookings.size());
//	    dto.setUpcomingBookings((int) userBookings.stream()
//	            .filter(b -> b.getBookingDate().after(new Date()))
//	            .count());
//	    
//	    // User sees only their revenue/payments
//	    Double userRevenue = paymentRepository.totalRevenueByUser(currentUserEmail);
//	    dto.setRevenue(Optional.ofNullable(userRevenue).orElse(0.0));
//
//	    // Recent bookings (latest 5)
//	    List<RecentBookingDTO> recents = userBookings.stream()
//	            .sorted((b1, b2) -> b2.getBookingDate().compareTo(b1.getBookingDate()))
//	            .limit(5)
//	            .map(b -> {
//	                RecentBookingDTO r = new RecentBookingDTO();
//	                r.setBookingId(b.getBookingId());
//	                r.setUserEmail(b.getUser() != null ? b.getUser().getEmail() : null);
//	                r.setVenueName(b.getVenue() != null ? b.getVenue().getVenueName() : null);
//	                r.setBookingDate(b.getBookingDate());
//	                r.setStatus(b.getStatus());
//	                return r;
//	            }).collect(Collectors.toList());
//
//	    dto.setRecentBookings(recents);
//	    return dto;
//	}

//	@Override
//	public UserDashboardDTO getUserMetrics() {
//		UserDashboardDTO dto = new UserDashboardDTO();
//
//		// 1️⃣ Logged-in user
//		String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
//
//		// 2️⃣ Fetch all bookings for this user
//		List<Booking> userBookings = bookingRepository.findByUser_Email(currentUserEmail);
//
//		// 3️⃣ Total bookings
//		dto.setTotalBookings(userBookings.size());
//
//		// 4️⃣ Upcoming bookings
//		dto.setUpcomingBookings((int) userBookings.stream().filter(b -> b.getBookingDate().after(new Date())).count());
//
//		// 5️⃣ Total Amount Spent (sum of payments)
//		Double totalAmount = paymentRepository.totalRevenueByUser(currentUserEmail);
//		dto.setTotalAmountSpent(Optional.ofNullable(totalAmount).orElse(0.0));
//
//		// 6️⃣ Total Cancellations
//		dto.setTotalCancellations(
//				(int) userBookings.stream().filter(b -> "CANCELLED".equalsIgnoreCase(b.getStatus())).count());
//		
//		// 7️⃣ Total Venues (all venues available)
//		dto.setTotalVenues(venueRepository.count());
//
//
//		// 7️⃣ Recent Bookings (latest 5)
//		List<RecentBookingDTO> recents = userBookings.stream()
//				.sorted((b1, b2) -> b2.getBookingDate().compareTo(b1.getBookingDate())).limit(5).map(b -> {
//					RecentBookingDTO r = new RecentBookingDTO();
//					r.setBookingId(b.getBookingId());
//					r.setUserEmail(b.getUser() != null ? b.getUser().getEmail() : null);
//					r.setVenueName(b.getVenue() != null ? b.getVenue().getVenueName() : null);
//					r.setBookingDate(b.getBookingDate());
//					r.setStatus(b.getStatus());
//					return r;
//				}).collect(Collectors.toList());
//
//		dto.setRecentBookings(recents);
//
//		return dto;
//	}

	
	
	@Override
	public UserDashboardDTO getUserMetrics() {
	    UserDashboardDTO dto = new UserDashboardDTO();

	    // 1️⃣ Get logged-in user's email
	    String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();

	    // 2️⃣ Fetch all bookings for this user
	    List<Booking> userBookings = bookingRepository.findByUser_Email(currentUserEmail);

	    // 3️⃣ Total bookings
	    dto.setTotalBookings(userBookings.size());

	    // 4️⃣ Upcoming bookings
	    dto.setUpcomingBookings((int) userBookings.stream()
	            .filter(b -> b.getBookingDate().after(new Date()))
	            .count());

	    // 5️⃣ Total Amount Spent: sum from Booking.totalAmount (BOOKED only)
	    Double totalAmount = userBookings.stream()
	            .filter(b -> "BOOKED".equalsIgnoreCase(b.getStatus()))
	            .mapToDouble(Booking::getTotalAmount)
	            .sum();
	    dto.setTotalAmountSpent(totalAmount);

	    // 6️⃣ Total Cancellations
	    dto.setTotalCancellations((int) userBookings.stream()
	            .filter(b -> "CANCELLED".equalsIgnoreCase(b.getStatus()))
	            .count());

	    // 7️⃣ Total Venues
	    dto.setTotalVenues(venueRepository.count());

	    // 8️⃣ Recent Bookings (latest 5)
	    List<RecentBookingDTO> recents = userBookings.stream()
	            .sorted((b1, b2) -> b2.getBookingDate().compareTo(b1.getBookingDate()))
	            .limit(5)
	            .map(b -> {
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

//package com.example.booking.serviceimpl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.booking.dto.BookingDTO;
//import com.example.booking.entity.Booking;
//import com.example.booking.entity.User;
//import com.example.booking.entity.Venue;
//import com.example.booking.mapper.BookingMapper;
//import com.example.booking.repository.BookingRepository;
//import com.example.booking.repository.UserRepository;
//import com.example.booking.repository.VenueRepository;
//import com.example.booking.service.BookingService;
//
//@Service
//public class BookingServiceImpl implements BookingService {
//
//	@Autowired
//	private BookingRepository bookingRepository;
//	
//	@Autowired
//	private VenueRepository venueRepository;
//	
//	@Autowired
//	private UserRepository userRepository;
//
//	@Autowired
//	private BookingMapper bookingMapper;
//
////    @Override
////    public BookingDTO createBooking(BookingDTO bookingDTO) {
////        Booking booking = bookingMapper.toBooking(bookingDTO);
////        Booking saved = bookingRepository.save(booking);
////        return bookingMapper.toBookingDTO(saved);
////    }
//
////	@Override
////	public BookingDTO createBooking(BookingDTO bookingDTO) {
////	    // Fetch User
////	    User user = userRepository.findById(bookingDTO.getUserId())
////	            .orElseThrow(() -> new RuntimeException("User not found"));
////
////	    // Fetch Venue by name
////	    Venue venue = venueRepository.findByNameIgnoreCase(bookingDTO.getVenueName().trim())
////	            .orElseThrow(() -> new RuntimeException("Venue not found"));
////
////	    // Check if venue already booked
////	    boolean alreadyBooked = bookingRepository.existsByVenueAndBookingDate(venue, bookingDTO.getBookingDate());
////	    if (alreadyBooked) {
////	        throw new RuntimeException("Venue is already booked on this date");
////	    }
////
////	    // Create Booking entity
////	    Booking booking = new Booking();
////	    booking.setUser(user);
////	    booking.setVenue(venue);
////	    booking.setBookingDate(bookingDTO.getBookingDate());
////	    booking.setTotalAmount(bookingDTO.getTotalAmount());
////	    booking.setUserEmail(bookingDTO.getUserEmail());
////	    booking.setStatus(bookingDTO.getStatus() != null ? bookingDTO.getStatus() : "PENDING");
////
////	    // Save booking
////	    Booking savedBooking = bookingRepository.save(booking);
////
////	    // Map back to DTO
////	    BookingDTO responseDTO = new BookingDTO();
////	    responseDTO.setBookingId(savedBooking.getBookingId());
////	    responseDTO.setUserId(user.getUserId());
////	    responseDTO.setUserName(user.getUserName());
////	    responseDTO.setVenueName(venue.getVenueName());
////	    responseDTO.setBookingDate(savedBooking.getBookingDate());
////	    responseDTO.setStatus(savedBooking.getStatus());
////	    responseDTO.setTotalAmount(savedBooking.getTotalAmount());
////	    responseDTO.setUserEmail(savedBooking.getUserEmail());
////
////	    return responseDTO;
////	}
//
//
//	@Override
//	public BookingDTO updateBooking(BookingDTO bookingDTO) {
//		if (bookingDTO.getBookingId() == null) {
//			throw new IllegalArgumentException("Booking ID cannot be null for update");
//		}
//		Booking booking = bookingMapper.toBooking(bookingDTO);
//		Booking updated = bookingRepository.save(booking);
//		return bookingMapper.toBookingDTO(updated);
//	}
//
//	@Override
//	public List<BookingDTO> getAllBookings() {
//		List<Booking> bookings = bookingRepository.findAll();
//		return bookingMapper.toBookingDTOList(bookings);
//	}
//
//	@Override
//	public BookingDTO getBookingById(Long bookingId) {
//		Booking booking = bookingRepository.findById(bookingId)
//				.orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));
//		return bookingMapper.toBookingDTO(booking);
//	}
//
//	@Override
//	public void deleteBooking(Long bookingId) {
//		if (!bookingRepository.existsById(bookingId)) {
//			throw new RuntimeException("Booking not found with id: " + bookingId);
//		}
//		bookingRepository.deleteById(bookingId);
//	}
//}



//package com.example.booking.serviceimpl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.booking.dto.BookingDTO;
//import com.example.booking.entity.Booking;
//import com.example.booking.entity.User;
//import com.example.booking.entity.Venue;
//import com.example.booking.mapper.BookingMapper;
//import com.example.booking.repository.BookingRepository;
//import com.example.booking.repository.UserRepository;
//import com.example.booking.repository.VenueRepository;
//import com.example.booking.service.BookingService;
//import javax.servlet.http.HttpServletRequest;
//
//
//@Service
//public class BookingServiceImpl implements BookingService {
//
//    @Autowired
//    private BookingRepository bookingRepository;
//
//    
//    @Autowired
//    private UserRepository userRepository;
//
//    
//    @Autowired
//    private VenueRepository venueRepository;
//
//    @Autowired
//    private BookingMapper bookingMapper;
//
//    @Autowired
//    private HttpServletRequest request; // to get logged-in user from JWT
//
//    /**
//     * Create a booking using only venueName and logged-in user
//     */
//    @Override
//    public BookingDTO createBooking(BookingDTO bookingDTO) {
//        // 1️⃣ Get logged-in user's email from JWT (assume stored in request attribute)
//        String userEmail = (String) request.getAttribute("userEmail");
//        if (userEmail == null) {
//            throw new RuntimeException("User not logged in");
//        }
//
//        User user = userRepository.findByEmail(userEmail)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // 2️⃣ Fetch Venue by name (case-insensitive, trimmed)
//        String venueName = bookingDTO.getVenueName().trim();
//        Venue venue = venueRepository.findByNameIgnoreCase(venueName)
//                .orElseThrow(() -> new RuntimeException("Venue not found"));
//
//        // 3️⃣ Check if venue is already booked on the requested date
//        boolean alreadyBooked = bookingRepository.existsByVenueAndBookingDate(venue, bookingDTO.getBookingDate());
//        if (alreadyBooked) {
//            throw new RuntimeException("Venue is already booked on this date");
//        }
//
//        // 4️⃣ Map DTO to Entity
//        Booking booking = new Booking();
//        booking.setUser(user);
//        booking.setVenue(venue);
//        booking.setBookingDate(bookingDTO.getBookingDate());
//        booking.setTotalAmount(bookingDTO.getTotalAmount());
//        booking.setUserEmail(user.getUserEmail());
//        booking.setStatus(bookingDTO.getStatus() != null ? bookingDTO.getStatus() : "PENDING");
//
//        // 5️⃣ Save Booking
//        Booking savedBooking = bookingRepository.save(booking);
//
//        // 6️⃣ Map Entity back to DTO
//        BookingDTO responseDTO = new BookingDTO();
//        responseDTO.setBookingId(savedBooking.getBookingId());
//        responseDTO.setUserId(user.getUserId());
//        responseDTO.setUserName(user.getUserName());
//        responseDTO.setVenueName(venue.getVenueName());
//        responseDTO.setBookingDate(savedBooking.getBookingDate());
//        responseDTO.setStatus(savedBooking.getStatus());
//        responseDTO.setTotalAmount(savedBooking.getTotalAmount());
//        responseDTO.setUserEmail(savedBooking.getUserEmail());
//
//        return responseDTO;
//    }
//
//    @Override
//    public BookingDTO updateBooking(BookingDTO bookingDTO) {
//        if (bookingDTO.getBookingId() == null) {
//            throw new IllegalArgumentException("Booking ID cannot be null for update");
//        }
//        Booking booking = bookingMapper.toBooking(bookingDTO);
//        Booking updated = bookingRepository.save(booking);
//        return bookingMapper.toBookingDTO(updated);
//    }
//
//    @Override
//    public List<BookingDTO> getAllBookings() {
//        List<Booking> bookings = bookingRepository.findAll();
//        return bookingMapper.toBookingDTOList(bookings);
//    }
//
//    @Override
//    public BookingDTO getBookingById(Long bookingId) {
//        Booking booking = bookingRepository.findById(bookingId)
//                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));
//        return bookingMapper.toBookingDTO(booking);
//    }
//
//    @Override
//    public void deleteBooking(Long bookingId) {
//        if (!bookingRepository.existsById(bookingId)) {
//            throw new RuntimeException("Booking not found with id: " + bookingId);
//        }
//        bookingRepository.deleteById(bookingId);
//    }
//}
//


package com.example.booking.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.booking.dto.BookingDTO;
import com.example.booking.entity.Booking;
import com.example.booking.entity.User;
import com.example.booking.entity.Venue;
import com.example.booking.mapper.BookingMapper;
import com.example.booking.repository.BookingRepository;
import com.example.booking.repository.UserRepository;
import com.example.booking.repository.VenueRepository;
import com.example.booking.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingMapper bookingMapper;

    /**
     * Create a booking using only venueName and logged-in user
     */
    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        // 1️⃣ Get logged-in user email from SecurityContext (JWT)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // Assuming username = email

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2️⃣ Fetch Venue by name (case-insensitive)
        String venueName = bookingDTO.getVenueName();
        Venue venue = venueRepository.findByNameIgnoreCaseTrimmed(venueName)
                .orElseThrow(() -> new RuntimeException("Venue not found"));


        // 3️⃣ Check if venue already booked on requested date
        boolean alreadyBooked = bookingRepository.existsByVenueAndBookingDate(venue, bookingDTO.getBookingDate());
        if (alreadyBooked) {
            throw new RuntimeException("Venue is already booked on this date");
        }

        // 4️⃣ Map DTO to Entity
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setVenue(venue);
        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setTotalAmount(bookingDTO.getTotalAmount());
        booking.setUserEmail(bookingDTO.getUserEmail());
        booking.setStatus(bookingDTO.getStatus() != null ? bookingDTO.getStatus() : "PENDING");

        // 5️⃣ Save Booking
        Booking savedBooking = bookingRepository.save(booking);

        // 6️⃣ Map Entity back to DTO
        BookingDTO responseDTO = new BookingDTO();
        responseDTO.setBookingId(savedBooking.getBookingId());
        responseDTO.setUserId(user.getUserId());
        responseDTO.setUserName(user.getUserName());
        responseDTO.setVenueName(venue.getVenueName());
        responseDTO.setBookingDate(savedBooking.getBookingDate());
        responseDTO.setStatus(savedBooking.getStatus());
        responseDTO.setTotalAmount(savedBooking.getTotalAmount());
        responseDTO.setUserEmail(savedBooking.getUserEmail());

        return responseDTO;
    }

    @Override
    public BookingDTO updateBooking(BookingDTO bookingDTO) {
        if (bookingDTO.getBookingId() == null) {
            throw new IllegalArgumentException("Booking ID cannot be null for update");
        }
        Booking booking = bookingMapper.toBooking(bookingDTO);
        Booking updated = bookingRepository.save(booking);
        return bookingMapper.toBookingDTO(updated);
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookingMapper.toBookingDTOList(bookings);
    }

    @Override
    public BookingDTO getBookingById(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));
        return bookingMapper.toBookingDTO(booking);
    }

    
  
    @Override
    public void deleteBooking(Long bookingId) {
        if (!bookingRepository.existsById(bookingId)) {
            throw new RuntimeException("Booking not found with id: " + bookingId);
        }
        bookingRepository.deleteById(bookingId);
    }
}

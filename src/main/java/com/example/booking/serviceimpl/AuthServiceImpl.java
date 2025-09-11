package com.example.booking.serviceimpl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.booking.entity.User;
import com.example.booking.repository.UserRepository;
import com.example.booking.service.AuthService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	private String secretKey = "mySuperSecretKey12345"; // Use application.properties instead

	@Override
	public boolean validateUser(String email, String rawPassword) {
		Optional<User> userOpt = userRepository.findByEmail(email);
		return userOpt.isPresent() && passwordEncoder.matches(rawPassword, userOpt.get().getPassword());
	}

	@Override
	public User registerUser(User user) {
		// Encrypt password before saving
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public String generateToken(String email) {
		long expirationTime = 1000 * 60 * 60; // 1 hour

		return Jwts.builder().setSubject(email).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
	}

	@Autowired
	private JavaMailSender mailSender;

//    public String forgotPassword(String email) {
//        Optional<User> optionalUser = userRepository.findByEmail(email);
//
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            String token = UUID.randomUUID().toString();
//            user.setResetToken(token);
//            userRepository.save(user);
//
//            String resetLink = "http://your-frontend-url.com/reset-password?token=" + token;
//
//            SimpleMailMessage mailMessage = new SimpleMailMessage();
//            mailMessage.setTo(user.getEmail());
//            mailMessage.setSubject("Password Reset Request");
//            mailMessage.setText("Click the link to reset your password:\n" + resetLink);
//
//            mailSender.send(mailMessage);
//
//            return "Reset link sent to your email.";
//        } else {
//            return "Email not found.";
//        }
//    }
//
//    public String resetPassword(String token, String newPassword) {
//        Optional<User> optionalUser = userRepository.findByResetToken(token);
//
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
//            user.setResetToken(null);
//            userRepository.save(user);
//
//            return "Password reset successfully.";
//        } else {
//            return "Invalid token.";
//        }
//    }

	@Value("${app.frontend.url}")
	private String frontendUrl;

	private final long RESET_TOKEN_EXPIRY_MS = 60 * 60 * 1000; // 1 hour

	@Override
	public String forgotPassword(String email) {
		Optional<User> optionalUser = userRepository.findByEmail(email);

		// Generic message for security
		String message = "If the email exists, a reset link has been sent.";

		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			String token = UUID.randomUUID().toString();
			user.setResetToken(token);
			user.setResetTokenExpiry(new Date(System.currentTimeMillis() + RESET_TOKEN_EXPIRY_MS));
			userRepository.save(user);

			String resetLink = frontendUrl + "/reset-password?token=" + token;

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(user.getEmail());
			mailMessage.setSubject("Password Reset Request");
			mailMessage.setText("Click the link to reset your password:\n" + resetLink);

			mailSender.send(mailMessage);
		}

		return message;
	}

	@Override
	public String resetPassword(String token, String newPassword) {
		Optional<User> optionalUser = userRepository.findByResetToken(token);

		if (optionalUser.isEmpty()) {
			return "Invalid or expired token.";
		}

		User user = optionalUser.get();
		Date now = new Date();

		if (user.getResetTokenExpiry() == null || user.getResetTokenExpiry().before(now)) {
			return "Token has expired.";
		}

		user.setPassword(passwordEncoder.encode(newPassword));
		user.setResetToken(null);
		user.setResetTokenExpiry(null);
		userRepository.save(user);

		return "Password reset successfully.";
	}
}

package com.boutique.vehiculos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.boutique.vehiculos.entity.Booking;
import com.boutique.vehiculos.entity.WorkService;
import com.boutique.vehiculos.repository.BookingRepository;

import jakarta.transaction.Transactional;

@Service("bookingService")
public class BookingService {

	@Autowired
	@Qualifier("bookingRepository")
	private BookingRepository bookingRepository;

	public List<Booking> findAll() {
		return bookingRepository.findAll();
	}

	public Booking findById(Long id) {
		Optional<Booking> booking = bookingRepository.findById(id);
		return booking.orElse(null);
	}

	public Booking saveBooking(Booking booking) {
		return bookingRepository.save(booking);
	}

	public List<Booking> findBookingByCustomerId(Long id) {
		return bookingRepository.findBookingsByCustomerId(id);
	}

	public List<Booking> findBookingByPremiumCustomers() {
		return bookingRepository.findBookingsByPremiumCustomers();
	}

	public boolean removeBooking(Long id) {
		try {
			bookingRepository.deleteById(id);
			return true;

		} catch (Exception exception) {
			return false;
		}
	}

	@Transactional
	public Booking addWorkService(Booking booking, WorkService service) {

		if (booking != null && service != null) {
			booking.getWorkService().add(service);
			return bookingRepository.save(booking);
		}
		return null;
	}
}

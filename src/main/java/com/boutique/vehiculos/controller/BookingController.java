package com.boutique.vehiculos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boutique.vehiculos.dto.BookingDto;
import com.boutique.vehiculos.entity.Booking;
import com.boutique.vehiculos.entity.Customer;
import com.boutique.vehiculos.entity.ServiceDetail;
import com.boutique.vehiculos.entity.Vehicle;
import com.boutique.vehiculos.entity.WorkService;
import com.boutique.vehiculos.service.BookingService;
import com.boutique.vehiculos.service.CustomerService;
import com.boutique.vehiculos.service.ServiceDetailService;
import com.boutique.vehiculos.service.VehicleService;
import com.boutique.vehiculos.service.WorkServiceService;
import com.boutique.vehiculos.service.util.ServiceDetailUtil;

import jakarta.transaction.Transactional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/bookings")
public class BookingController {

	@Autowired
	@Qualifier("bookingService")
	private BookingService bookingService;

	@Autowired
	@Qualifier("customerService")
	private CustomerService customerService;

	@Autowired
	@Qualifier("vehicleService")
	private VehicleService vehicleService;

	@Autowired
	@Qualifier("serviceDetailService")
	private ServiceDetailService serviceDetailService;

	@Autowired
	@Qualifier("workServiceService")
	private WorkServiceService workServiceService;

	@GetMapping("/all")
	public List<Booking> getAll() {

		List<Booking> bookings = bookingService.findAll();

		for (Booking booking : bookings) {
			booking.getCustomer().getVehicles().clear();
		}
		return bookings;
	}

	@GetMapping("/active")
	public List<Booking> getActiveBookings() {

		List<Booking> bookings = this.getAll();
		List<Booking> bookingsFiltered = new ArrayList<Booking>();

		for (Booking booking : bookings) {
			if (booking.isActive()) {
				bookingsFiltered.add(booking);
			}
		}
		return bookingsFiltered;
	}

	@GetMapping("/not-active")
	public List<Booking> getNonActiveBookings() {

		List<Booking> bookings = this.getAll();
		List<Booking> bookingsFiltered = new ArrayList<Booking>();

		for (Booking booking : bookings) {
			if (!booking.isActive()) {
				bookingsFiltered.add(booking);
			}
		}
		return bookingsFiltered;
	}

	@GetMapping("/customers/{id}")
	public List<Booking> getBookingByCustomerId(@PathVariable Long id) {
		return bookingService.findBookingByCustomerId(id);
	}

	@GetMapping("/customers/premium")
	public List<Booking> getBookingsByPremiumUsers() {
		return bookingService.findBookingByPremiumCustomers();
	}

	@PostMapping("/")
	@Transactional
	public ResponseEntity<Booking> newBooking(@RequestBody BookingDto bookingDto) {

		Customer customer = customerService.findById(bookingDto.getCustomerId());
		Vehicle vehicle = vehicleService.findById(bookingDto.getVehicleId());

		customer.setBookingQuantity(customer.getBookingQuantity() + 1);

		if (customer != null && vehicle != null) {

			Booking booking = new Booking();

			booking.setCustomer(customer);
			booking.setVehicle(vehicle);
			booking.setTime(bookingDto.getTime());
			booking.setActive(true);

			Booking bookingResult = bookingService.saveBooking(booking);

			WorkService workService = new WorkService();
			ServiceDetail serviceDetail = serviceDetailService.getServiceById(bookingDto.getServiceDetailId());

			if (customer.getBookingQuantity() >= 5) {
				customer.setIsPremium(true);
			}

			if (customer.getIsPremium()) {
				serviceDetail = ServiceDetailUtil.getDiscountIfPremium(customer, serviceDetail);
			}

			if (serviceDetail != null) {
				workService.setServiceDetail(serviceDetail);
			}

			workService.setBooking(booking);
			WorkService workServiceResult = workServiceService.saveService(workService);

			bookingResult.getWorkService().add(workServiceResult);
			customerService.saveCustomer(customer);
			bookingService.saveBooking(bookingResult);

			return ResponseEntity.ok(bookingResult);

		}
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteBooking(@PathVariable Long id) {

		Boolean result = bookingService.removeBooking(id);

		if (result) {

			return ResponseEntity.ok(result);

		}

		return ResponseEntity.notFound().build();

	}

	@PutMapping("/{id}")
	public ResponseEntity<Booking> completeBooking(@PathVariable Long id) {

		Booking booking = bookingService.findById(id);

		if (booking.isActive()) {

			booking.setActive(false);

			Booking bookingResult = bookingService.saveBooking(booking);
			return ResponseEntity.ok(bookingResult);

		}
		return ResponseEntity.badRequest().build();
	}
}
package com.boutique.vehiculos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.boutique.vehiculos.dto.CustomerDto;
import com.boutique.vehiculos.entity.Booking;
import com.boutique.vehiculos.entity.Customer;
import com.boutique.vehiculos.entity.Vehicle;
import com.boutique.vehiculos.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service("customerService")
public class CustomerService {

	@Autowired
	@Qualifier("customerRepository")
	CustomerRepository customerRepository;

	public List<CustomerDto> findAll() {

		List<Customer> customers = customerRepository.findAll();

		List<CustomerDto> customersResult = new ArrayList<CustomerDto>();

		for (Customer customer : customers) {

			CustomerDto customerDto = new CustomerDto();

			customerDto.setCustomerId(customer.getCustomerId());
			customerDto.setFirstName(customer.getFirstName());
			customerDto.setLastName(customer.getLastName());
			customerDto.setPremium(customer.getIsPremium());
			customerDto.setVehicles(customer.getVehicles());
			customerDto.setBookings(customer.getBookings());
			customerDto.setBookingQuantity(customer.getBookingQuantity());

			customersResult.add(customerDto);

		}

		return customersResult;

	}

	public Customer findById(Long id) {

		Optional<Customer> customer = customerRepository.findById(id);
		return customer.orElse(null);
	}

	public Customer saveCustomer(Customer customer) {

		return customerRepository.save(customer);

	}

	@Transactional
	public Customer addVehicle(Customer customer, Vehicle vehicle) {

		if (customer != null && vehicle != null) {

			customer.getVehicles().add(vehicle);
			vehicle.setCustomer(customer);
			return saveCustomer(customer);
		}
		return null;
	}

	@Transactional
	public Customer removeVehicle(Customer customer, Vehicle vehicle) {

		if (customer != null && vehicle != null) {

			customer.getVehicles().remove(vehicle);
			vehicle.setCustomer(null);
			return saveCustomer(customer);
		}
		return null;
	}

	@Transactional
	public Customer addBooking(Customer customer, Booking booking) {

		if (customer != null && booking != null) {

			customer.getBookings().add(booking);
			booking.setCustomer(customer);
			return saveCustomer(customer);
		}
		return null;
	}

	@Transactional
	public Customer removeBooking(Customer customer, Booking booking) {

		if (customer != null && booking != null) {

			customer.getBookings().remove(booking);
			booking.setCustomer(null);
			return saveCustomer(customer);
		}
		return null;
	}

	public boolean removeCustomer(Customer customer) {
		customerRepository.delete(customer);
		return true;
	}

}

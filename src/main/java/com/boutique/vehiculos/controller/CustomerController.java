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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boutique.vehiculos.dto.CreateCustomerDto;
import com.boutique.vehiculos.dto.CustomerDto;
import com.boutique.vehiculos.entity.Customer;
import com.boutique.vehiculos.entity.Vehicle;
import com.boutique.vehiculos.service.CustomerService;
import com.boutique.vehiculos.service.VehicleService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/customers")
public class CustomerController {

	@Autowired
	@Qualifier("customerService")
	CustomerService customerService;

	@Autowired
	@Qualifier("vehicleService")
	VehicleService vehicleService;

	@GetMapping("/all")
	public List<CustomerDto> getAll() {
		return customerService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
		Customer customer = customerService.findById(id);

		if (customer != null) {
			return ResponseEntity.ok(customer);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/")
	public ResponseEntity<Customer> saveCustomer(@RequestBody CreateCustomerDto customerDto) {

		Customer customer = new Customer();

		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setVehicles(new ArrayList<Vehicle>());
		customer.setBookingQuantity(0);
		customer.setIsPremium(false);

		Customer customerResult = customerService.saveCustomer(customer);

		return ResponseEntity.ok(customerResult);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> removeCustomer(@PathVariable Long id) {

		Customer customer = customerService.findById(id);

		if (customer != null) {

			boolean result = customerService.removeCustomer(customer);
			return ResponseEntity.ok(result);

		}
		return ResponseEntity.notFound().build();

	}

	@PostMapping("/{customerId}/vehicles/{vehicleId}")
	public ResponseEntity<Customer> addVehicleToCustomer(@PathVariable Long customerId, @PathVariable Long vehicleId) {

		Customer customer = customerService.findById(customerId);
		Vehicle vehicle = vehicleService.findById(vehicleId);

		if (customer != null && vehicle != null) {

			Customer customerResult = customerService.addVehicle(customer, vehicle);
			return ResponseEntity.ok(customerResult);

		}
		return ResponseEntity.internalServerError().build();
	}

	@DeleteMapping("/{customerId}/vehicles/{vehicleId}")
	public ResponseEntity<Customer> removeVehicleToCustomer(@PathVariable Long customerId,
			@PathVariable Long vehicleId) {

		Customer customer = customerService.findById(customerId);
		Vehicle vehicle = vehicleService.findById(vehicleId);

		if (customer != null && vehicle != null) {

			Customer customerResult = customerService.removeVehicle(customer, vehicle);
			vehicleService.removeVehicle(vehicleId);
			return ResponseEntity.ok(customerResult);

		}
		return ResponseEntity.internalServerError().build();
	}
}

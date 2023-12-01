package com.boutique.vehiculos.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.boutique.vehiculos.entity.Customer;

@SpringBootTest
public class CustomerServiceTest {

	@Autowired
	@Qualifier("customerService")
	private CustomerService customerService;

	private Customer customer;

	@BeforeEach
	void setup() {
		customer = new Customer("Leandro", "Blandi", true);
	}

	// Test 1
	@DisplayName("Test para guardar un cliente")
	@Test
	public void testSaveCustomer() {
		Customer customerResult = customerService.saveCustomer(customer);
		assertThat(customerResult).isNotNull();
		assertThat(customerResult.getCustomerId()).isGreaterThan(0);
	}

	// Test 2
	@DisplayName("Test para obtener cliente por Id")
	@Test
	void testGetCustomerById() {
		customerService.saveCustomer(customer);
		Customer customerResult = customerService.findById(customer.getCustomerId());
		assertThat(customerResult).isNotNull();
	}

	// Test 3
	@DisplayName("Test para eliminar un cliente")
	@Test
	void testRemoveCustomer() {
		customerService.saveCustomer(customer);
		boolean result = customerService.removeCustomer(customer);
		assertThat(result).isTrue();
	}
}

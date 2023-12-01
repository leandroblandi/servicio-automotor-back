package com.boutique.vehiculos.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.boutique.vehiculos.entity.Vehicle;

@SpringBootTest
public class VehicleServiceTest {

	@Autowired
	@Qualifier("vehicleService")
	private VehicleService vehicleService;
	private Vehicle vehicle;

	@BeforeEach
	void run() {
		vehicle = new Vehicle("AKX193");
	}

	// Test 4
	@DisplayName("Test para obtener todos los vehiculos")
	@Test
	void testGetAllVehicles() {

		List<Vehicle> vehicles = vehicleService.findAll();
		assertThat(vehicles).size().isGreaterThan(0);

	}

	// Test 5
	@DisplayName("Test para agregar un vehiculo")
	@Test
	void testSaveVehicle() {
		Vehicle vehicleResult = vehicleService.saveVehicle(vehicle);
		assertThat(vehicleResult).isNotNull();
		assertThat(vehicleResult.getVehicleId()).isGreaterThan(0);
	}

	// Test 6
	@DisplayName("Test para eliminar un vehiculo")
	@Test
	void testRemoveVehicle() {
		Long vehicleId = vehicleService.saveVehicle(vehicle).getVehicleId();
		boolean result = vehicleService.removeVehicle(vehicleId);
		assertThat(result).isTrue();
	}

}

package com.boutique.vehiculos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boutique.vehiculos.entity.Vehicle;
import com.boutique.vehiculos.service.VehicleService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/vehicles")
public class VehicleController {

	@Autowired
	@Qualifier("vehicleService")
	VehicleService vehicleService;

	@GetMapping("/all")
	public List<Vehicle> getAll() {
		return vehicleService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
		Vehicle vehicle = vehicleService.findById(id);

		if (vehicle != null) {
			return ResponseEntity.ok(vehicle);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/")
	public ResponseEntity<Vehicle> newVehicle(@RequestBody Vehicle vehicle) {

		Vehicle vehicleResult = vehicleService.saveVehicle(vehicle);
		return ResponseEntity.ok(vehicleResult);

	}

}

package com.boutique.vehiculos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.boutique.vehiculos.entity.Vehicle;
import com.boutique.vehiculos.repository.VehicleRepository;

@Service("vehicleService")
public class VehicleService {

	@Autowired
	@Qualifier("vehicleRepository")
	private VehicleRepository vehicleRepository;

	public List<Vehicle> findAll() {
		return vehicleRepository.findAll();
	}

	public Vehicle findById(Long id) {
		Optional<Vehicle> vehicle = vehicleRepository.findById(id);
		return vehicle.orElse(null);
	}

	public Vehicle saveVehicle(Vehicle vehicle) {

		return vehicleRepository.save(vehicle);

	}

	public boolean removeVehicle(Long id) {
		try {
			vehicleRepository.deleteById(id);
			return true;

		} catch (Exception exception) {
			return false;
		}
	}

}

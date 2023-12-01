package com.boutique.vehiculos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boutique.vehiculos.entity.Vehicle;

@Repository("vehicleRepository")
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}

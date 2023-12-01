package com.boutique.vehiculos.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "vehicles")
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vehicleId;

	@Column
	@Pattern(regexp = "^[A-Z]{3}\\d{3}$", message = "Vehicle plate is invalid")
	private String vehiclePlate;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@CreationTimestamp
	@Column
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column
	private LocalDateTime UpdatedAt;

	public Vehicle() {
		// TODO Auto-generated constructor stub
	}

	public Vehicle(@Pattern(regexp = "^[A-Z]{3}\\d{3}$", message = "Vehicle plate is invalid") String vehiclePlate) {
		this.setVehiclePlate(vehiclePlate);
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehiclePlate() {
		return vehiclePlate;
	}

	public void setVehiclePlate(String vehiclePlate) {
		this.vehiclePlate = vehiclePlate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}

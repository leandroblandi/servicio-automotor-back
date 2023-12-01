package com.boutique.vehiculos.dto;

import java.util.List;

import com.boutique.vehiculos.entity.Booking;
import com.boutique.vehiculos.entity.Vehicle;

public class CustomerDto {

	private Long customerId;
	private String firstName;
	private String lastName;
	private boolean isPremium;
	private List<Vehicle> vehicles;
	private List<Booking> bookings;
	private int bookingQuantity;

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isPremium() {
		return isPremium;
	}

	public void setPremium(boolean isPremium) {
		this.isPremium = isPremium;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public int getBookingQuantity() {
		return bookingQuantity;
	}

	public void setBookingQuantity(int boookingQuantity) {
		this.bookingQuantity = boookingQuantity;
	}
}

package com.boutique.vehiculos.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookings")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;

	@Column
	private LocalDateTime time;

	@JsonIgnoreProperties("bookings")
	@ManyToOne(fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne(fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JoinColumn(name = "vehicle_id")
	private Vehicle vehicle;

	@JsonManagedReference
	@OneToMany(mappedBy = "booking", fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REMOVE })
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private List<WorkService> workService = new ArrayList<WorkService>();

	@Column
	private boolean active;

	@CreationTimestamp
	@Column
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column
	private LocalDateTime UpdatedAt;

	public Booking() {
		// TODO Auto-generated constructor stub
	}

	public Booking(LocalDateTime time, Customer customer, Vehicle vehicle) {
		this.setTime(time);
		this.setCustomer(customer);
		this.setVehicle(vehicle);
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public List<WorkService> getWorkService() {
		return workService;
	}

	public void setWorkService(List<WorkService> workService) {
		this.workService = workService;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}

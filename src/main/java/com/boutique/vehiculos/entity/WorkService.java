package com.boutique.vehiculos.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "work_services")
public class WorkService {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "work_service_id")
	private int workServiceId;

	@ManyToOne(fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JoinColumn(name = "service_id")
	private ServiceDetail serviceDetail;

	@JsonBackReference
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "booking_id")
	private Booking booking;

	@CreationTimestamp
	@Column
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column
	private LocalDateTime UpdatedAt;

	public WorkService() {
		// TODO Auto-generated constructor stub
	}

	public WorkService(ServiceDetail serviceDetail) {
		this.setServiceDetail(serviceDetail);
	}

	public int getWorkServiceId() {
		return workServiceId;
	}

	public void setWorkServiceId(int workServiceId) {
		this.workServiceId = workServiceId;
	}

	public ServiceDetail getServiceDetail() {
		return serviceDetail;
	}

	public void setServiceDetail(ServiceDetail serviceDetail) {
		this.serviceDetail = serviceDetail;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return UpdatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		UpdatedAt = updatedAt;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

}

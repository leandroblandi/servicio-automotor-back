package com.boutique.vehiculos.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "services")
public class ServiceDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long serviceId;

	@Column
	private String serviceName;

	@Column
	private Float servicePrice;

	@CreationTimestamp
	@Column
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column
	private LocalDateTime UpdatedAt;

	public ServiceDetail() {
		// TODO Auto-generated constructor stub
	}

	public ServiceDetail(String serviceName, Float servicePrice) {
		this.setServiceName(serviceName);
		this.setServicePrice(servicePrice);
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Float getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(Float servicePrice) {
		this.servicePrice = servicePrice;
	}
}

package com.boutique.vehiculos.dto;

import java.time.LocalDateTime;

public class BookingDto {

	private Long customerId;
	private Long vehicleId;
	private Long serviceDetailId;
	private LocalDateTime time;

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setServiceDetailId(Long serviceDetailId) {
		this.serviceDetailId = serviceDetailId;
	}

	public Long getServiceDetailId() {
		return serviceDetailId;
	}
}

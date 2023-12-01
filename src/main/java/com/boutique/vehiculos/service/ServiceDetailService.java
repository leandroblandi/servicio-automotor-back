package com.boutique.vehiculos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.boutique.vehiculos.entity.ServiceDetail;
import com.boutique.vehiculos.repository.ServiceDetailRepository;

@Service("serviceDetailService")
public class ServiceDetailService {

	@Autowired
	@Qualifier("serviceDetailRepository")
	private ServiceDetailRepository serviceRepository;

	public ServiceDetail saveService(ServiceDetail service) {
		return serviceRepository.save(service);
	}

	public List<ServiceDetail> getAll() {
		return serviceRepository.findAll();
	}

	public ServiceDetail getServiceById(Long serviceId) {

		Optional<ServiceDetail> serviceDetail = serviceRepository.findById(serviceId);
		return serviceDetail.orElse(null);
	}
}

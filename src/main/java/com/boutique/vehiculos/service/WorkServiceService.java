package com.boutique.vehiculos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.boutique.vehiculos.entity.WorkService;
import com.boutique.vehiculos.repository.WorkServiceRepository;

@Service("workServiceService")
public class WorkServiceService {

	@Autowired
	@Qualifier("workServiceRepository")
	WorkServiceRepository workServiceRepository;

	public WorkService saveService(WorkService service) {
		return workServiceRepository.save(service);
	}

	public List<WorkService> getAll() {
		return workServiceRepository.findAll();
	}

	public WorkService getServiceById(Long serviceId) {
		Optional<WorkService> workService = workServiceRepository.findById(serviceId);
		return workService.orElse(null);
	}

}

package com.boutique.vehiculos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boutique.vehiculos.entity.ServiceDetail;
import com.boutique.vehiculos.service.ServiceDetailService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/service-detail")
public class ServiceDetailController {

	@Autowired
	@Qualifier("serviceDetailService")
	ServiceDetailService serviceDetailService;

	@GetMapping("/all")
	public List<ServiceDetail> getAll() {
		return serviceDetailService.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ServiceDetail> getServiceDetailById(@PathVariable Long id) {

		ServiceDetail serviceDetail = serviceDetailService.getServiceById(id);

		if (serviceDetail != null) {
			return ResponseEntity.ok(serviceDetail);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/")
	public ResponseEntity<ServiceDetail> newServiceDetail(@RequestBody ServiceDetail serviceDetail) {

		ServiceDetail serviceDetailResult = serviceDetailService.saveService(serviceDetail);
		return ResponseEntity.ok(serviceDetailResult);

	}

}

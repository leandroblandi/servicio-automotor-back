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

import com.boutique.vehiculos.entity.WorkService;
import com.boutique.vehiculos.service.WorkServiceService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/work-service")
public class WorkServiceController {

	@Autowired
	@Qualifier("workServiceService")
	WorkServiceService workServiceService;

	@GetMapping("/all")
	public List<WorkService> getAll() {
		return workServiceService.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<WorkService> getWorkServiceDetailById(@PathVariable Long id) {

		WorkService workService = workServiceService.getServiceById(id);

		if (workService != null) {
			return ResponseEntity.ok(workService);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/")
	public ResponseEntity<WorkService> newWorkService(@RequestBody WorkService workService) {

		WorkService workServiceResult = workServiceService.saveService(workService);
		return ResponseEntity.ok(workServiceResult);

	}

}

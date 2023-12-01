package com.boutique.vehiculos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boutique.vehiculos.entity.WorkService;

@Repository("workServiceRepository")
public interface WorkServiceRepository extends JpaRepository<WorkService, Long> {

}

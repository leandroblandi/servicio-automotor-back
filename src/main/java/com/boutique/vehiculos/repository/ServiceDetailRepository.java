package com.boutique.vehiculos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boutique.vehiculos.entity.ServiceDetail;

@Repository("serviceDetailRepository")
public interface ServiceDetailRepository extends JpaRepository<ServiceDetail, Long> {

}

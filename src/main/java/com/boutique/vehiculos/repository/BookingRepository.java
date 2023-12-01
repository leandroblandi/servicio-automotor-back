package com.boutique.vehiculos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.boutique.vehiculos.entity.Booking;

@Repository("bookingRepository")
public interface BookingRepository extends JpaRepository<Booking, Long> {

	@Query("SELECT b FROM Booking b WHERE b.customer.customerId = :customerId")
	List<Booking> findBookingsByCustomerId(@Param("customerId") Long customerId);

	@Query("SELECT b FROM Booking b WHERE b.customer.isPremium = true")
	List<Booking> findBookingsByPremiumCustomers();

}

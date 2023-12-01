package com.boutique.vehiculos;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.boutique.vehiculos.entity.Customer;
import com.boutique.vehiculos.entity.ServiceDetail;
import com.boutique.vehiculos.entity.Vehicle;
import com.boutique.vehiculos.service.BookingService;
import com.boutique.vehiculos.service.CustomerService;
import com.boutique.vehiculos.service.ServiceDetailService;
import com.boutique.vehiculos.service.VehicleService;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class PruebaTecnicaApplication {

	@Autowired
	@Qualifier("serviceDetailService")
	ServiceDetailService serviceDetailService;

	@Autowired
	@Qualifier("customerService")
	CustomerService customerService;

	@Autowired
	@Qualifier("vehicleService")
	VehicleService vehicleService;

	@Autowired
	@Qualifier("bookingService")
	BookingService bookingService;

	private static final Logger LOGGER = Logger.getLogger(PruebaTecnicaApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(PruebaTecnicaApplication.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner commandLineRunner() {

		return args -> {

			LOGGER.info("Initial data begin to save");

			serviceDetailService.saveService(new ServiceDetail("Lavado Basico", 300f));
			serviceDetailService.saveService(new ServiceDetail("Lavado Completo", 500f));
			serviceDetailService.saveService(new ServiceDetail("Lavado Premium", 700f));
			serviceDetailService
					.saveService(new ServiceDetail("Alineacion y balanceo - SIN CAMBIO DE CUBIERTAS", 1500f));
			serviceDetailService
					.saveService(new ServiceDetail("Alineacion y balanceo - CON CAMBIO DE CUBIERTAS", 2300f));
			serviceDetailService.saveService(new ServiceDetail("Cambio de aceite y filtros - BASICO DIESEL", 2000f));
			serviceDetailService.saveService(new ServiceDetail("Cambio de aceite y filtros - PREMIUM DIESEL", 2800f));
			serviceDetailService.saveService(new ServiceDetail("Cambio de aceite y filtros - BASICO NAFTERO", 2000f));
			serviceDetailService.saveService(new ServiceDetail("Cambio de aceite y filtros - PREMIUM NAFTERO", 2800f));

			LOGGER.info("Services added successfully");

			customerService.saveCustomer(new Customer("Leandro", "Blandi", false));
			customerService.saveCustomer(new Customer("Javier", "Sanchez", false));
			customerService.saveCustomer(new Customer("Lucas", "Mont", false));
			customerService.saveCustomer(new Customer("Fernando", "Lopez", false));

			LOGGER.info("Customers added successfully");

			vehicleService.saveVehicle(new Vehicle("AAA111"));
			vehicleService.saveVehicle(new Vehicle("AAB112"));
			vehicleService.saveVehicle(new Vehicle("AAC113"));
			vehicleService.saveVehicle(new Vehicle("AAD114"));
			vehicleService.saveVehicle(new Vehicle("AAE115"));
			vehicleService.saveVehicle(new Vehicle("AAF116"));

			LOGGER.info("Vehicles added successfully");

			customerService.addVehicle(customerService.findById(1L), vehicleService.findById(1L));
			customerService.addVehicle(customerService.findById(1L), vehicleService.findById(2L));
			customerService.addVehicle(customerService.findById(2L), vehicleService.findById(3L));
			customerService.addVehicle(customerService.findById(3L), vehicleService.findById(4L));
			customerService.addVehicle(customerService.findById(4L), vehicleService.findById(5L));
			customerService.addVehicle(customerService.findById(4L), vehicleService.findById(6L));

			LOGGER.info("Vehicles linked to Customers successfully");

			LOGGER.info("Initial data saved successfully to the database");

		};
	}
}

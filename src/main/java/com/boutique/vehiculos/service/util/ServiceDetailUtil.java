package com.boutique.vehiculos.service.util;

import com.boutique.vehiculos.entity.Customer;
import com.boutique.vehiculos.entity.ServiceDetail;

public abstract class ServiceDetailUtil {

	public static ServiceDetail getDiscountIfPremium(Customer customer, ServiceDetail service) {
		if (customer.getIsPremium()) {
			float discountedPrice = service.getServicePrice() * 0.75f;
			service.setServicePrice(discountedPrice);
		}
		return service;
	}

}

package com.ethor.testbed.api.domain.customer;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "customers")
public class Customers {

	private List<Customer> customers;

	public List<Customer> getCustomers() {
		return customers;
	}

	@XmlElement(name = "customer")
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	public Customer getCustomerById(final String customerId) {
		if (customers != null) {
			for (Customer customer : customers) {
				if (customer.getId().equals(customerId)) {
					return customer;
				}
			}
		}
		return null;
	}

}

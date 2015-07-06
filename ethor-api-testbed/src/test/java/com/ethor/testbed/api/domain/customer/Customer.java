package com.ethor.testbed.api.domain.customer;

import javax.xml.bind.annotation.XmlType;

import com.ethor.testbed.api.domain.Entity;
import com.ethor.testbed.api.domain.order.OrderCustomer;

/**
 * This class encapsulates customer details in the reference spread sheet.
 * 
 * @author Roy Fernando.
 */
@XmlType
public class Customer extends OrderCustomer {

	private Address billingAddress;
	private PhoneNumbers phoneNumbers;

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(final Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public PhoneNumbers getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(PhoneNumbers phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
}

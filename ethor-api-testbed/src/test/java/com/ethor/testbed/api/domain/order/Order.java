package com.ethor.testbed.api.domain.order;

import java.util.List;

import com.ethor.testbed.api.domain.customer.Address;
import com.ethor.testbed.api.domain.customer.Customer;
import com.ethor.testbed.api.domain.customer.PhoneNumber;

/**
 * This class encapsulates an order.
 * 
 * @author Roy Fernando.
 */
public class Order {

	private String orderNumber;
	private Customer customer;
	private Address billingAddress;
	private Address deliveryAddress;
	private PhoneNumber phoneNumber;
	private String orderType;
	private Payment payment;
	private OrderSpecial specials;
	private String deliveryInstructions;
	private String orderDate;
	private String savings;
	private String subTotal;
	private String deliveryFee;
	private String totalTax;
	private String grandTotal;
	private Boolean isPaid;
	private OrderStatus orderStatus;

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(final String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(final Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(final Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(final PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(final String orderType) {
		this.orderType = orderType;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(final Payment payment) {
		this.payment = payment;
	}

	public OrderSpecial getSpecials() {
		return specials;
	}

	public void setSpecials(final OrderSpecial specials) {
		this.specials = specials;
	}

	public String getDeliveryInstructions() {
		return deliveryInstructions;
	}

	public void setDeliveryInstructions(final String deliveryInstructions) {
		this.deliveryInstructions = deliveryInstructions;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(final String orderDate) {
		this.orderDate = orderDate;
	}

	public String getSavings() {
		return savings;
	}

	public void setSavings(final String savings) {
		this.savings = savings;
	}

	public String getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(final String subTotal) {
		this.subTotal = subTotal;
	}

	public String getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(final String deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public String getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(final String totalTax) {
		this.totalTax = totalTax;
	}

	public String getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(final String grandTotal) {
		this.grandTotal = grandTotal;
	}

	public Boolean getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(final Boolean isPaid) {
		this.isPaid = isPaid;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(final OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

}

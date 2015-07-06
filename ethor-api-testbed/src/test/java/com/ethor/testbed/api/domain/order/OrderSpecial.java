package com.ethor.testbed.api.domain.order;

import java.util.List;

import com.ethor.testbed.api.domain.Entity;

/**
 * This class encapsulates special for an order.
 * 
 * @author Roy Fernando.
 */
public class OrderSpecial extends Entity {

	private String quantity;
	private List<OrderItem> items;
	private String total;

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(final String quantity) {
		this.quantity = quantity;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(final String total) {
		this.total = total;
	}

}

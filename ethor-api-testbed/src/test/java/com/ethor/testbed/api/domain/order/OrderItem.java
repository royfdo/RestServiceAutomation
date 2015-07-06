package com.ethor.testbed.api.domain.order;

import java.util.List;


/**
 * This class encapsulates order item.
 * 
 * @author Roy Fernando.
 */
public class OrderItem {

	private String menuItem;
	private String quantity;
	private String sizeId;
	private List<OrderContent> contents;
	private String total;

	public String getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(final String menuItem) {
		this.menuItem = menuItem;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(final String quantity) {
		this.quantity = quantity;
	}

	public String getSizeId() {
		return sizeId;
	}

	public void setSizeId(final String sizeId) {
		this.sizeId = sizeId;
	}

	public List<OrderContent> getContents() {
		return contents;
	}

	public void setContents(List<OrderContent> contents) {
		this.contents = contents;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(final String total) {
		this.total = total;
	}
}

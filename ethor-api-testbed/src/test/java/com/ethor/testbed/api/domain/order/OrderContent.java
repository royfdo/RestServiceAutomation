package com.ethor.testbed.api.domain.order;

import com.ethor.testbed.api.domain.Entity;

/**
 * This class encapsulates order content.
 * 
 * @author Roy Fernando.
 * 
 */
public class OrderContent extends Entity {

	private String portionId;
	private String amountId;

	public String getPortionId() {
		return portionId;
	}

	public void setPortionId(final String portionId) {
		this.portionId = portionId;
	}

	public String getAmountId() {
		return amountId;
	}

	public void setAmountId(final String amountId) {
		this.amountId = amountId;
	}

}

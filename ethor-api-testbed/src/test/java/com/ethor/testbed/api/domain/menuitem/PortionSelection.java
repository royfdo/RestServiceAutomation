package com.ethor.testbed.api.domain.menuitem;

import com.ethor.testbed.api.domain.Entity;

/**
 * This class encapsulates attributes and methods related to a selection of the topping portion
 * 
 * @author Roy Fernando.
 */
public class PortionSelection extends Entity {

	private String name;
	private Integer displayOrder;
	private Boolean isSelected;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(final Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public Boolean isSelected() {
		return isSelected;
	}

	public void setSelected(final Boolean isSelected) {
		this.isSelected = isSelected;
	}

}

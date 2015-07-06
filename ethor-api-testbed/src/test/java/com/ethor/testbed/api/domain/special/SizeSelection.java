package com.ethor.testbed.api.domain.special;

import com.ethor.testbed.api.domain.Entity;

/**
 * This class encapsulates size selections.
 * 
 * @author Roy Fernando.
 */
public class SizeSelection extends Entity {

	private String name;
	private int displayOrder;
	private String groupName;
	private int groupDisplayOrder;
	private boolean selected;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(final int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(final String groupName) {
		this.groupName = groupName;
	}

	public int getGroupDisplayOrder() {
		return groupDisplayOrder;
	}

	public void setGroupDisplayOrder(final int groupDisplayOrder) {
		this.groupDisplayOrder = groupDisplayOrder;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(final boolean selected) {
		this.selected = selected;
	}

}

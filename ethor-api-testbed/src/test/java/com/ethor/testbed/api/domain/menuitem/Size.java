package com.ethor.testbed.api.domain.menuitem;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.ethor.testbed.api.domain.Entity;

/**
 * This class encapsulate attributes of size object.
 * 
 * @author Roy Fernando.
 * 
 */
public class Size extends Entity {

	@JsonIgnore
	private String groupName;
	@JsonIgnore
	private Integer groupDisplayOrder;
	private String name;
	private Integer displayOrder;
	@JsonIgnore
	private Boolean isSelected;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(final String groupName) {
		this.groupName = groupName;
	}

	public Integer getGroupDisplayOrder() {
		return groupDisplayOrder;
	}

	public void setGroupDisplayOrder(final Integer groupDisplayOrder) {
		this.groupDisplayOrder = groupDisplayOrder;
	}

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

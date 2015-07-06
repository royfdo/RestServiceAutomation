package com.ethor.testbed.api.domain.special;

import java.util.List;

import com.ethor.testbed.api.domain.Entity;


/**
 * This class encapsulates attributes and methods of special items.
 * 
 * @author Roy Fernando.
 */
public class SpecialItems extends Entity{

	private String name;
	private String description;
	private Integer displayOrder;
	private String image;
	private Integer includedContents;
	private Integer minQuantity;
	private List<ItemSelections> itemSelections;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(final Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getImage() {
		return image;
	}

	public void setImage(final String image) {
		this.image = image;
	}

	public Integer getIncludedContents() {
		return includedContents;
	}

	public void setIncludedContents(final Integer includedContents) {
		this.includedContents = includedContents;
	}

	public Integer getMinQuantity() {
		return minQuantity;
	}

	public void setMinQuantity(final Integer minQuantity) {
		this.minQuantity = minQuantity;
	}

	public List<ItemSelections> getItemSelections() {
		return itemSelections;
	}

	public void setItemSelections(final List<ItemSelections> itemSelections) {
		this.itemSelections = itemSelections;
	}

}

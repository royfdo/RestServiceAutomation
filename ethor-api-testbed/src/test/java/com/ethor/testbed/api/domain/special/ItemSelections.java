package com.ethor.testbed.api.domain.special;

/**
 * This class encapsulates item selections
 * 
 * @author Roy Fernando.
 */
public class ItemSelections {

	private String menuItem;
	private SizeSelections sizeSelections;

	public String getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(final String menuItem) {
		this.menuItem = menuItem;
	}

	public SizeSelections getSizeSelections() {
		return sizeSelections;
	}

	public void setSizeSelections(final SizeSelections sizeSelections) {
		this.sizeSelections = sizeSelections;
	}

}

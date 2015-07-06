package com.ethor.testbed.api.domain.menuitem;

import java.util.ArrayList;
import java.util.List;

import com.ethor.testbed.api.domain.Entity;

/**
 * This class encapsulates Contents related attributes and methods.
 * 
 * @author Roy Fernando.
 */
public class Contents extends Entity {

	private String groupName;
	private int groupDisplayOrder;
	private String name;
	private int displayOrder;
	private String extraInfo;
	private List<PortionSelection> portionSelections;
	private List<AmountSelection> amountSelections;

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

	public String getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(final String extraInfo) {
		this.extraInfo = extraInfo;
	}

	public List<PortionSelection> getPortionSelections() {
		return portionSelections;
	}
	
	public List<String> getPortionSelectionsAsStringList() {
		List<String> list = new ArrayList<String>();
		if (portionSelections != null && !portionSelections.isEmpty()) {
			for (PortionSelection selection : portionSelections) {
				list.add(selection.getName());
			}
		}

		return list;
	}

	public void setPortionSelections(final List<PortionSelection> portionSelections) {
		this.portionSelections = portionSelections;
	}

	public List<AmountSelection> getAmountSelections() {
		return amountSelections;
	}
	
	public List<String> getAmountSelectionsAsStringList() {
		List<String> list = new ArrayList<String>();
		if (amountSelections != null && !amountSelections.isEmpty()) {
			for (AmountSelection selection : amountSelections) {
				list.add(selection.getName());
			}
		}

		return list;
	}

	public void setAmountSelections(final List<AmountSelection> amountSelections) {
		this.amountSelections = amountSelections;
	}
	
}

package com.ethor.testbed.api.domain.menuitem;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * This class encapsulates list of menu items.
 * 
 * @author Roy Fernando.
 */
@XmlRootElement(name = "menuItems")
public class MenuItems {

	@JsonProperty(value = "menuItems")
	private List<MenuItem> menuItems;

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	@XmlElement(name = "menuItem")
	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

}

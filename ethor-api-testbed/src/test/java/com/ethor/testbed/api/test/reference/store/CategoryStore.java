package com.ethor.testbed.api.test.reference.store;

import java.util.ArrayList;
import java.util.List;

import com.ethor.testbed.api.domain.category.Category;
import com.ethor.testbed.api.domain.menuitem.MenuItem;

/**
 * This class stores received category details.
 * 
 * @author Roy Fernando.
 */
public class CategoryStore {

	private final Category category;
	private List<MenuItem> menuItems = new ArrayList<MenuItem>();

	public CategoryStore(final Category category) {
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(final List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}
}

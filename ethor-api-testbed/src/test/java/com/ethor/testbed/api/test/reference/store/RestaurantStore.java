package com.ethor.testbed.api.test.reference.store;

import java.util.ArrayList;
import java.util.List;

import com.ethor.testbed.api.domain.restaurant.Restaurant;
import com.ethor.testbed.api.domain.special.Special;

/**
 * This class stores received restaurant details.
 * 
 * @author Roy Fernando.
 */
public class RestaurantStore {

	private final Restaurant restaurant;
	private List<CategoryStore> categoryStores = new ArrayList<CategoryStore>();
	private List<Special> specials = new ArrayList<Special>();

	public RestaurantStore(final Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public List<CategoryStore> getCategoryStores() {
		return categoryStores;
	}

	public void setCategoryStores(final List<CategoryStore> categoryStores) {
		this.categoryStores = categoryStores;
	}

	public List<Special> getSpecials() {
		return specials;
	}

	public void setSpecials(final List<Special> specials) {
		this.specials = specials;
	}
	
	public CategoryStore getCategoryStoreByCategoryName(final String categoryName) {
		for (CategoryStore categoryStore : categoryStores) {
			if (categoryStore.getCategory().getName().equals(categoryName)) {
				return categoryStore;
			}
		}
		throw new IllegalArgumentException("Category not found for category name <" + categoryName + ">");
	}

}

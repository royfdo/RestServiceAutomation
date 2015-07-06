package com.ethor.testbed.api.test.reference.store;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ethor.testbed.api.domain.chain.Chain;

/**
 * This class stores received chain details.
 * 
 * @author Roy Fernando.
 */
public class ChainStore {

	private final Chain chain;
	private List<RestaurantStore> restaurantStores = new ArrayList<RestaurantStore>();

	public ChainStore(final Chain chain) {
		this.chain = chain;
	}

	public Chain getChain() {
		return chain;
	}

	public List<RestaurantStore> getRestaurantStores() {
		return restaurantStores;
	}

	public void setRestaurantStores(final List<RestaurantStore> restaurantStores) {
		this.restaurantStores = restaurantStores;
	}
	
	public RestaurantStore getRestaurantStoreByRestaurantId(final String resturantId) {
		for (RestaurantStore restaurantStore : restaurantStores) {
			if (restaurantStore.getRestaurant().getId().equals(resturantId)) {
				return restaurantStore;
			}
		}
		throw new IllegalArgumentException("Restaurant not found for resturant id <" + resturantId + ">");
	}

}

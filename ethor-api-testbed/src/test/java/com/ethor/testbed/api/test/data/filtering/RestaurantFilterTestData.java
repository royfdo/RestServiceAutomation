package com.ethor.testbed.api.test.data.filtering;

import java.util.ArrayList;
import java.util.List;

public class RestaurantFilterTestData extends AbstractFilterTestData {

	private List<String> expectedRestaurants = new ArrayList<String>();

	public List<String> getExpectedRestaurants() {
		return expectedRestaurants;
	}
	public void setExpectedRestaurants(final List<String> expectedRestaurants) {
		this.expectedRestaurants = expectedRestaurants;
	}

	

}

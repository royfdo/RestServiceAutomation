package com.ethor.testbed.api.test.data.filtering;

import java.util.ArrayList;
import java.util.List;

public class SpecialsFilterTestData extends AbstractFilterTestData {

	private String restaurantId;
	private List<String> expectedSpecials = new ArrayList<String>();

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(final String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public List<String> getExpectedSpecials() {
		return expectedSpecials;
	}

	public void setExpectedSpecials(final List<String> expectedSpecials) {
		this.expectedSpecials = expectedSpecials;
	}

	

}

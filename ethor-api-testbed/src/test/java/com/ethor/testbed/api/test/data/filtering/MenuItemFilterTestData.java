package com.ethor.testbed.api.test.data.filtering;

import java.util.ArrayList;
import java.util.List;

public class MenuItemFilterTestData extends AbstractFilterTestData {

	private String restaurantId;
	private String categoryId;
	private List<String> expectedContent = new ArrayList<String>();
	private List<String> expectedSizes = new ArrayList<String>();

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(final String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(final String categoryId) {
		this.categoryId = categoryId;
	}

	public List<String> getExpectedContent() {
		return expectedContent;
	}

	public void setExpectedContent(final List<String> expectedContent) {
		this.expectedContent = expectedContent;
	}

	public void setExpectedSizes(final List<String> expectedSizes) {
		this.expectedSizes = expectedSizes;
	}

	public List<String> getExpectedSizes() {
		return expectedSizes;
	}


}

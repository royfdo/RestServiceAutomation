package com.ethor.testbed.api.test.restaurant;

import static junit.framework.Assert.assertEquals;

import org.junit.Before;

import com.ethor.testbed.api.domain.Entity;
import com.ethor.testbed.api.domain.chain.Chain;
import com.ethor.testbed.api.domain.restaurant.Restaurant;
import com.ethor.testbed.api.domain.restaurant.RestaurantStatus;
import com.ethor.testbed.api.domain.restaurant.Restaurants;
import com.ethor.testbed.api.test.AbstractAPITest;

/**
 * Abstract test class for restaurants.
 * 
 * @author Roy Fernando.
 */
public abstract class AbstractRestaurantTest extends AbstractAPITest {

	protected static final String INVALID_RESTAURANT_ID = "-1";

	@Before
	public void setUp() {
		setXmlMarshaller(Entity.class, Restaurants.class, Restaurant.class, RestaurantStatus.class);
	}

	protected void validateRestaurant(final Restaurant restaurant) {
		Chain chainToBeTest = (Chain) getTestSession().get("CHAIN_TO_BE_TEST");
		assertEquals("Invalid chain id", getBaseUrl() + "/chains/" + chainToBeTest.getId(), restaurant.getChainId());
		assertEquals("invalid number of Store opening times", 7, restaurant.getOpenTime().size());
		assertEquals("invalid number of Store closing times", 7, restaurant.getCloseTime().size());

		// TODO other validations
	}

}

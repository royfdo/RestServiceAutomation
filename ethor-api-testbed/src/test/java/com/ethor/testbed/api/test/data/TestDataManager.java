package com.ethor.testbed.api.test.data;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ethor.testbed.api.domain.category.Categories;
import com.ethor.testbed.api.domain.chain.Chains;
import com.ethor.testbed.api.domain.customer.Customers;
import com.ethor.testbed.api.domain.menuitem.MenuItems;
import com.ethor.testbed.api.domain.order.Order;
import com.ethor.testbed.api.domain.restaurant.Restaurants;
import com.ethor.testbed.api.test.data.filtering.MenuItemFilterTestData;
import com.ethor.testbed.api.test.data.filtering.RestaurantFilterTestData;
import com.ethor.testbed.api.test.data.filtering.SpecialsFilterTestData;

/**
 * Test data manager interface.
 * 
 * @author Roy Fernando.
 * 
 */
public interface TestDataManager {

	/**
	 * Reads reference test data from spread sheet and return as TestData
	 * instance.
	 * 
	 * @return TestData instance.
	 * @throws IOException
	 *             could be thrown.
	 */
	List<Order> getOrderTestData() throws IOException;
	
	Chains getChainsTestData() throws IOException;

	Restaurants getRestaurantTestData() throws IOException;
	
	Categories getCategoryTestData() throws IOException;
	
	MenuItems getMenuItemsTestData() throws IOException;
	
	Customers getCustomerTestData() throws IOException;
	
	List<RestaurantFilterTestData> getRestaurantFilterTestData() throws IOException;	
	
	List<SpecialsFilterTestData> getSpecialsFilterTestData() throws IOException;
	
	List<MenuItemFilterTestData> getMenuItemFilterTestData() throws IOException;

	Map<String, List<String>> getCustomerChainMapping() throws IOException;

}

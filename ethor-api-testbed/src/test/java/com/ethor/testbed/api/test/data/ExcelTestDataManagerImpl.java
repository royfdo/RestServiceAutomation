package com.ethor.testbed.api.test.data;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
 * Reads reference data set from the spread sheet.
 * 
 * @author Roy Fernando.
 */
public class ExcelTestDataManagerImpl implements TestDataManager {

	private static XSSFWorkbook xssfwb;
	private static SXSSFWorkbook workbook;
	
	private static XSSFWorkbook xssfwbForMenuItems;
	private static SXSSFWorkbook workbookForMenuItems;
	
	private static XSSFWorkbook xssfwbForFilter;
	private static SXSSFWorkbook workbookForFilter;
	
	private static XSSFWorkbook xssfwbForOrders;
	private static SXSSFWorkbook workbookForOrders;

	static {
		try {
			xssfwb = new XSSFWorkbook(ClassLoader.getSystemResourceAsStream("testData.xlsx"));
			workbook = new SXSSFWorkbook(xssfwb);
			
			xssfwbForMenuItems = new XSSFWorkbook(ClassLoader.getSystemResourceAsStream("MenuItemTestData.xlsx"));
			workbookForMenuItems = new SXSSFWorkbook(xssfwbForMenuItems);
			
			xssfwbForFilter = new XSSFWorkbook(ClassLoader.getSystemResourceAsStream("filter-testData.xlsx"));
			workbookForFilter = new SXSSFWorkbook(xssfwbForFilter);
			
			xssfwbForOrders = new XSSFWorkbook(ClassLoader.getSystemResourceAsStream("order-testData.xlsx"));
			workbookForOrders = new SXSSFWorkbook(xssfwbForOrders);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private RestaurantTestDataReader restaurantTestDataReader;
	private ChainTestDataReader chainTestDataReader;
	private CategoryTestDataReader categoryTestDataReader;
	private CustomerTestDataReader customerTestDataReader;
	private MenuItemTestDataReader menuItemTestDataReader;
	private CustomerChainMapDataReader customerChainMapDataReader;
	private RestaurantFilterTestDataReader restaurantFilterTestDataReader;
	private SpecialsFilterTestDataReader specialsFilterTestDataReader;
	private MenuItemFilterTestDataReader menuItemFilterTestDataReader;
	private OrderTestDataReader orderTestDataReader;

	@Override
	public List<Order> getOrderTestData() throws IOException {
		return orderTestDataReader.readData(workbook.getXSSFWorkbook());
	}

	@Override
	public Restaurants getRestaurantTestData() throws IOException {
		return restaurantTestDataReader.readData(workbook.getXSSFWorkbook());
	}

	@Override
	public Customers getCustomerTestData() throws IOException {
		return customerTestDataReader.readData(workbookForOrders.getXSSFWorkbook());
	}

	@Override
	public Map<String, List<String>> getCustomerChainMapping() throws IOException {
		return customerChainMapDataReader.readData(workbook.getXSSFWorkbook());
	}

	@Override
	public List<RestaurantFilterTestData> getRestaurantFilterTestData() throws IOException {
		return restaurantFilterTestDataReader.readData(workbookForFilter.getXSSFWorkbook());
	}

	@Override
	public List<SpecialsFilterTestData> getSpecialsFilterTestData() throws IOException {
		return specialsFilterTestDataReader.readData(workbookForFilter.getXSSFWorkbook());
	}

	@Override
	public Chains getChainsTestData() throws IOException {
		return chainTestDataReader.readData(workbook.getXSSFWorkbook());
	}

	@Override
	public Categories getCategoryTestData() throws IOException {
		return categoryTestDataReader.readData(workbook.getXSSFWorkbook());
	}
	
	@Override
	public MenuItems getMenuItemsTestData() throws IOException {
		return menuItemTestDataReader.readData(workbookForMenuItems.getXSSFWorkbook());
	}

	@Override
	public List<MenuItemFilterTestData> getMenuItemFilterTestData() throws IOException {
		return menuItemFilterTestDataReader.readData(workbookForFilter.getXSSFWorkbook());
	}

	/*
	 * public static void main(String[] args) throws IOException { new
	 * ExcelTestDataManagerImpl().getCustomerChainMapping(); }
	 */

	public void setRestaurantTestDataReader(final RestaurantTestDataReader restaurantTestDataReader) {
		this.restaurantTestDataReader = restaurantTestDataReader;
	}

	public void setChainTestDataReader(final ChainTestDataReader chainTestDataReader) {
		this.chainTestDataReader = chainTestDataReader;
	}

	public void setCustomerTestDataReader(final CustomerTestDataReader customerTestDataReader) {
		this.customerTestDataReader = customerTestDataReader;
	}

	public void setCustomerChainMapDataReader(final CustomerChainMapDataReader customerChainMapDataReader) {
		this.customerChainMapDataReader = customerChainMapDataReader;
	}

	public void setRestaurantFilterTestDataReader(RestaurantFilterTestDataReader restaurantFilterTestDataReader) {
		this.restaurantFilterTestDataReader = restaurantFilterTestDataReader;
	}

	public void setSpecialsFilterTestDataReader(final SpecialsFilterTestDataReader specialsFilterTestDataReader) {
		this.specialsFilterTestDataReader = specialsFilterTestDataReader;
	}

	public void setCategoryTestDataReader(final CategoryTestDataReader categoryTestDataReader) {
		this.categoryTestDataReader = categoryTestDataReader;
	}

	public void setMenuItemFilterTestDataReader(final MenuItemFilterTestDataReader menuItemFilterTestDataReader) {
		this.menuItemFilterTestDataReader = menuItemFilterTestDataReader;
	}

	public void setOrderTestDataReader(final OrderTestDataReader orderTestDataReader) {
		this.orderTestDataReader = orderTestDataReader;
	}

	public void setMenuItemTestDataReader(final MenuItemTestDataReader menuItemTestDataReader) {
		this.menuItemTestDataReader = menuItemTestDataReader;
	}
}

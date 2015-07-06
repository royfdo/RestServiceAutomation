package com.ethor.testbed.api.test.menuitem;

import static com.ethor.testbed.api.test.Assert.assertSame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.http.ResponseEntity;

import com.ethor.testbed.api.domain.category.Categories;
import com.ethor.testbed.api.domain.category.Category;
import com.ethor.testbed.api.domain.chain.Chain;
import com.ethor.testbed.api.domain.chain.Chains;
import com.ethor.testbed.api.domain.menuitem.ContentGroups;
import com.ethor.testbed.api.domain.menuitem.Contents;
import com.ethor.testbed.api.domain.menuitem.MenuItem;
import com.ethor.testbed.api.domain.menuitem.MenuItems;
import com.ethor.testbed.api.domain.menuitem.Size;
import com.ethor.testbed.api.domain.restaurant.Restaurant;
import com.ethor.testbed.api.domain.restaurant.Restaurants;
import com.ethor.testbed.api.test.AbstractAPITest;
import com.ethor.testbed.api.test.data.TestDataManager;
import com.ethor.testbed.api.test.data.UnExpectedValue;
import com.ethor.testbed.api.test.data.filtering.MenuItemFilterTestData;
import com.ethor.testbed.api.test.report.TestFormat;
import com.ethor.testbed.api.test.report.TestModule;
import com.ethor.testbed.api.test.report.TestResult;
import com.ethor.testbed.api.test.report.TestStatus;

public class MenuItemFilterTest extends AbstractAPITest {

	private TestDataManager testDataManager;

	public void filter() throws IOException {
		List<MenuItemFilterTestData> filterTestData = testDataManager.getMenuItemFilterTestData();
		filter(TestFormat.JSON, filterTestData);
		filter(TestFormat.XML, filterTestData);
	}

	private void filter(final TestFormat format, List<MenuItemFilterTestData> filterTestData) {
		Chains chainTestData = (Chains) getTestSession().get("CHAIN_TEST_DATA");
		Restaurants restaurantTestData = (Restaurants) getTestSession().get("RESTAURANT_TEST_DATA");
		Categories CategoryTestData = (Categories) getTestSession().get("CATEGORY_TEST_DATA");

		for (MenuItemFilterTestData testData : filterTestData) {
			ResponseEntity<MenuItems> responseEntity = null;
			TestResult testResult = initializeTestResults("filter menuItems by "
					+ testData.getFilterDataMap().keySet().toString(), format, TestModule.MENUITEM_FILTER);

			Chain testChain = chainTestData.getChainById(testData.getChainId());
			Restaurant testRestaurant = restaurantTestData.getRestaurantById(testData.getRestaurantId());
			Category testCategory = CategoryTestData.getCategoryById(testData.getCategoryId());

			testResult.addRequestParam("ChainId",
					testData.getChainId() + (testChain != null ? "[" + testChain.getName() + "]" : ""));
			testResult.addRequestParam("RestaurantId", testData.getRestaurantId()
					+ (testRestaurant != null ? "[" + testRestaurant.getStreet() + "]" : ""));
			testResult.addRequestParam("CategoryId", testData.getCategoryId()
					+ (testCategory != null ? "[" + testCategory.getName() + "]" : ""));

			String url = getUrl("/chains/" + testData.getChainId() + "/restaurants/" + testData.getRestaurantId()
					+ "/categories/" + testData.getCategoryId() + "/menuitems." + format.toString().toLowerCase() + "?"
					+ getQueryString(testData.getFilterDataMap(), testResult) + "accessKey=" + getAccessKey());
			testResult.setUrl(url);
			responseEntity = getTemplate().getForEntity(url, MenuItems.class);
			testResult.setStatus(TestStatus.PASS);

			List<String> sizesList = new ArrayList<String>();
			List<String> contenList = new ArrayList<String>();
			for (MenuItem menuItem : responseEntity.getBody().getMenuItems()) {
				for (Size size : menuItem.getSizes()) {
					sizesList.add(size.getName().trim());
				}
				for (ContentGroups contentGroups : menuItem.getContentGroups()) {
					for (Contents contents : contentGroups.getContents()) {
						contenList.add(contents.getName().trim());
					}
				}
				break;

			}

			List<UnExpectedValue> unExpectedValues = new ArrayList<UnExpectedValue>();
			assertSame(testData.getExpectedSizes(), sizesList, "menuitem.sizes", unExpectedValues);
			assertSame(testData.getExpectedContent(), contenList, "menuitem.contents", unExpectedValues);

			if (!unExpectedValues.isEmpty()) {
				testResult.setStatus(TestStatus.FAIL);
				testResult.buildUnExpectedValueOutput(unExpectedValues);
			} else {
				testResult.addTestDetails("Successful");
			}

		}

	}

	private String getQueryString(Map<String, String> filterValues, final TestResult testResult) {
		StringBuilder builder = new StringBuilder();
		for (Entry<String, String> entry : filterValues.entrySet()) {
			builder.append(entry.getKey().trim());
			builder.append("=");
			builder.append(entry.getValue().trim());
			builder.append("&");

			testResult.addRequestParam(entry.getKey(), entry.getValue());
		}
		return builder.toString();
	}

	public void setTestDataManager(TestDataManager testDataManager) {
		this.testDataManager = testDataManager;
	}
	
	

}

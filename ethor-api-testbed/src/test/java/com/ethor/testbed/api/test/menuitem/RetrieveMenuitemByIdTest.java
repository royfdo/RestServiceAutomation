package com.ethor.testbed.api.test.menuitem;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ethor.testbed.api.domain.menuitem.MenuItem;
import com.ethor.testbed.api.domain.menuitem.MenuItems;
import com.ethor.testbed.api.test.AbstractAPITest;
import com.ethor.testbed.api.test.data.TestDataManager;
import com.ethor.testbed.api.test.data.UnExpectedValue;
import com.ethor.testbed.api.test.report.TestFormat;
import com.ethor.testbed.api.test.report.TestModule;
import com.ethor.testbed.api.test.report.TestResult;
import com.ethor.testbed.api.test.report.TestStatus;

/**
 * Retrieve menuitems API test.
 * 
 * @author Roy Fernando.
 */
public class RetrieveMenuitemByIdTest extends AbstractAPITest {

	private TestDataManager testDataManager;

	public void setTestDataManager(final TestDataManager testDataManager) {
		this.testDataManager = testDataManager;
	}

	public void retrieveMenuItems() throws IOException {
		MenuItems testMenuItems = testDataManager.getMenuItemsTestData();
		
		retrieve(TestFormat.JSON, testMenuItems);
		retrieve(TestFormat.XML, testMenuItems);
	}

	public void retrieve(final TestFormat format, MenuItems testMenuItems) {
		
		for (MenuItem testMenuItem : testMenuItems.getMenuItems()) {
			TestResult testResult = initializeTestResults("Retrieve menuItems for given id", format,
					TestModule.MENUITEM_BYID);
			
			testResult.addRequestParam("chainId", testMenuItem.getMetaDataByKey("ChainName"));
			testResult.addRequestParam("restaurantId", testMenuItem.getMetaDataByKey("RestaurantName"));
			testResult.addRequestParam("categoryId", testMenuItem.getMetaDataByKey("CategoryName"));
			testResult.addRequestParam("menuItemId", testMenuItem.getName());
			String url = getUrl("chains/" + testMenuItem.getMetaDataByKey("ChainId") + "/restaurants/"
					+ testMenuItem.getMetaDataByKey("RestaurantId") + "/categories/"
					+ testMenuItem.getMetaDataByKey("CategoryId") + "/menuitems/" +  testMenuItem.getId() + "."
					+ format.toString().toLowerCase() + "?accessKey=" + getAccessKey());
			testResult.setUrl(url);
			
			try {
				ResponseEntity<MenuItem> responseEntity = getTemplate().getForEntity(url, MenuItem.class);

				validateResponse(responseEntity.getStatusCode());
				validateResponseType(format, responseEntity.getHeaders().getContentType());

				List<UnExpectedValue> unExpectedValues = MenuItemComparator.compare(testMenuItem,
						responseEntity.getBody());
				if (!unExpectedValues.isEmpty()) {
					testResult.setStatus(TestStatus.FAIL);
					testResult.buildUnExpectedValueOutput(unExpectedValues);
				} else {
					testResult.addTestDetails("Successful");
				}

			} catch (Exception e) {
				handleExceptions(testResult, e);
			}
		}
	}
}
package com.ethor.testbed.api.test.menuitem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ethor.testbed.APIResponseErrorHandler;
import com.ethor.testbed.api.domain.menuitem.MenuItem;
import com.ethor.testbed.api.domain.menuitem.MenuItems;
import com.ethor.testbed.api.test.AbstractAPITest;
import com.ethor.testbed.api.test.exception.ValidationException;
import com.ethor.testbed.api.test.reference.store.CategoryStore;
import com.ethor.testbed.api.test.reference.store.ChainStore;
import com.ethor.testbed.api.test.reference.store.ReferenceStore;
import com.ethor.testbed.api.test.reference.store.RestaurantStore;
import com.ethor.testbed.api.test.report.TestFormat;
import com.ethor.testbed.api.test.report.TestModule;
import com.ethor.testbed.api.test.report.TestResult;
import com.ethor.testbed.api.test.report.TestStatus;

/**
 * Retrieve menuitems API test.
 * 
 * @author Roy Fernando.
 */
public class RetrieveMenuitemsTest extends AbstractAPITest {

	public void retrieveMenuItems() {
		retrieve(TestFormat.JSON);
		retrieve(TestFormat.XML);
	}

	public void retrieve(final TestFormat format) {

		ReferenceStore referenceStore = (ReferenceStore) getTestSession().get(REFERENCE_STORE);
		if (referenceStore != null) {
			getTemplate().setErrorHandler(new APIResponseErrorHandler());
			for (ChainStore chainStore : referenceStore.getChainStores()) {

				for (RestaurantStore restaurantStore : chainStore.getRestaurantStores()) {

					for (CategoryStore categoryStores : restaurantStore.getCategoryStores()) {

						TestResult testResult = initializeTestResults(
								"Retrieve menuitems for given chain , restaurant and category", format,
								TestModule.MENUITEM);
						testResult.addRequestParam("chainId", "[" + chainStore.getChain().getId() + "], "
								+ chainStore.getChain().getName());
						testResult.addRequestParam("restaurantId", "[" + restaurantStore.getRestaurant().getId()
								+ "], " + restaurantStore.getRestaurant().getStreet());
						testResult.addRequestParam("categoryId", "[" + categoryStores.getCategory().getId() + "], "
								+ categoryStores.getCategory().getName());
						String url = getUrl("chains/" + chainStore.getChain().getId() + "/restaurants/"
								+ restaurantStore.getRestaurant().getId() + "/categories/"
								+ categoryStores.getCategory().getId() + "/menuitems."
								+ format.toString().toLowerCase() + "?accessKey=" + getAccessKey());
						testResult.setUrl(url);
						try {
							ResponseEntity<MenuItems> responseEntity = getTemplate().getForEntity(url, MenuItems.class);

							validateResponse(responseEntity.getStatusCode());
							validateResponseType(format, responseEntity.getHeaders().getContentType());

							List<String> menuItemIds = new ArrayList<String>();
							List<String> validationErrors = new ArrayList<String>();

							for (MenuItem menuItem : responseEntity.getBody().getMenuItems()) {
								if (menuItemIds.contains(menuItem.getId())) {
									throw new ValidationException("Duplicate category id, category id must be unique");
								}
								menuItemIds.add(menuItem.getId());
							}

							categoryStores.setMenuItems(responseEntity.getBody().getMenuItems());
							updateTestResults(testResult, responseEntity);

							if (!validationErrors.isEmpty()) {
								throw new com.ethor.testbed.api.test.exception.ValidationException(validationErrors);
							}

						} catch (Exception e) {
							handleExceptions(testResult, e);
						}

					}

				}
			}
		}

	}

	private void updateTestResults(final TestResult testResult, final ResponseEntity<MenuItems> responseEntity) {
		if (responseEntity.getBody().getMenuItems().isEmpty()) {
			testResult.setStatus(TestStatus.FAIL);
			testResult.addTestDetails("MenuItems not found");
		} else {
			StringBuilder menuItemsString = new StringBuilder();
			for (int i = 0; i < responseEntity.getBody().getMenuItems().size(); i++) {
				menuItemsString.append("[");
				menuItemsString.append(responseEntity.getBody().getMenuItems().get(i).getName());
				menuItemsString.append("]");
				if (i != responseEntity.getBody().getMenuItems().size() - 1) {
					menuItemsString.append(", ");
				}
			}
			testResult.addTestDetails("MenuItems retrieved : " + menuItemsString.toString());
		}
	}
}
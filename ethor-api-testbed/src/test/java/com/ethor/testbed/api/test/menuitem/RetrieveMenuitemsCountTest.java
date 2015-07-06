package com.ethor.testbed.api.test.menuitem;

import static com.ethor.testbed.api.test.Assert.assertNotNull;
import static com.ethor.testbed.api.test.Assert.assertTrue;

import org.springframework.http.ResponseEntity;

import com.ethor.testbed.api.domain.Count;
import com.ethor.testbed.api.test.AbstractAPITest;
import com.ethor.testbed.api.test.reference.store.CategoryStore;
import com.ethor.testbed.api.test.reference.store.ChainStore;
import com.ethor.testbed.api.test.reference.store.ReferenceStore;
import com.ethor.testbed.api.test.reference.store.RestaurantStore;
import com.ethor.testbed.api.test.report.TestFormat;
import com.ethor.testbed.api.test.report.TestModule;
import com.ethor.testbed.api.test.report.TestResult;

public class RetrieveMenuitemsCountTest extends AbstractAPITest {

	public void retrieveMenuitemsCount() {
		retrieve(TestFormat.JSON);
		retrieve(TestFormat.XML);
	}

	private void retrieve(final TestFormat format) {
		ReferenceStore referenceStore = (ReferenceStore) getTestSession().get(REFERENCE_STORE);
		if (referenceStore != null) {
			for (ChainStore chainStore : referenceStore.getChainStores()) {
				for (RestaurantStore restaurantStore : chainStore.getRestaurantStores()) {
					for (CategoryStore categoryStores : restaurantStore.getCategoryStores()) {
						TestResult testResult = initializeTestResults("Retrieve menuitem count for chain - "
								+ chainStore.getChain().getName() + ", restaurant - "
								+ restaurantStore.getRestaurant().getStreet() + " & category - "
								+ categoryStores.getCategory().getName(), format, TestModule.MENUITEM_COUNT);

						String url = getUrl("chains/" + chainStore.getChain().getId() + "/restaurants/"
								+ restaurantStore.getRestaurant().getId() + "/categories/"
								+ categoryStores.getCategory().getId() + "/menuitems/count."
								+ format.toString().toLowerCase() + "?accessKey=" + getAccessKey());
						testResult.setUrl(url);

						testResult.addRequestParam("chainId", "[" + chainStore.getChain().getId() + "], "
								+ chainStore.getChain().getName());
						testResult.addRequestParam("restaurantId", "[" + restaurantStore.getRestaurant().getId()
								+ "], " + restaurantStore.getRestaurant().getStreet());
						testResult.addRequestParam("categoryId", "[" + categoryStores.getCategory().getId() + "], "
								+ categoryStores.getCategory().getName());

						try {
							ResponseEntity<Count> responseEntity = getTemplate().getForEntity(url, Count.class);
							validateResponseType(format, responseEntity.getHeaders().getContentType());
							Count menuItemCount = responseEntity.getBody();
							validateMenuItemCount(menuItemCount);
							testResult.addTestDetails("Retrieved Menuitem count : " + menuItemCount.getCount());
						} catch (Exception e) {
							handleExceptions(testResult, e);
						}
					}
				}
			}
		}
	}

	private void validateMenuItemCount(final Count specialCount) {
		assertNotNull(specialCount.getCount(), "Menuitem count may not be null");
		assertTrue(specialCount.getCount() >= 0, "Menuitem count may not be a negative number");
	}

}

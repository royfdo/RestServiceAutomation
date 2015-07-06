package com.ethor.testbed.api.test.category;

import static com.ethor.testbed.api.test.Assert.assertNotNull;
import static com.ethor.testbed.api.test.Assert.assertTrue;

import org.springframework.http.ResponseEntity;

import com.ethor.testbed.api.domain.Count;
import com.ethor.testbed.api.test.reference.store.ChainStore;
import com.ethor.testbed.api.test.reference.store.ReferenceStore;
import com.ethor.testbed.api.test.reference.store.RestaurantStore;
import com.ethor.testbed.api.test.report.TestFormat;
import com.ethor.testbed.api.test.report.TestModule;
import com.ethor.testbed.api.test.report.TestResult;

/**
 * Retrieve categories count API test.
 * 
 * @author Roy Fernando.
 */
public class RetrieveCategoriesCountTest extends AbstractCategoryTest {

	/**
	 * Requesting categories count as json.
	 */
	public void retrieveCategoriesCount() {
		retrieve(TestFormat.JSON);
		retrieve(TestFormat.XML);
	}

	private void retrieve(final TestFormat format) {
		if (getAccessKey() != null) {
			ReferenceStore referenceStore = (ReferenceStore) getTestSession().get(REFERENCE_STORE);
			if (referenceStore != null) {
				for (ChainStore chainStore : referenceStore.getChainStores()) {
					for (RestaurantStore restaurantStore : chainStore.getRestaurantStores()) {
						TestResult testResult = initializeTestResults("Retrieve category count for chain - "
								+ chainStore.getChain().getName() + " & restaurant - "
								+ restaurantStore.getRestaurant().getStreet(), format, TestModule.CATEGORY_COUNT);
						String url = getUrl("/chains/" + chainStore.getChain().getId() + "/restaurants/"
								+ restaurantStore.getRestaurant().getId() + "/categories/count."
								+ format.toString().toLowerCase() + "?accessKey=" + getAccessKey());
						testResult.setUrl(url);

						testResult.addRequestParam("chainId", "[" + chainStore.getChain().getId() + "], "
								+ chainStore.getChain().getName());
						testResult.addRequestParam("restaurantId", "[" + restaurantStore.getRestaurant().getId()
								+ "], " + restaurantStore.getRestaurant().getStreet());

						try {
							ResponseEntity<Count> responseEntity = getTemplate().getForEntity(url, Count.class);

							validateResponse(responseEntity.getStatusCode());
							validateResponseType(format, responseEntity.getHeaders().getContentType());

							Count categoriesCount = responseEntity.getBody();
							validateCategoriesCount(categoriesCount);
							testResult.addTestDetails("Retrieved category count : " + categoriesCount.getCount());
						} catch (Exception e) {
							handleExceptions(testResult, e);
						}

					}
				}
			}
		}

	}

	private void validateCategoriesCount(final Count categoriesCount) {
		assertNotNull(categoriesCount.getCount(), "Categories count may not be null");
		assertTrue(categoriesCount.getCount() >= 0, "Categories count may not be a negative number");
	}
}

package com.ethor.testbed.api.test.restaurant;

import static com.ethor.testbed.api.test.Assert.assertNotNull;
import static com.ethor.testbed.api.test.Assert.assertTrue;

import org.springframework.http.ResponseEntity;

import com.ethor.testbed.api.domain.Count;
import com.ethor.testbed.api.test.reference.store.ChainStore;
import com.ethor.testbed.api.test.reference.store.ReferenceStore;
import com.ethor.testbed.api.test.report.TestFormat;
import com.ethor.testbed.api.test.report.TestModule;
import com.ethor.testbed.api.test.report.TestResult;

/**
 * Retrieve restaurants count API test.
 * 
 * @author Roy Fernando.
 */
public class RetrieveRestaurantsCountTest extends AbstractRestaurantTest {

	/**
	 * Request restaurant count as json.
	 */
	public void retrieveRestaurantCount() {
		retrieve(TestFormat.JSON);
		retrieve(TestFormat.XML);
	}

	private void retrieve(final TestFormat format) {
		if (getAccessKey() != null) {
			ReferenceStore referenceStore = (ReferenceStore) getTestSession().get(REFERENCE_STORE);
			if (referenceStore != null) {
				for (ChainStore chainStore : referenceStore.getChainStores()) {
					TestResult testResult = initializeTestResults("Retrieve restaurant count for chain - "
							+ chainStore.getChain().getName(), format, TestModule.RESTAURANT_COUNT);
					String url = getUrl("/chains/" + chainStore.getChain().getId() + "/restaurants/count."
							+ format.toString().toLowerCase() + "?accessKey=" + getAccessKey());
					testResult.setUrl(url);

					testResult.addRequestParam("chainId", "[" + chainStore.getChain().getId() + "], "
							+ chainStore.getChain().getName());

					try {
						ResponseEntity<Count> responseEntity = getTemplate().getForEntity(url, Count.class);
						validateResponse(responseEntity.getStatusCode());
						validateResponseType(format, responseEntity.getHeaders().getContentType());
						Count restaurantCount = responseEntity.getBody();
						validateRestaurantCount(restaurantCount);
						testResult.addTestDetails("Retrieved restaurant count : " + restaurantCount.getCount());
					} catch (Exception e) {
						handleExceptions(testResult, e);
					}

				}
			}
		}
	}

	private void validateRestaurantCount(final Count chainCount) {
		assertNotNull(chainCount.getCount(), "Restaurant count may not be null");
		assertTrue(chainCount.getCount() >= 0, "Restaurant count may not be a negative number");
	}

}

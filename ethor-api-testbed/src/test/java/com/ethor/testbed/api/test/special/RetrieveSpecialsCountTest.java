package com.ethor.testbed.api.test.special;

import static com.ethor.testbed.api.test.Assert.assertNotNull;
import static com.ethor.testbed.api.test.Assert.assertTrue;

import org.springframework.http.ResponseEntity;

import com.ethor.testbed.api.domain.Count;
import com.ethor.testbed.api.test.AbstractAPITest;
import com.ethor.testbed.api.test.reference.store.ChainStore;
import com.ethor.testbed.api.test.reference.store.ReferenceStore;
import com.ethor.testbed.api.test.reference.store.RestaurantStore;
import com.ethor.testbed.api.test.report.TestFormat;
import com.ethor.testbed.api.test.report.TestModule;
import com.ethor.testbed.api.test.report.TestResult;

public class RetrieveSpecialsCountTest extends AbstractAPITest {

	/**
	 * Requesting special count as json.
	 */
	public void retrieveSpecialCount() {
		retrieve(TestFormat.JSON);
		retrieve(TestFormat.XML);
	}

	private void retrieve(final TestFormat format) {
		ReferenceStore referenceStore = (ReferenceStore) getTestSession().get(REFERENCE_STORE);
		if (referenceStore != null) {
			for (ChainStore chainStore : referenceStore.getChainStores()) {
				for (RestaurantStore restaurantStore : chainStore.getRestaurantStores()) {
					TestResult testResult = initializeTestResults("Retrieve specials count for chain - "
							+ chainStore.getChain().getName() + " & restaurant - "
							+ restaurantStore.getRestaurant().getStreet(), format, TestModule.SPECIAL_COUNT);

					String url = getUrl("chains/" + chainStore.getChain().getId() + "/restaurants/"
							+ restaurantStore.getRestaurant().getId() + "/specials/count."
							+ format.toString().toLowerCase() + "?accessKey=" + getAccessKey());
					testResult.setUrl(url);

					testResult.addRequestParam("chainId", "[" + chainStore.getChain().getId() + "], "
							+ chainStore.getChain().getName());
					testResult.addRequestParam("restaurantId", "[" + restaurantStore.getRestaurant().getId() + "], "
							+ restaurantStore.getRestaurant().getStreet());

					try {
						ResponseEntity<Count> responseEntity = getTemplate().getForEntity(url, Count.class);
						validateResponseType(format, responseEntity.getHeaders().getContentType());

						Count specialCount = responseEntity.getBody();
						validateSpecialCount(specialCount);
						testResult.addTestDetails("Retrieved special count : " + specialCount.getCount());
					} catch (Exception e) {
						handleExceptions(testResult, e);
					}
				}
			}
		}
	}

	private void validateSpecialCount(final Count specialCount) {
		assertNotNull(specialCount.getCount(), "Special count may not be null");
		assertTrue(specialCount.getCount() >= 0, "Special count may not be a negative number");
	}

}

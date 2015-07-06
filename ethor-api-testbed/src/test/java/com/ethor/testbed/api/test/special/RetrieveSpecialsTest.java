package com.ethor.testbed.api.test.special;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ethor.testbed.api.domain.special.Special;
import com.ethor.testbed.api.domain.special.Specials;
import com.ethor.testbed.api.test.AbstractAPITest;
import com.ethor.testbed.api.test.exception.ValidationException;
import com.ethor.testbed.api.test.reference.store.ChainStore;
import com.ethor.testbed.api.test.reference.store.ReferenceStore;
import com.ethor.testbed.api.test.reference.store.RestaurantStore;
import com.ethor.testbed.api.test.report.TestFormat;
import com.ethor.testbed.api.test.report.TestModule;
import com.ethor.testbed.api.test.report.TestResult;
import com.ethor.testbed.api.test.report.TestStatus;

/**
 * Retrieve specials API test.
 * 
 * @author Roy Fernando.
 */
public class RetrieveSpecialsTest extends AbstractAPITest {

	public void retrieveSpecials() {
		retrieve(TestFormat.JSON);
		retrieve(TestFormat.XML);
	}

	private void retrieve(final TestFormat format) {

		ReferenceStore referenceStore = (ReferenceStore) getTestSession().get(REFERENCE_STORE);
		if (referenceStore != null) {
			for (ChainStore chainStore : referenceStore.getChainStores()) {

				for (RestaurantStore restaurantStore : chainStore.getRestaurantStores()) {

					TestResult testResult = initializeTestResults("Retrieve specials for given chain and restaurant",
							format, TestModule.SPECIAL);
					testResult.addRequestParam("chainId", "[" + chainStore.getChain().getId() + "], "
							+ chainStore.getChain().getName());
					testResult.addRequestParam("restaurantId", "[" + restaurantStore.getRestaurant().getId() + "], "
							+ restaurantStore.getRestaurant().getStreet());
					String url = getUrl("chains/" + chainStore.getChain().getId() + "/restaurants/"
							+ restaurantStore.getRestaurant().getId() + "/specials." + format.toString().toLowerCase()
							+ "?accessKey=" + getAccessKey());
					testResult.setUrl(url);
					try {
						ResponseEntity<Specials> responseEntity = getTemplate().getForEntity(url, Specials.class);

						validateResponse(responseEntity.getStatusCode());
						validateResponseType(format, responseEntity.getHeaders().getContentType());

						List<String> specialIds = new ArrayList<String>();
						List<String> validationErrors = new ArrayList<String>();

						restaurantStore.setSpecials(responseEntity.getBody().getSpecials());

						for (Special special : responseEntity.getBody().getSpecials()) {
							if (specialIds.contains(special.getId())) {
								throw new ValidationException("Special id [" + special.getId()
										+ "] given for special [" + special.getName()
										+ "] is invalid, since it's not unique");
							}
							specialIds.add(special.getId());
						}

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

	private void updateTestResults(final TestResult testResult, final ResponseEntity<Specials> responseEntity) {
		if (responseEntity.getBody().getSpecials().isEmpty()) {
			testResult.setStatus(TestStatus.FAIL);
			testResult.addTestDetails("Specials not found");
		} else {
			StringBuilder specialsString = new StringBuilder();
			for (int i = 0; i < responseEntity.getBody().getSpecials().size(); i++) {
				specialsString.append("[");
				specialsString.append(responseEntity.getBody().getSpecials().get(i).getName());
				specialsString.append("]");
				if (i != responseEntity.getBody().getSpecials().size() - 1) {
					specialsString.append(", ");
				}
			}
			testResult.addTestDetails("Specials retrieved : " + specialsString.toString());
		}
	}
}
package com.ethor.testbed.api.test.restaurant;

import static com.ethor.testbed.api.test.Assert.assertNotNull;
import static com.ethor.testbed.api.test.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ethor.testbed.api.domain.restaurant.Restaurant;
import com.ethor.testbed.api.domain.restaurant.Restaurants;
import com.ethor.testbed.api.test.exception.ValidationException;
import com.ethor.testbed.api.test.reference.store.ChainStore;
import com.ethor.testbed.api.test.reference.store.ReferenceStore;
import com.ethor.testbed.api.test.reference.store.RestaurantStore;
import com.ethor.testbed.api.test.report.TestFormat;
import com.ethor.testbed.api.test.report.TestModule;
import com.ethor.testbed.api.test.report.TestResult;
import com.ethor.testbed.api.test.report.TestStatus;

/**
 * Retrieve available Restaurants API test.
 * 
 * @author Roy Fernando.
 */
public class RetrieveRestaurantsTest extends AbstractRestaurantTest {

	public void retrieveRestaurants() {
		retrieve(TestFormat.JSON);
		retrieve(TestFormat.XML);
	}

	private void retrieve(final TestFormat format) {

		ReferenceStore referenceStore = (ReferenceStore) getTestSession().get(REFERENCE_STORE);
		if (referenceStore != null) {
			for (ChainStore chainStore : referenceStore.getChainStores()) {
				TestResult testResult = initializeTestResults("Retrieve all available restaurants for given chain id",
						format, TestModule.RESTAURANT);
				String url = getUrl("/chains/" + chainStore.getChain().getId() + "/restaurants."
						+ format.toString().toLowerCase() + "?accessKey=" + getAccessKey());
				testResult.setUrl(url);
				try {
					testResult.addRequestParam("chainId", "[" + chainStore.getChain().getId() + "], "
							+ chainStore.getChain().getName());
					ResponseEntity<Restaurants> responseEntity = getTemplate().getForEntity(url, Restaurants.class);

					validateResponse(responseEntity.getStatusCode());
					validateResponseType(format, responseEntity.getHeaders().getContentType());
					updateReferenceStore(chainStore, responseEntity);
					validateRestaurants(responseEntity.getBody(), chainStore.getChain().getId());
					updateTestResults(testResult, responseEntity);

				} catch (Exception e) {
					handleExceptions(testResult, e);
				}
			}

		}
	}

	private void updateReferenceStore(ChainStore chainStore, ResponseEntity<Restaurants> responseEntity) {
		Restaurants restaurants = responseEntity.getBody();
		List<RestaurantStore> restaurantStores = new ArrayList<RestaurantStore>();
		for (Restaurant restaurant : restaurants.getRestaurants()) {

			restaurantStores.add(new RestaurantStore(restaurant));
		}
		chainStore.setRestaurantStores(restaurantStores);
	}

	private void updateTestResults(final TestResult testResult, final ResponseEntity<Restaurants> responseEntity) {
		if (responseEntity.getBody().getRestaurants().isEmpty()) {
			testResult.setStatus(TestStatus.FAIL);
			testResult.addTestDetails("Restaurants not found");
		} else {
			StringBuilder restaurantString = new StringBuilder();
			for (int i = 0; i < responseEntity.getBody().getRestaurants().size(); i++) {
				restaurantString.append("[");
				restaurantString.append(responseEntity.getBody().getRestaurants().get(i).getStreet());
				restaurantString.append("]");
				if (i != responseEntity.getBody().getRestaurants().size() - 1) {
					restaurantString.append(", ");
				}
			}
			testResult.addTestDetails("Restaurants retrieved : " + restaurantString.toString());
		}
	}

	private void validateRestaurants(final Restaurants restaurants, String chainId) {
		List<String> restaurantIds = new ArrayList<String>();
		List<String> validationErrors = new ArrayList<String>();
		if (restaurants.getRestaurants().isEmpty()) {
			throw new ValidationException("Response is empty, restaurants not found");
		}
		for (Restaurant restaurant : restaurants.getRestaurants()) {
			if (restaurantIds.contains(restaurant.getId())) {
				validationErrors.add("Restaurant id [" + restaurant.getId() + "] given for resturant ["
						+ restaurant.getStreet() + "] is invalid since it's not unique");
			}

			restaurantIds.add(restaurant.getId());
			assertSame(chainId, restaurant.getChainId(), "Invalid chatin id", validationErrors);
			assertNotNull(restaurant.getStatus(), "Invalid restaurant Status [" + restaurant.getStatus()
					+ "] for restaurant - [" + restaurant.getId() + "], " + restaurant.getStreet(), validationErrors);
		}

		if (!validationErrors.isEmpty()) {
			throw new ValidationException(validationErrors);
		}
	}

}

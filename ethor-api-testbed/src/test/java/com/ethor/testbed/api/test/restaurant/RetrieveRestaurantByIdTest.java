package com.ethor.testbed.api.test.restaurant;

import static com.ethor.testbed.api.test.Assert.assertSame;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ethor.testbed.api.domain.restaurant.Restaurant;
import com.ethor.testbed.api.domain.restaurant.Restaurants;
import com.ethor.testbed.api.test.data.UnExpectedValue;
import com.ethor.testbed.api.test.report.TestFormat;
import com.ethor.testbed.api.test.report.TestModule;
import com.ethor.testbed.api.test.report.TestResult;
import com.ethor.testbed.api.test.report.TestStatus;

/**
 * Retrieve restaurant by id API test.
 * 
 * @author Roy Fernando.
 */
public class RetrieveRestaurantByIdTest extends AbstractRestaurantTest {

	public void retrieveRestaurant() throws IOException {
		Restaurants restaurantsToBeTest = (Restaurants)testSession.get("RESTAURANT_TEST_DATA");
		retrieve(TestFormat.JSON, restaurantsToBeTest);
		retrieve(TestFormat.XML, restaurantsToBeTest);
	}

	private void retrieve(final TestFormat format, Restaurants restaurantsToBeTest) {
		if (restaurantsToBeTest != null) {
			for (Restaurant restaurant : restaurantsToBeTest.getRestaurants()) {
				TestResult testResult = initializeTestResults("Retrieve restaurant for given id", format,
						TestModule.RESTAURANT_BYID);
				ResponseEntity<Restaurant> responseEntity = null;
				try {

					testResult.addRequestParam("chaindId", restaurant.getChainId());
					testResult
							.addRequestParam("restaurantId", restaurant.getStreet());

					String url = getUrl("/chains/" + restaurant.getChainId() + "/restaurants/" + restaurant.getId()
							+ "." + format.toString().toLowerCase() + "?accessKey=" + getAccessKey());
					testResult.setUrl(url);
					responseEntity = getTemplate().getForEntity(url, Restaurant.class);
					validateResponse(responseEntity.getStatusCode());
					validateResponseType(format, responseEntity.getHeaders().getContentType());
					assertSame(restaurant, responseEntity.getBody(), "Return restaurant is not what expected");
					List<UnExpectedValue> unExpectedValues = RestaurantComparator.compare(restaurant,
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
}

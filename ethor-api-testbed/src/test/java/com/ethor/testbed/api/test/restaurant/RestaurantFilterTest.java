package com.ethor.testbed.api.test.restaurant;

import static com.ethor.testbed.api.test.Assert.assertSame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.http.ResponseEntity;

import com.ethor.testbed.api.domain.chain.Chain;
import com.ethor.testbed.api.domain.chain.Chains;
import com.ethor.testbed.api.domain.restaurant.Restaurant;
import com.ethor.testbed.api.domain.restaurant.Restaurants;
import com.ethor.testbed.api.test.AbstractAPITest;
import com.ethor.testbed.api.test.data.TestDataManager;
import com.ethor.testbed.api.test.data.UnExpectedValue;
import com.ethor.testbed.api.test.data.filtering.RestaurantFilterTestData;
import com.ethor.testbed.api.test.report.TestFormat;
import com.ethor.testbed.api.test.report.TestModule;
import com.ethor.testbed.api.test.report.TestResult;
import com.ethor.testbed.api.test.report.TestStatus;

public class RestaurantFilterTest extends AbstractAPITest {

	private TestDataManager testDataManager;

	public void filter() throws IOException {
		List<RestaurantFilterTestData> filterTestData = testDataManager.getRestaurantFilterTestData();
		filter(TestFormat.JSON, filterTestData);
		filter(TestFormat.XML, filterTestData);		
	}

	private void filter(final TestFormat format, List<RestaurantFilterTestData> filterTestData) {

		Chains chainTestData = (Chains) getTestSession().get("CHAIN_TEST_DATA");

		for (RestaurantFilterTestData testData : filterTestData) {
			if (!testData.getFilterDataMap().isEmpty()) {
				ResponseEntity<Restaurants> responseEntity = null;
				TestResult testResult = initializeTestResults("filter restaurants by "
						+ testData.getFilterDataMap().keySet().toString(), format, TestModule.RESTAURANT_FILTER);
				try {

					Chain testChain = chainTestData.getChainById(testData.getChainId());
					testResult.addRequestParam("ChainId",
							testData.getChainId() + (testChain != null ? "[" + testChain.getName() + "]" : ""));

					String url = getUrl("/chains/" + testData.getChainId() + "/restaurants."
							+ format.toString().toLowerCase() + "?"
							+ getQueryString(testData.getFilterDataMap(), testResult) + "accessKey=" + getAccessKey());
					testResult.setUrl(url);
					responseEntity = getTemplate().getForEntity(url, Restaurants.class);
					testResult.setStatus(TestStatus.PASS);

					List<String> restaurantNames = new ArrayList<String>();
					for (Restaurant restaurant : responseEntity.getBody().getRestaurants()) {
						restaurantNames.add(restaurant.getStreet());
					}
					List<UnExpectedValue> unExpectedValues = new ArrayList<UnExpectedValue>();
					assertSame(testData.getExpectedRestaurants(), restaurantNames, "resturants", unExpectedValues);
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

	public void setTestDataManager(final TestDataManager testDataManager) {
		this.testDataManager = testDataManager;
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

}

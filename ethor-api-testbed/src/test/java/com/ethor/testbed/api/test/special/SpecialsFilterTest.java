package com.ethor.testbed.api.test.special;

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
import com.ethor.testbed.api.domain.special.Special;
import com.ethor.testbed.api.domain.special.Specials;
import com.ethor.testbed.api.test.AbstractAPITest;
import com.ethor.testbed.api.test.data.TestDataManager;
import com.ethor.testbed.api.test.data.UnExpectedValue;
import com.ethor.testbed.api.test.data.filtering.SpecialsFilterTestData;
import com.ethor.testbed.api.test.report.TestFormat;
import com.ethor.testbed.api.test.report.TestModule;
import com.ethor.testbed.api.test.report.TestResult;
import com.ethor.testbed.api.test.report.TestStatus;

public class SpecialsFilterTest extends AbstractAPITest {

	private TestDataManager testDataManager;

	public void filter() throws IOException {
		List<SpecialsFilterTestData> filterTestData = testDataManager.getSpecialsFilterTestData();
		filter(TestFormat.JSON, filterTestData);
		filter(TestFormat.XML, filterTestData);
	}

	private void filter(final TestFormat format, List<SpecialsFilterTestData> filterTestData) {
		Chains chainTestData = (Chains) getTestSession().get("CHAIN_TEST_DATA");
		Restaurants restaurantTestData = (Restaurants) getTestSession().get("RESTAURANT_TEST_DATA");

		for (SpecialsFilterTestData testData : filterTestData) {
			if (!testData.getFilterDataMap().isEmpty()) {
				ResponseEntity<Specials> responseEntity = null;
				TestResult testResult = initializeTestResults("filter specials by "
						+ testData.getFilterDataMap().keySet().toString(), format, TestModule.SPECIAL_FILTER);
				try {

					Chain testChain = chainTestData.getChainById(testData.getChainId());
					Restaurant testRestaurant = restaurantTestData.getRestaurantById(testData.getRestaurantId());
					testResult.addRequestParam("ChainId",
							testData.getChainId() + (testChain != null ? "[" + testChain.getName() + "]" : ""));
					testResult.addRequestParam("RestaurantId", testData.getRestaurantId()
							+ (testRestaurant != null ? "[" + testRestaurant.getStreet() + "]" : ""));
					
					String url = getUrl("/chains/" + testData.getChainId() + "/restaurants/" + testData.getRestaurantId() + "/specials."
							+ format.toString().toLowerCase() + "?"
							+ getQueryString(testData.getFilterDataMap(), testResult) + "accessKey=" + getAccessKey());
					testResult.setUrl(url);
					responseEntity = getTemplate().getForEntity(url, Specials.class);
					testResult.setStatus(TestStatus.PASS);
					
					List<String> specialNames = new ArrayList<String>();
					for (Special special : responseEntity.getBody().getSpecials()) {
						specialNames.add(special.getName().trim());
					}
					List<UnExpectedValue> unExpectedValues = new ArrayList<UnExpectedValue>();
					assertSame(testData.getExpectedSpecials(), specialNames, "specials", unExpectedValues);
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

	public void setTestDataManager(final TestDataManager testDataManager) {
		this.testDataManager = testDataManager;
	}

}

package com.ethor.testbed.api.test.customer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.bind.ValidationException;

import org.springframework.http.ResponseEntity;

import com.ethor.testbed.api.domain.customer.Customer;
import com.ethor.testbed.api.domain.customer.Customers;
import com.ethor.testbed.api.test.AbstractAPITest;
import com.ethor.testbed.api.test.data.TestDataManager;
import com.ethor.testbed.api.test.data.UnExpectedValue;
import com.ethor.testbed.api.test.report.TestFormat;
import com.ethor.testbed.api.test.report.TestModule;
import com.ethor.testbed.api.test.report.TestResult;
import com.ethor.testbed.api.test.report.TestStatus;

public class RetrieveCustomerTest extends AbstractAPITest {

	private TestDataManager testDataManager;

	public void RetrieveCustomer() throws IOException {
		Customers customersTestData = (Customers) testSession.get("CUSTOMER_TEST_DATA");
		Map<String, List<String>> customerChainMap = testDataManager.getCustomerChainMapping();

		if (customersTestData != null && customerChainMap != null) {
			retrieveCustomerByPhoneNumber(TestFormat.JSON, customersTestData, customerChainMap);
			retrieveCustomerByPhoneNumber(TestFormat.XML, customersTestData, customerChainMap);

			retrieveCustomerByEmail(TestFormat.JSON, customersTestData, customerChainMap);
			retrieveCustomerByEmail(TestFormat.XML, customersTestData, customerChainMap);
		}

	}

	private void retrieveCustomerByPhoneNumber(final TestFormat format, final Customers customersTestData,
			final Map<String, List<String>> customerChainMap) {
		retrieve(format, TestModule.CUSTOMER_BY_PHONE, customersTestData, customerChainMap);
	}

	private void retrieveCustomerByEmail(final TestFormat format, final Customers customersTestData,
			final Map<String, List<String>> customerChainMap) {
		retrieve(format, TestModule.CUSTOMER_BY_EMAIL, customersTestData, customerChainMap);
	}

	private void retrieve(final TestFormat format, final TestModule testModule, final Customers customersTestData,
			final Map<String, List<String>> customerChainMap) {

		for (Customer customerToBeTested : customersTestData.getCustomers()) {

			if (customerChainMap.containsKey(customerToBeTested.getId())) {
				List<String> chainIds = customerChainMap.get(customerToBeTested.getId());
				for (String chainId : chainIds) {

					String actionType = "email";
					if (testModule.equals(TestModule.CUSTOMER_BY_PHONE)) {
						actionType = "phone";
					}

					TestResult testResult = initializeTestResults("Retrieve customer by " + actionType, format,
							testModule);
					ResponseEntity<Customers> responseEntity = null;
					try {

						StringBuilder parameterString = new StringBuilder();
						if (testModule.equals(TestModule.CUSTOMER_BY_PHONE)) {
							if (customerToBeTested.getPhoneNumbers().get(0) == null) {
								throw new ValidationException(
										"Customer phone number is not available for customer id - " + chainId
												+ ", in test data");
							}
							parameterString.append(customerToBeTested.getPhoneNumbers().get(0).getAreaCode());
							parameterString.append(customerToBeTested.getPhoneNumbers().get(0).getNumber());
						} else {
							if (customerToBeTested.getEmail() == null) {
								throw new ValidationException("Customer email is not available for customer id - "
										+ chainId + ", in test data");
							}
							parameterString.append(customerToBeTested.getEmail());
						}

						testResult.addRequestParam("chainId", chainId);
						testResult.addRequestParam(actionType, parameterString.toString());

						String url = getUrl("/chains/" + chainId + "/customers." + format.toString().toLowerCase()
								+ "?" + actionType + "=" + parameterString.toString() + "&accessKey="
								+ getAccessKey());

						testResult.setUrl(url);

						responseEntity = getTemplate().getForEntity(url, Customers.class);
						testResult.setStatus(TestStatus.PASS);

						for (Customer customer : responseEntity.getBody().getCustomers()) {
							List<UnExpectedValue> unExpectedValues = CustomerComparator.compare(customerToBeTested,
									customer);
							if (!unExpectedValues.isEmpty()) {
								testResult.setStatus(TestStatus.FAIL);
								testResult.buildUnExpectedValueOutput(unExpectedValues);
							} else {
								testResult.addTestDetails("Successful");
							}
								}
					} catch (Exception e) {
						handleExceptions(testResult, e);
					}

				}

			}

		}

	}

	public void setTestDataManager(final TestDataManager testDataManager) {
		this.testDataManager = testDataManager;
	}
}

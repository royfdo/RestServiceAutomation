package com.ethor.testbed.api.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ethor.testbed.RestClient;
import com.ethor.testbed.api.domain.AccessKey;
import com.ethor.testbed.api.test.category.RetrieveCategoriesCountTest;
import com.ethor.testbed.api.test.category.RetrieveCategoriesTest;
import com.ethor.testbed.api.test.category.RetrieveCategoryByIdTest;
import com.ethor.testbed.api.test.chain.RetrieveChainByIdTest;
import com.ethor.testbed.api.test.chain.RetrieveChainsCountTest;
import com.ethor.testbed.api.test.chain.RetrieveChainsTest;
import com.ethor.testbed.api.test.customer.RetrieveCustomerTest;
import com.ethor.testbed.api.test.data.TestDataManager;
import com.ethor.testbed.api.test.menuitem.MenuItemFilterTest;
import com.ethor.testbed.api.test.menuitem.RetrieveMenuitemByIdTest;
import com.ethor.testbed.api.test.menuitem.RetrieveMenuitemsCountTest;
import com.ethor.testbed.api.test.menuitem.RetrieveMenuitemsTest;
import com.ethor.testbed.api.test.order.CalculateOrderTest;
import com.ethor.testbed.api.test.reference.store.ReferenceStore;
import com.ethor.testbed.api.test.report.TestModule;
import com.ethor.testbed.api.test.report.TestReportGenerator;
import com.ethor.testbed.api.test.report.TestReportGeneratorImpl;
import com.ethor.testbed.api.test.report.TestResult;
import com.ethor.testbed.api.test.report.TestStatus;
import com.ethor.testbed.api.test.restaurant.RestaurantFilterTest;
import com.ethor.testbed.api.test.restaurant.RetrieveRestaurantByIdTest;
import com.ethor.testbed.api.test.restaurant.RetrieveRestaurantsCountTest;
import com.ethor.testbed.api.test.restaurant.RetrieveRestaurantsTest;
import com.ethor.testbed.api.test.special.RetrieveSpecialsCountTest;
import com.ethor.testbed.api.test.special.RetrieveSpecialsTest;
import com.ethor.testbed.api.test.special.SpecialsFilterTest;

import freemarker.template.TemplateException;

/**
 * Main test suite for ethor Rest API tests.
 * 
 * @author Roy Fernando.
 */
@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class RestAPITestSuite extends AbstractAPITest implements ApplicationContextAware {

	private static final Logger LOGGER = Logger.getLogger(RestAPITestSuite.class);

	private RetrieveChainsTest chainsTest;
	private RetrieveRestaurantsTest restaurantsTest;
	private RetrieveCategoriesTest categoriesTest;
	private RetrieveMenuitemsTest menuitemsTest;
	private RetrieveSpecialsTest specialsTest;
	private RetrieveCustomerTest customerTest;

	private RetrieveChainsCountTest chainsCountTest;
	private RetrieveRestaurantsCountTest restaurantsCountTest;
	private RetrieveCategoriesCountTest categoriesCountTest;
	private RetrieveSpecialsCountTest specialsCountTest;
	private RetrieveMenuitemsCountTest menuitemsCountTest;

	private RetrieveChainByIdTest retrieveChainByIdTest; 
	private RetrieveRestaurantByIdTest restaurantByIdTest;
	private RetrieveCategoryByIdTest retrieveCategoryByIdTest;
	private RetrieveMenuitemByIdTest retrieveMenuitemByIdTest; 
	
	private RestaurantFilterTest restaurantFilterTest;
	private SpecialsFilterTest specialsFilterTest;
	private MenuItemFilterTest menuItemFilterTest;
	
	private CalculateOrderTest calculateOrderTest;
	
	private TestDataManager testDataManager;

	@BeforeClass
	public static void start() throws IOException {
		LOGGER.info("Starting ethorRestAPI test suite");
		LOGGER.info("Test are executing, this will take few minutes please wait...");
	}

	/**
	 * Executes all possible test scenarios.
	 * 
	 * @throws IOException
	 */
	@Test
	public void rundTest() throws IOException {
		
		testSession.put("CHAIN_TEST_DATA", testDataManager.getChainsTestData());
		testSession.put("RESTAURANT_TEST_DATA", testDataManager.getRestaurantTestData());
		testSession.put("CATEGORY_TEST_DATA", testDataManager.getCategoryTestData());
		testSession.put("CUSTOMER_TEST_DATA", testDataManager.getCustomerTestData());

		chainsTest.retrieveChains();
		restaurantsTest.retrieveRestaurants();
		categoriesTest.retrieveCategories();
		menuitemsTest.retrieveMenuItems();
		specialsTest.retrieveSpecials();

		chainsCountTest.retrieveChainCount();
		restaurantsCountTest.retrieveRestaurantCount();
		categoriesCountTest.retrieveCategoriesCount();
		specialsCountTest.retrieveSpecialCount();
		menuitemsCountTest.retrieveMenuitemsCount();
		retrieveMenuitemByIdTest.retrieveMenuItems();
		
		customerTest.RetrieveCustomer();
		
		//calculateOrderTest.calculateOrder();

		retrieveChainByIdTest.retrieveChain();
		restaurantByIdTest.retrieveRestaurant();
		retrieveCategoryByIdTest.retrieveCategory();
		
		restaurantFilterTest.filter();
		specialsFilterTest.filter();
		menuItemFilterTest.filter();

		
		
	}

	/**
	 * Generates html report after test execution.
	 * 
	 * @throws IOException
	 *             could be thrown.
	 * @throws TemplateException
	 *             could be thrown.
	 */
	@AfterClass
	public static void generateReport() throws IOException, TemplateException {
		LOGGER.info("Finised executing ethorRestAPI test suite");
		LOGGER.info("Report generation process started, pls wait...");

		ReferenceStore referenceStore = (ReferenceStore) getTestSession().get("REFERENCE_STORE");
		List<TestResult> testResults = (List<TestResult>) getTestSession().get("TEST_RESULTS");

		TestReportGenerator generator = new TestReportGeneratorImpl();
		generator.generateReport(testResults);

		getTestSession().clear();

		LOGGER.info("Report generated successfully, and can be access through " + System.getProperty("projectDir")
				+ "\\report\\index.html");
	}

	private void init(final ApplicationContext testContext) {
		restClient = testContext.getBean("restClient", RestClient.class);
		testSession = testContext.getBean("testSession", HashMap.class);

		chainsTest = testContext.getBean("retrieveChains", RetrieveChainsTest.class);
		restaurantsTest = testContext.getBean("retrieveRestaurants", RetrieveRestaurantsTest.class);
		categoriesTest = testContext.getBean("retrieveCategories", RetrieveCategoriesTest.class);
		menuitemsTest = testContext.getBean("retrieveMenuitems", RetrieveMenuitemsTest.class);
		specialsTest = testContext.getBean("retrieveSpecials", RetrieveSpecialsTest.class);
		customerTest = testContext.getBean("retrieveCustomer", RetrieveCustomerTest.class);

		chainsCountTest = testContext.getBean("retrieveChainsCount", RetrieveChainsCountTest.class);
		restaurantsCountTest = testContext.getBean("retrieveRestaurantsCount", RetrieveRestaurantsCountTest.class);
		categoriesCountTest = testContext.getBean("retrieveCategoriesCount", RetrieveCategoriesCountTest.class);
		specialsCountTest = testContext.getBean("retrieveSpecialsCount", RetrieveSpecialsCountTest.class);
		menuitemsCountTest = testContext.getBean("retrieveMenuitemsCount", RetrieveMenuitemsCountTest.class);
		
		restaurantFilterTest = testContext.getBean("restaurantFilter", RestaurantFilterTest.class);
		specialsFilterTest = testContext.getBean("specialsFilter", SpecialsFilterTest.class);
		menuItemFilterTest = testContext.getBean("menuItemFilter", MenuItemFilterTest.class);
		
		
		restaurantByIdTest = testContext.getBean("retrieveRestaurantById", RetrieveRestaurantByIdTest.class);
		retrieveChainByIdTest = testContext.getBean("retrieveChainById", RetrieveChainByIdTest.class);
		retrieveCategoryByIdTest = testContext.getBean("retrieveCategoryById", RetrieveCategoryByIdTest.class);
		retrieveMenuitemByIdTest = testContext.getBean("retrieveMenuItemById", RetrieveMenuitemByIdTest.class);
		
		calculateOrderTest = testContext.getBean("calculateOrder", CalculateOrderTest.class);
		
		testDataManager = testContext.getBean("testDataManager", TestDataManager.class);

	}

	@Override
	public void setApplicationContext(ApplicationContext testContext) throws BeansException {
		init(testContext);

		String apiKey = restClient.getCredentials().getApiKey();
		String userName = restClient.getCredentials().getUserName();
		String password = restClient.getCredentials().getPassword();

		List<TestResult> testResults = new ArrayList<TestResult>();
		TestResult testResult = new TestResult();
		testResults.add(testResult);
		testResult.setTestName("Get access key for API");
		testResult.setTestModule(TestModule.AUTHENTICATION);
		testResult.addRequestParam("username", userName);
		testResult.addRequestParam("password", password);
		testResult.addRequestParam("apiKey", apiKey);
		String url = getUrl("/getAccessKey?apiKey=" + apiKey + "&username=" + userName + "&password=" + password);
		testResult.setUrl(url);

		ResponseEntity<AccessKey> accessKey = null;
		try {
			accessKey = restClient.getRestTemplate().getForEntity(url, AccessKey.class);
			testResult.setStatus(TestStatus.PASS);
			testResult.addTestDetails("Access key : " + accessKey.getBody().getAccessKey());
			testSession.put(ACCESS_KEY, accessKey.getBody().getAccessKey());

		} catch (Exception e) {
			testResult.setStatus(TestStatus.ERROR);
			testResult.addTestDetails("Error : " + e.getMessage());
			testSession.put(ACCESS_KEY, null);
		}

		testSession.put("TEST_RESULTS", testResults);

	}

	public void setChainsTest(final RetrieveChainsTest chainsTest) {
		this.chainsTest = chainsTest;
	}

	public void setRestaurantsTest(final RetrieveRestaurantsTest restaurantsTest) {
		this.restaurantsTest = restaurantsTest;
	}

	public void setCategoriesTest(final RetrieveCategoriesTest categoriesTest) {
		this.categoriesTest = categoriesTest;
	}

}

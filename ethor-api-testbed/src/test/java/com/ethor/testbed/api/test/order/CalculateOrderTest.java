package com.ethor.testbed.api.test.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.ethor.testbed.api.domain.order.Order;
import com.ethor.testbed.api.domain.order.Orders;
import com.ethor.testbed.api.test.AbstractAPITest;
import com.ethor.testbed.api.test.data.TestDataManager;
import com.ethor.testbed.api.test.report.TestFormat;
import com.ethor.testbed.api.test.report.TestModule;
import com.ethor.testbed.api.test.report.TestResult;

public class CalculateOrderTest extends AbstractAPITest {

	private TestDataManager testDataManager;
	
	public void calculateOrder() throws IOException {

		for (Order testOrder : testDataManager.getOrderTestData()) {

			TestResult testResult = initializeTestResults("Calculate order", TestFormat.JSON, TestModule.ORDER);

			String url = getUrl("/chains.json") + "?accessKey=" + getAccessKey();
			testResult.setUrl(url);

			String chainId = "1";
			String restaurantId = "15";
			
			Orders orders = new Orders();
			List<Order> list = new ArrayList<Order>();
			list.add(testOrder);
			orders.setOrders(list);

			HttpEntity<Orders> entity = new HttpEntity<Orders>(orders);
			ResponseEntity<Orders> responseEntiy = getTemplate().exchange(getUrl("chains/{chainId}/restaurants/{restaurantId}/orders/calculateOrder.json?accessKey={accessKey}"),
					HttpMethod.POST, entity, Orders.class, chainId, restaurantId,  getAccessKey());
			
			
			
			System.out.println("");

		}

	}

	public void setTestDataManager(final TestDataManager testDataManager) {
		this.testDataManager = testDataManager;
	}
	
	

}

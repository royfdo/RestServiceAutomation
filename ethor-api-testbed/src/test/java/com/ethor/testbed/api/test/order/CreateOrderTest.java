package com.ethor.testbed.api.test.order;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.ethor.testbed.api.test.AbstractAPITest;
import com.ethor.testbed.api.test.data.ExcelTestDataManagerImpl;
import com.ethor.testbed.api.test.data.TestDataManager;
import com.ethor.testbed.api.test.reference.store.ReferenceStore;

public class CreateOrderTest extends AbstractAPITest{
	
	private TestDataManager dataManager = new ExcelTestDataManagerImpl();
			
	public void createOrderAsJson() throws IOException {
		
		ReferenceStore referenceStore = (ReferenceStore) getTestSession().get(REFERENCE_STORE);
		
		Map<String, Object> config = new HashMap<String, Object>();
		config.put("baseUrl", getBaseUrl());
/*		
		TestData testData = dataManager.getTestData();
		
		for (TestOrder testOrder : testData.getOrders()) {
			
			Order order = new Order();
			//order.setCustomer(customer) TODO: ???
			//order.setBillingAddress(billingAddress)  TODO: ??
			//order.setDeliveryAddress(deliveryAddress) TODO: ??
			//order.setPhoneNumber(phoneNumber) TODO: ??
			order.setOrderType(testOrder.getOrderType());
			//order.setPayment(payment) TODO: ???			
			//order.setSpecials(testOrder.getSpecials());
			//order.setItems(testOrder.getItems())
			
			
		}*/
		
	}

}

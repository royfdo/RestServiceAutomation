package com.ethor.testbed.api.test.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ethor.testbed.api.domain.customer.Customer;
import com.ethor.testbed.api.domain.customer.Customers;
import com.ethor.testbed.api.domain.order.Order;
import com.ethor.testbed.api.domain.order.OrderContent;
import com.ethor.testbed.api.domain.order.OrderItem;
import com.ethor.testbed.api.domain.order.OrderSpecial;

public class OrderTestDataReader extends ExcelDataSupport implements ExcelDataReader<List<Order>> {

	@Override
	public List<Order> readData(XSSFWorkbook workbook) throws IOException {

		Map<String, OrderContent> itemContent = readContentData(workbook.getSheet("Contents"));
		Map<String, OrderItem> items = readItemData(workbook.getSheet("Items"), itemContent);
		Map<String, OrderSpecial> orderSpecials = readSpecialData(workbook.getSheet("Special"), items);
		List<Order> orders = readOrderData(workbook.getSheet("Orders"), orderSpecials);

		return orders;
	}

	private Map<String, OrderSpecial> readSpecialData(final XSSFSheet sheet, Map<String, OrderItem> items) {
		Map<String, OrderSpecial> orderSpecials = new HashMap<String, OrderSpecial>();
		for (Row row : sheet) {
			if (row.getRowNum() != 0) {
				OrderSpecial orderSpecial = new OrderSpecial();
				orderSpecial.setId(getCellValueAsString(row.getCell(0)));
				orderSpecial.setQuantity(getCellValueAsString(row.getCell(1)));
				// orderSpecial.setTotal(getCellValueAsString(row.getCell(2)));
				List<OrderItem> orderItems = new ArrayList<OrderItem>();
				List<String> refItemIds = getCellValueAsStringList(row.getCell(2));
				for (String refItemId : refItemIds) {
					if (items.containsKey(refItemId)) {
						orderItems.add(items.get(refItemId));
					}
				}
				orderSpecial.setItems(orderItems);
				orderSpecials.put(orderSpecial.getId(), orderSpecial);
			}
		}
		return orderSpecials;
	}

	private Map<String, OrderContent> readContentData(final XSSFSheet sheet) {
		Map<String, OrderContent> orderContents = new HashMap<String, OrderContent>();
		for (Row row : sheet) {
			if (row.getRowNum() != 0) {
				OrderContent orderContent = new OrderContent();
				String refId = getCellValueAsString(row.getCell(0));
				orderContent.setId(getCellValueAsString(row.getCell(1)));
				orderContent.setPortionId(getCellValueAsString(row.getCell(2)));
				orderContent.setAmountId(getCellValueAsString(row.getCell(3)));

				orderContents.put(refId, orderContent);

			}
		}
		return orderContents;
	}

	private Map<String, OrderItem> readItemData(final XSSFSheet sheet, Map<String, OrderContent> contentMap) {
		Map<String, OrderItem> orderItems = new HashMap<String, OrderItem>();
		for (Row row : sheet) {
			if (row.getRowNum() != 0) {
				OrderItem orderItem = new OrderItem();
				String refItemId = getCellValueAsString(row.getCell(0));
				orderItem.setMenuItem(getCellValueAsString(row.getCell(1))); // TODO
				orderItem.setQuantity(getCellValueAsString(row.getCell(2)));
				orderItem.setSizeId(getCellValueAsString(row.getCell(3)));
				List<String> contentRefIds = getCellValueAsStringList(row.getCell(4));
				List<OrderContent> orderContents = new ArrayList<OrderContent>();
				for (String contentRefId : contentRefIds) {
					if (contentMap.containsKey(contentRefId)) {
						orderContents.add(contentMap.get(contentRefId));
					}
				}
				orderItem.setContents(orderContents);
				orderItems.put(refItemId, orderItem);

			}
		}
		return orderItems;
	}

	private List<Order> readOrderData(final XSSFSheet sheet, Map<String, OrderSpecial> orderSpecials) {
		List<Order> orders = new ArrayList<Order>();
		for (Row row : sheet) {
			if (row.getRowNum() != 0) {
				Order order = new Order();
				String customerId = getCellValueAsString(row.getCell(0));
				Customers customers = (Customers) getTestSession().get("CUSTOMER_TEST_DATA");
				Customer customer = customers.getCustomerById(customerId);
				order.setCustomer(customers.getCustomerById(customerId));
				order.setBillingAddress(customer.getBillingAddress());
				order.setDeliveryAddress(customer.getBillingAddress());
				order.setPhoneNumber(customer.getPhoneNumbers().get(0));
				order.setOrderType(getCellValueAsString(row.getCell(1)));

				String specialId = getCellValueAsString(row.getCell(2));

				order.setSpecials(orderSpecials.get(specialId));

				orders.add(order);
			}
		}
		return orders;
	}

	public static void main(String[] args) throws IOException {
		XSSFWorkbook xssfwb = new XSSFWorkbook(ClassLoader.getSystemResourceAsStream("testData.xlsx"));
		SXSSFWorkbook workbook = new SXSSFWorkbook(xssfwb);

		new OrderTestDataReader().readData(workbook.getXSSFWorkbook());
	}

}

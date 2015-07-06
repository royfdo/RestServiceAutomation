package com.ethor.testbed.api.test.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ethor.testbed.api.domain.chain.Chain;
import com.ethor.testbed.api.domain.chain.Chains;
import com.ethor.testbed.api.domain.restaurant.AcceptedPayments;
import com.ethor.testbed.api.domain.restaurant.Restaurant;
import com.ethor.testbed.api.domain.restaurant.RestaurantStatus;
import com.ethor.testbed.api.domain.restaurant.Restaurants;

public class RestaurantTestDataReader extends ExcelDataSupport implements ExcelDataReader<Restaurants> {

	public Restaurants readData(final XSSFWorkbook workbook) throws IOException {
		XSSFSheet sheet = workbook.getSheet("Restaurant");
		Chains chains = (Chains)testSession.get("CHAIN_TEST_DATA");
		
		Restaurants restaurants = new Restaurants();
		List<Restaurant> restaurantList = new ArrayList<Restaurant>();
		restaurants.setRestaurants(restaurantList);

		for (Row row : sheet) {
			if (row.getRowNum() != 0) {
				if (getCellValueAsString(row.getCell(0)) != null) {
					Restaurant restaurant = new Restaurant();
					restaurant.setId(getCellValueAsString(row.getCell(0)));
					
					Chain chain = chains.getChainByName(getCellValueAsString(row.getCell(1)));
					if (chain == null) {
						throw new IllegalArgumentException("Invalid chain id <" + getCellValueAsString(row.getCell(1))
								+ "> found while reading <Restaurant> spread sheet");
					}
					restaurant.setChainId(chain.getId());
					restaurant.setCountry(getCellValueAsString(row.getCell(2)));
					restaurant.setProvState(getCellValueAsString(row.getCell(3)));
					restaurant.setCity(getCellValueAsString(row.getCell(4)));
					restaurant.setPostalZip(getCellValueAsString(row.getCell(5)));
					restaurant.setStreet(getCellValueAsString(row.getCell(6)));
					restaurant.setPhoneNumber(getCellValueAsString(row.getCell(7)));
					restaurant.setLongitude(getCellValueAsString(row.getCell(8)));
					restaurant.setLatitude(getCellValueAsString(row.getCell(9)));
					restaurant.setTimeZone(getCellValueAsString(row.getCell(10)));
					restaurant.setOpenTime(getCellValueAsStringList(row.getCell(11)));
					restaurant.setCloseTime(getCellValueAsStringList(row.getCell(12)));
					restaurant.setMinDelivery(getCellValueAsString(row.getCell(13)));
					restaurant.setDeliveryFee(getCellValueAsString(row.getCell(14)));
					List<AcceptedPayments> acceptedPayments = new ArrayList<AcceptedPayments>();
					List<String> paymentTypes = getCellValueAsStringList(row.getCell(15));
					for (String paymentType : paymentTypes) {
						AcceptedPayments payments = new AcceptedPayments();
						payments.setPaymentType(paymentType);
						acceptedPayments.add(payments);
					}
					
					restaurant.setAcceptedPayments(acceptedPayments);
					
					restaurant.setStatus(RestaurantStatus.valueOf(getCellValueAsString(row.getCell(16))));

					restaurantList.add(restaurant);

				}
			}
		}

		return restaurants;
	}
}

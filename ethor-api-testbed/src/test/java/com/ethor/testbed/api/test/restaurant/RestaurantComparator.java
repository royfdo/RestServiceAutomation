package com.ethor.testbed.api.test.restaurant;

import static com.ethor.testbed.api.test.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import com.ethor.testbed.api.domain.restaurant.Restaurant;
import com.ethor.testbed.api.test.data.UnExpectedValue;

public class RestaurantComparator {

	public static List<UnExpectedValue> compare(Restaurant expected, Restaurant actual) {
		List<UnExpectedValue> expectedValueList = new ArrayList<UnExpectedValue>();
		if (expected != null && actual != null) {

			assertSame(expected.getChainId(), actual.getChainId(), "chaind id", expectedValueList);
			assertSame(expected.getCity(), actual.getCity(), "city", expectedValueList);
			assertSame(expected.getCountry(), actual.getCountry(), "country", expectedValueList);
			assertSame(expected.getDeliveryFee(), actual.getDeliveryFee(), "deliveryFee", expectedValueList);
			assertSame(expected.getId(), actual.getId(), "id", expectedValueList);
			assertSame(expected.getLatitude(), actual.getLatitude(), "latitude", expectedValueList);
			assertSame(expected.getLongitude(), actual.getLongitude(), "longitude", expectedValueList);
			assertSame(expected.getMinDelivery(), actual.getMinDelivery(), "minDelivery", expectedValueList);
			assertSame(expected.getPhoneNumber(), actual.getPhoneNumber(), "phoneNumber", expectedValueList);
			assertSame(expected.getPostalZip(), actual.getPostalZip(), "postalZip", expectedValueList);
			assertSame(expected.getProvState(), actual.getProvState(), "provState", expectedValueList);
			assertSame(expected.getStatus().toString(), actual.getStatus().toString(), "status", expectedValueList);
			assertSame(expected.getStreet(), actual.getStreet(), "street", expectedValueList);
			assertSame(expected.getTimeZone(), actual.getTimeZone(), "timeZone", expectedValueList);
			assertSame(expected.getAcceptedPayments(), actual.getAcceptedPayments(), "acceptedPayment", expectedValueList);
			assertSame(expected.getOpenTime(), actual.getOpenTime(), "openTime", expectedValueList);
			assertSame(expected.getCloseTime(), actual.getCloseTime(), "closeTime", expectedValueList);

		}
		return expectedValueList;

	}
}

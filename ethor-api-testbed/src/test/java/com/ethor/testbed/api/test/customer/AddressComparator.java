package com.ethor.testbed.api.test.customer;

import static com.ethor.testbed.api.test.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import com.ethor.testbed.api.domain.customer.Address;
import com.ethor.testbed.api.test.data.UnExpectedValue;

public class AddressComparator {

	public static List<UnExpectedValue> compare(final Address expected, final Address actual,
			final String attributePrefix) {
		List<UnExpectedValue> expectedValueList = new ArrayList<UnExpectedValue>();
		if (expected != null && actual != null) {

			assertSame(expected.getCity(), actual.getCity(), attributePrefix + "city", expectedValueList);
			assertSame(expected.getCountry(), actual.getCountry(), attributePrefix + "country", expectedValueList);
			assertSame(expected.getPostalZip(), actual.getPostalZip(), attributePrefix + "postalZip", expectedValueList);
			assertSame(expected.getProvState(), actual.getProvState(), attributePrefix + "provState", expectedValueList);
			assertSame(expected.getStreet(), actual.getStreet(), attributePrefix + "street", expectedValueList);
			assertSame(expected.getStreetNumber(), actual.getStreetNumber(), attributePrefix + "streetNumber",
					expectedValueList);
			assertSame(expected.getSuiteNumber(), actual.getSuiteNumber(), attributePrefix + "suiteNumber",
					expectedValueList);
			assertSame(expected.getType(), actual.getType(), attributePrefix + "type", expectedValueList);

		}

		return expectedValueList;

	}

}

package com.ethor.testbed.api.test.customer;

import static com.ethor.testbed.api.test.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import com.ethor.testbed.api.domain.customer.Customer;
import com.ethor.testbed.api.test.data.UnExpectedValue;

public class CustomerComparator {

	public static List<UnExpectedValue> compare(Customer expected, Customer actual) {
		List<UnExpectedValue> expectedValueList = null;
		if (expected != null && actual != null) {
			expectedValueList = new ArrayList<UnExpectedValue>();
			assertSame(expected.getId(), actual.getId(), "id", expectedValueList);
			assertSame(expected.getBirthDate(), actual.getBirthDate(), "birth date", expectedValueList);
			assertSame(expected.getEmail(), actual.getEmail(), "email", expectedValueList);
			assertSame(expected.getFirstName(), actual.getFirstName(), "firstName", expectedValueList);
			assertSame(expected.getLastName(), actual.getLastName(), "lastName", expectedValueList);
			assertSame(expected.getGender(), actual.getGender(), "gender", expectedValueList);

			expectedValueList.addAll(AddressComparator.compare(expected.getBillingAddress(),
					actual.getBillingAddress(), "billingAddress."));
			
			StringBuilder phoneNumbersList = new StringBuilder();
			boolean phoneNumberFound = false;
			for (int i = 0; i < actual.getPhoneNumbers().size(); i++) {
				phoneNumbersList.append(actual.getPhoneNumbers().get(i));
				if(actual.getPhoneNumbers().get(i).equals(expected.getPhoneNumbers().get(0))) {
					phoneNumberFound = true;
					break;
				}
			}
			
			if (!phoneNumberFound) {
				expectedValueList.add(new UnExpectedValue("phoneNumber", expected.getPhoneNumbers().get(0).toString(), phoneNumbersList.toString()));
			}
			

		}

		return expectedValueList;

	}

}

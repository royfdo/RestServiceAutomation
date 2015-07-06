package com.ethor.testbed.api.test.special;

import static com.ethor.testbed.api.test.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import com.ethor.testbed.api.domain.special.Special;
import com.ethor.testbed.api.test.data.UnExpectedValue;

public class SpecialComparator {

	public static List<UnExpectedValue> compare(Special expected, Special actual) {
		List<UnExpectedValue> expectedValueList = new ArrayList<UnExpectedValue>();

		if (expected != null && actual != null) {

			assertSame(expected.getCouponCodes(), actual.getCouponCodes(), "couponCodes", expectedValueList);
			assertSame(expected.getDescription(), actual.getDescription(), "description", expectedValueList);
			assertSame(expected.getDisplayOrder(), actual.getDisplayOrder(), "displayOrder", expectedValueList);
			assertSame(expected.getEffectiveDate(), actual.getEffectiveDate(), "effectiveDate", expectedValueList);
			assertSame(expected.getExclusive(), actual.getExclusive(), "exclusive", expectedValueList);
			assertSame(expected.getExpireDate(), actual.getExpireDate(), "expireDate", expectedValueList);
			assertSame(expected.getId(), actual.getId(), "id", expectedValueList);
			assertSame(expected.getImage(), actual.getImage(), "image", expectedValueList);
			assertSame(expected.getLimitPerOrder(), actual.getLimitPerOrder(), "limitPerOrder", expectedValueList);
			assertSame(expected.getLongDescription(), actual.getLongDescription(), "longDescription", expectedValueList);
			assertSame(expected.getMaxUsage(), actual.getMaxUsage(), "maxUsage", expectedValueList);
			assertSame(expected.getName(), actual.getName(), "maxUsage", expectedValueList);
			assertSame(expected.getName(), actual.getName(), "maxUsage", expectedValueList);

			if (expected.getSpecialItems().size() != expected.getSpecialItems().size()) {
				expectedValueList.add(new UnExpectedValue("# of specialItems", String.valueOf(expected
						.getSpecialItems().size()), String.valueOf(expected.getSpecialItems().size())));
			} else {
				for (int i = 0; i < expected.getSpecialItems().size(); i++) {
					expectedValueList = SpecialItemsComparator.compare(expected.getSpecialItems().get(i), actual.getSpecialItems().get(i),
							i, expectedValueList);
				}
			}

			assertSame(expected.getValidDaysOfWeek(), actual.getValidDaysOfWeek(), "validDaysOfWeek", expectedValueList);
			assertSame(expected.getValidFromTime(), actual.getValidFromTime(), "validFromTime", expectedValueList);
			assertSame(expected.getValidToTime(), actual.getValidToTime(), "validToTime", expectedValueList);
			assertSame(expected.getVisible(), actual.getVisible(), "visible", expectedValueList);
		}

		return expectedValueList;
	}

}

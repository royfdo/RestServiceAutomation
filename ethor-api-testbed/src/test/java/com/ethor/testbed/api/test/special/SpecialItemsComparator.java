package com.ethor.testbed.api.test.special;

import static com.ethor.testbed.api.test.Assert.assertSame;

import java.util.List;

import com.ethor.testbed.api.domain.special.SpecialItems;
import com.ethor.testbed.api.test.data.UnExpectedValue;

public class SpecialItemsComparator {

	public static List<UnExpectedValue> compare(SpecialItems expected, SpecialItems actual, int index,
			List<UnExpectedValue> expectedValueList) {

		if (expected != null && actual != null) {

			assertSame(expected.getDescription(), actual.getDescription(), "specialItems[" + index + "].description",
					expectedValueList);
			assertSame(expected.getDisplayOrder(), actual.getDisplayOrder(),
					"specialItems[" + index + "].displayOrder", expectedValueList);
			assertSame(expected.getId(), actual.getId(), "specialItems[" + index + "].id", expectedValueList);
			assertSame(expected.getImage(), actual.getImage(), "specialItems[" + index + "].image", expectedValueList);
			assertSame(expected.getIncludedContents(), actual.getIncludedContents(), "specialItems[" + index
					+ "].includedContents", expectedValueList);
			assertSame(expected.getMinQuantity(), actual.getMinQuantity(), "specialItems[" + index + "].minQuantity",
					expectedValueList);
			assertSame(expected.getName(), actual.getName(), "specialItems[" + index + "].name", expectedValueList);

			for (int i = 0; i < expected.getItemSelections().size(); i++) {

				ItemSelectionsComparator.compare(expected.getItemSelections().get(i),
						actual.getItemSelections().get(i), "specialItems[" + index + "].itemSelections[" + i + "]",
						expectedValueList);
			}

		}

		return expectedValueList;
	}

}

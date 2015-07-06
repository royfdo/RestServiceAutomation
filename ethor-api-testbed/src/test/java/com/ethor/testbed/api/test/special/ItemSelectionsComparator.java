package com.ethor.testbed.api.test.special;

import static com.ethor.testbed.api.test.Assert.assertSame;

import java.util.List;

import com.ethor.testbed.api.domain.special.ItemSelections;
import com.ethor.testbed.api.test.data.UnExpectedValue;

public class ItemSelectionsComparator {

	public static List<UnExpectedValue> compare(ItemSelections expected, ItemSelections actual, String attributePrefix,
			List<UnExpectedValue> expectedValueList) {

		if (expected != null && actual != null) {

			assertSame(expected.getMenuItem(), actual.getMenuItem(), attributePrefix + ".menuItem", expectedValueList);

			for (int i = 0; i < expected.getSizeSelections().size(); i++) {
				SizeSelectionComparator.compare(expected.getSizeSelections().get(i), actual.getSizeSelections().get(i),
						attributePrefix + ".sizeSelections[" + i + "]", expectedValueList);
			}
		}

		return expectedValueList;
	}
}

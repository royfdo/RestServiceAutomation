package com.ethor.testbed.api.test.special;

import static com.ethor.testbed.api.test.Assert.assertSame;

import java.util.List;

import com.ethor.testbed.api.domain.special.SizeSelection;
import com.ethor.testbed.api.test.data.UnExpectedValue;

public class SizeSelectionComparator {

	public static List<UnExpectedValue> compare(SizeSelection expected, SizeSelection actual, String attributePrefix,
			List<UnExpectedValue> expectedValueList) {

		if (expected != null && actual != null) {

			assertSame(expected.getDisplayOrder(), actual.getDisplayOrder(), attributePrefix + ".displayOrder",
					expectedValueList);
			assertSame(expected.getGroupDisplayOrder(), actual.getGroupDisplayOrder(), attributePrefix
					+ ".groupDisplayOrder", expectedValueList);
			assertSame(expected.getGroupName(), actual.getGroupName(), attributePrefix + ".groupName",
					expectedValueList);
			assertSame(expected.getId(), actual.getId(), attributePrefix + ".id", expectedValueList);
			assertSame(expected.getName(), actual.getName(), attributePrefix + ".name", expectedValueList);
		}

		return expectedValueList;
	}

}

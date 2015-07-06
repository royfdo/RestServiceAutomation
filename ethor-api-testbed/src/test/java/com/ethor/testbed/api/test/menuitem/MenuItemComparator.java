package com.ethor.testbed.api.test.menuitem;

import static com.ethor.testbed.api.test.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import com.ethor.testbed.api.domain.menuitem.Contents;
import com.ethor.testbed.api.domain.menuitem.MenuItem;
import com.ethor.testbed.api.test.data.UnExpectedValue;

public class MenuItemComparator {

	public static List<UnExpectedValue> compare(final MenuItem expected, final MenuItem actual) {
		List<UnExpectedValue> expectedValueList = new ArrayList<UnExpectedValue>();
		if (expected != null && actual != null) {

			assertSame(expected.getName(), actual.getName(), "name", expectedValueList);
			assertSame(expected.getDescription().trim().replaceAll("\\s","").replaceAll("<br />", ""), actual.getDescription().trim().replaceAll("\\s",""), "description", expectedValueList);
			assertSame(expected.getImage(), actual.getImage(), "image", expectedValueList);
			assertSame(expected.getSizesAsStringList(), actual.getSizesAsStringList(), "sizes", expectedValueList);

			if (expected.getContentGroups().size() != actual.getContentGroups().size()) {
				expectedValueList.add(new UnExpectedValue("contentGroups", "count - "
						+ expected.getContentGroups().size(), "count - " + actual.getContentGroups().size()));
			} else {
				assertSame(expected.getContentGroups().get(0).getMinContents(), actual.getContentGroups().get(0).getMinContents(), "contentGroups.minContents", expectedValueList);
				assertSame(expected.getContentGroups().get(0).getMaxContents(), actual.getContentGroups().get(0).getMaxContents(), "contentGroups.maxContents", expectedValueList);
				List<Contents> expectedContents = expected.getContentGroups().get(0).getContents();
				List<Contents> actualContents = actual.getContentGroups().get(0).getContents();
				if (expectedContents.size() != actualContents.size()) {
					expectedValueList.add(new UnExpectedValue("contentGroups.content", "count - "
							+ expected.getContentGroups().size(), "count - " + actual.getContentGroups().size()));
				} else {

					for (int i = 0; i < expectedContents.size(); i++) {
						assertSame(expectedContents.get(i).getName().trim(), actualContents.get(i).getName().trim(),
								"contentGroups.content.name", expectedValueList);						
						assertSame(expectedContents.get(i).getPortionSelectionsAsStringList(), actualContents.get(i)
								.getPortionSelectionsAsStringList(), "contentGroups.content.portionSelections", expectedValueList);
						assertSame(expectedContents.get(i).getAmountSelectionsAsStringList(), actualContents.get(i)
								.getAmountSelectionsAsStringList(), "contentGroups.content.amountSelections", expectedValueList);
					}
				}

			}

		}
		return expectedValueList;
	}
}

package com.ethor.testbed.api.test.category;

import static com.ethor.testbed.api.test.Assert.assertNotNull;

import com.ethor.testbed.api.domain.category.Category;
import com.ethor.testbed.api.test.AbstractAPITest;

/**
 * Abstract test class for chains.
 * 
 * @author Roy Fernando.
 */
public abstract class AbstractCategoryTest extends AbstractAPITest {

	protected static final String VALID_CATEGORY_ID = "e5778c";
	protected static final String INVALID_CATEGORY_ID = "-1";

	protected static final String INVALID_RESTAURANT_ID = "-1";

	protected void validateCategory(final Category category) {
		assertNotNull("Category id should not be null", category.getId());
		assertNotNull("Category name should not be null", category.getName());
	}

}

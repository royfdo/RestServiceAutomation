package com.ethor.testbed.api.test.category;

import static com.ethor.testbed.api.test.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ethor.testbed.api.domain.category.Categories;
import com.ethor.testbed.api.domain.category.Category;
import com.ethor.testbed.api.test.exception.ValidationException;
import com.ethor.testbed.api.test.reference.store.CategoryStore;
import com.ethor.testbed.api.test.reference.store.ChainStore;
import com.ethor.testbed.api.test.reference.store.ReferenceStore;
import com.ethor.testbed.api.test.reference.store.RestaurantStore;
import com.ethor.testbed.api.test.report.TestFormat;
import com.ethor.testbed.api.test.report.TestModule;
import com.ethor.testbed.api.test.report.TestResult;
import com.ethor.testbed.api.test.report.TestStatus;

/**
 * Retrieve categories API test.
 * 
 * @author Roy Fernando.
 */
public class RetrieveCategoriesTest extends AbstractCategoryTest {

	public void retrieveCategories() {
		retrieve(TestFormat.JSON);
		retrieve(TestFormat.XML);
	}

	private void retrieve(final TestFormat format) {

		ReferenceStore referenceStore = (ReferenceStore) getTestSession().get(REFERENCE_STORE);

		if (referenceStore != null) {
			for (ChainStore chainStore : referenceStore.getChainStores()) {

				for (RestaurantStore restaurantStore : chainStore.getRestaurantStores()) {

					List<CategoryStore> categoryStores = new ArrayList<CategoryStore>();

					TestResult testResult = initializeTestResults(
							"Retrieve restaurants for categories for given chain and restaurant", format,
							TestModule.CATEGORY);
					testResult.addRequestParam("chainId", "[" + chainStore.getChain().getId() + "], "
							+ chainStore.getChain().getName());
					testResult.addRequestParam("restaurantId", "[" + restaurantStore.getRestaurant().getId() + "], "
							+ restaurantStore.getRestaurant().getStreet());
					String url = getUrl("chains/" + chainStore.getChain().getId() + "/restaurants/"
							+ restaurantStore.getRestaurant().getId() + "/categories."
							+ format.toString().toLowerCase() + "?accessKey=" + getAccessKey());
					testResult.setUrl(url);
					try {
						ResponseEntity<Categories> responseEntity = getTemplate().getForEntity(url, Categories.class);

						validateResponse(responseEntity.getStatusCode());
						validateResponseType(format, responseEntity.getHeaders().getContentType());
						List<String> categoryIds = new ArrayList<String>();
						List<String> validationErrors = new ArrayList<String>();
						Categories categories = responseEntity.getBody();
						for (Category category : categories.getCategories()) {
							if (categoryIds.contains(category.getId())) {
								throw new ValidationException("Duplicate category id, category id must be unique");
							}
							categoryIds.add(category.getId());
							assertNotNull(category.getId(), "Category id should not be null", validationErrors);
							assertNotNull(category.getName(), "Category name should not be null", validationErrors);

							categoryStores.add(new CategoryStore(category));
						}

						restaurantStore.setCategoryStores(categoryStores);
						updateTestResults(testResult, responseEntity);

						if (!validationErrors.isEmpty()) {
							throw new com.ethor.testbed.api.test.exception.ValidationException(validationErrors);
						}

					} catch (Exception e) {
						handleExceptions(testResult, e);
					}
				}
			}
		}

	}

	private void updateTestResults(final TestResult testResult, final ResponseEntity<Categories> responseEntity) {
		if (responseEntity.getBody().getCategories().isEmpty()) {
			testResult.setStatus(TestStatus.FAIL);
			testResult.addTestDetails("Categories not found");
		} else {
			StringBuilder categoriesString = new StringBuilder();
			for (int i = 0; i < responseEntity.getBody().getCategories().size(); i++) {
				categoriesString.append("[");
				categoriesString.append(responseEntity.getBody().getCategories().get(i).getName());
				categoriesString.append("]");
				if (i != responseEntity.getBody().getCategories().size() - 1) {
					categoriesString.append(", ");
				}
			}
			testResult.addTestDetails("Categories retrieved : " + categoriesString.toString());
		}
	}

}

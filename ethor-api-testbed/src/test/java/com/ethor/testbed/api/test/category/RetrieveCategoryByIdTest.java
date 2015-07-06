package com.ethor.testbed.api.test.category;

import static com.ethor.testbed.api.test.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.http.ResponseEntity;

import com.ethor.testbed.api.domain.category.Categories;
import com.ethor.testbed.api.domain.category.Category;
import com.ethor.testbed.api.test.data.UnExpectedValue;
import com.ethor.testbed.api.test.report.TestFormat;
import com.ethor.testbed.api.test.report.TestModule;
import com.ethor.testbed.api.test.report.TestResult;
import com.ethor.testbed.api.test.report.TestStatus;

/**
 * Retrieve category by id API test.
 * 
 * @author Roy Fernando.
 */
public class RetrieveCategoryByIdTest extends AbstractCategoryTest {

	public void retrieveCategory() {
		Categories categoriesToBeTested = (Categories) testSession.get("CATEGORY_TEST_DATA");
		if (categoriesToBeTested != null) {
			retrieve(TestFormat.JSON, categoriesToBeTested);
			retrieve(TestFormat.XML, categoriesToBeTested);
		}
	}

	private void retrieve(final TestFormat format, final Categories categoriesToBeTested) {
		for (Category category : categoriesToBeTested.getCategories()) {
			TestResult testResult = initializeTestResults("Retrieve category for given id", format,
					TestModule.CATEGORY_BYID);
			ResponseEntity<Category> responseEntity = null;
			try {

				for (Entry<String, String> metaData : category.getMetaDataMap().entrySet()) {
					testResult.addRequestParam(metaData.getKey(), metaData.getValue());
				}
				String url = getUrl("/chains/" + category.getMetaDataByKey("ChainId") + "/restaurants/"
						+ category.getMetaDataByKey("RestaurantId") + "/categories/" + category.getId() + "."
						+ format.toString().toLowerCase() + "?accessKey=" + getAccessKey());
				testResult.setUrl(url);
				responseEntity = getTemplate().getForEntity(url, Category.class);
				validateResponse(responseEntity.getStatusCode());
				validateResponseType(format, responseEntity.getHeaders().getContentType());

				List<UnExpectedValue> unExpectedValues = new ArrayList<UnExpectedValue>();
				assertSame(category.getId(), responseEntity.getBody().getId(), "CategoryId", unExpectedValues);
				assertSame(category.getName(), responseEntity.getBody().getName(), "CategoryName", unExpectedValues);

				if (!unExpectedValues.isEmpty()) {
					testResult.setStatus(TestStatus.FAIL);
					testResult.buildUnExpectedValueOutput(unExpectedValues);
				} else {
					testResult.addTestDetails("Successful");
				}

			} catch (Exception e) {
				handleExceptions(testResult, e);
			}
		}
	}
}

package com.ethor.testbed.api.test.chain;

import static com.ethor.testbed.api.test.Assert.assertNotNull;
import static com.ethor.testbed.api.test.Assert.assertTrue;

import org.springframework.http.ResponseEntity;

import com.ethor.testbed.api.domain.Count;
import com.ethor.testbed.api.test.report.TestFormat;
import com.ethor.testbed.api.test.report.TestModule;
import com.ethor.testbed.api.test.report.TestResult;

/**
 * Retrieve chains API test.
 * 
 * @author Roy Fernando.
 */
public class RetrieveChainsCountTest extends AbstractChainTest {

	/**
	 * Requesting chain count as json.
	 */
	public void retrieveChainCount() {
		retrieve(TestFormat.JSON);
		retrieve(TestFormat.XML);
	}

	private void retrieve(final TestFormat format) {

		if (getAccessKey() != null) {
			TestResult testResult = initializeTestResults("Retrieve chain count", format, TestModule.CHAIN_COUNT);
			try {
				String url = getUrl("/chains/count." + format.toString().toLowerCase() + "?accessKey=" + getAccessKey());
				testResult.setUrl(url);

				ResponseEntity<Count> responseEntity = getTemplate().getForEntity(url, Count.class);

				validateResponse(responseEntity.getStatusCode());
				validateResponseType(format, responseEntity.getHeaders().getContentType());

				Count chainCount = responseEntity.getBody();
				validateChainCount(chainCount);
				testResult.addTestDetails("Retrieved chain count : " + chainCount.getCount());
			} catch (Exception e) {
				handleExceptions(testResult, e);
			}
		}

	}

	private void validateChainCount(final Count chainCount) {
		assertNotNull(chainCount.getCount(), "Chain count may not be null");
		assertTrue(chainCount.getCount() >= 0, "Chain count may not be a negative number");
	}
}

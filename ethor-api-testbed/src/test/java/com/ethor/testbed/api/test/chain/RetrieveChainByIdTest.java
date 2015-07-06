package com.ethor.testbed.api.test.chain;

import static com.ethor.testbed.api.test.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ethor.testbed.api.domain.chain.Chain;
import com.ethor.testbed.api.domain.chain.Chains;
import com.ethor.testbed.api.test.data.UnExpectedValue;
import com.ethor.testbed.api.test.report.TestFormat;
import com.ethor.testbed.api.test.report.TestModule;
import com.ethor.testbed.api.test.report.TestResult;
import com.ethor.testbed.api.test.report.TestStatus;

/**
 * Retrieve chain by id API test.
 * 
 * @author Roy Fernando.
 */
public class RetrieveChainByIdTest extends AbstractChainTest {

	public void retrieveChain() {
		Chains chainsToBeTested = (Chains) testSession.get("CHAIN_TEST_DATA");
		if (chainsToBeTested != null) {
			retrieve(TestFormat.JSON, chainsToBeTested);
			retrieve(TestFormat.XML, chainsToBeTested);
		}
	}
	
	private void retrieve(final TestFormat format, Chains chainsToBeTested) {
		for (Chain chain : chainsToBeTested.getChains()) {
			TestResult testResult = initializeTestResults("Retrieve chain for given id", format,
					TestModule.CHAIN_BYID);
			ResponseEntity<Chain> responseEntity = null;
			try {
				
				testResult.addRequestParam("chaindId", chain.getId());
				String url = getUrl("/chains/" + chain.getId() + "." + format.toString().toLowerCase() + "?accessKey=" + getAccessKey());
				testResult.setUrl(url);
				responseEntity = getTemplate().getForEntity(url, Chain.class);
				validateResponse(responseEntity.getStatusCode());
				validateResponseType(format, responseEntity.getHeaders().getContentType());
				
				List<UnExpectedValue> unExpectedValues = new ArrayList<UnExpectedValue>();
				assertSame(chain.getId(), responseEntity.getBody().getId(), "ChainId", unExpectedValues);
				assertSame(chain.getName(), responseEntity.getBody().getName(), "ChainName", unExpectedValues);
				
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

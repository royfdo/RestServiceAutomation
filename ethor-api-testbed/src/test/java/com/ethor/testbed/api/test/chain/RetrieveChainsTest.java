package com.ethor.testbed.api.test.chain;

import static com.ethor.testbed.api.test.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ethor.testbed.api.domain.chain.Chain;
import com.ethor.testbed.api.domain.chain.Chains;
import com.ethor.testbed.api.test.exception.ValidationException;
import com.ethor.testbed.api.test.reference.store.ChainStore;
import com.ethor.testbed.api.test.reference.store.ReferenceStore;
import com.ethor.testbed.api.test.report.TestFormat;
import com.ethor.testbed.api.test.report.TestModule;
import com.ethor.testbed.api.test.report.TestResult;
import com.ethor.testbed.api.test.report.TestStatus;

/**
 * Retrieve available chains API test.
 * 
 * @author Roy Fernando.
 */
public class RetrieveChainsTest extends AbstractChainTest {

	public void retrieveChains() {
		retrieve(TestFormat.JSON);
		retrieve(TestFormat.XML);
	}

	private void retrieve(TestFormat format) {
		if (getAccessKey() != null) {
			TestResult testResult = initializeTestResults("Retrieve all available chains", format, TestModule.CHAIN);
			ResponseEntity<Chains> responseEntity = null;

			String url = getUrl("/chains." + format.toString().toLowerCase()) + "?accessKey=" + getAccessKey();
			testResult.setUrl(url);

			try {
				responseEntity = getTemplate().getForEntity(url, Chains.class);
				validateResponse(responseEntity.getStatusCode());
				validateResponseType(format, responseEntity.getHeaders().getContentType());
				validateChains(responseEntity.getBody());
				updateTestResults(testResult, responseEntity);

				initiateReferenceStore(responseEntity);

			} catch (Exception e) {
				handleExceptions(testResult, e);
			}
		}
	}

	private void updateTestResults(final TestResult testResult, final ResponseEntity<Chains> responseEntity) {
		if (responseEntity.getBody().getChains().isEmpty()) {
			testResult.setStatus(TestStatus.FAIL);
			testResult.addTestDetails("Chains not found");
		} else {
			StringBuilder chainString = new StringBuilder();
			for (int i = 0; i < responseEntity.getBody().getChains().size(); i++) {
				chainString.append("[");
				chainString.append(responseEntity.getBody().getChains().get(i).getName());
				chainString.append("]");
				if (i != responseEntity.getBody().getChains().size() - 1) {
					chainString.append(", ");
				}
			}
			testResult.addTestDetails("Chains retrieved : " + chainString.toString());
		}
	}

	private void initiateReferenceStore(final ResponseEntity<Chains> responseEntity) {
		ReferenceStore referenceStore = new ReferenceStore();
		List<ChainStore> chainStores = new ArrayList<ChainStore>();
		for (Chain chain : responseEntity.getBody().getChains()) {
			chainStores.add(new ChainStore(chain));
		}
		referenceStore.setChainStores(chainStores);
		getTestSession().put(REFERENCE_STORE, referenceStore);
	}

	private void validateChains(final Chains chains) {
		if (chains.getChains().isEmpty()) {
			throw new ValidationException("Response is empty, chains not found");
		}
		List<String> validationErrors = new ArrayList<String>();
		List<String> chainIds = new ArrayList<String>();
		for (Chain chain : chains.getChains()) {
			if (chainIds.contains(chain.getId())) {
				validationErrors.add("Chain id - " + chain.getId() + ", is duplicated, chain id's should be unique");
			}
			chainIds.add(chain.getId());
			assertNotNull(chain.getId(), "Chain id is null", validationErrors);
			assertNotNull(chain.getName(), "Chain name is null", validationErrors);

		}

		if (!validationErrors.isEmpty()) {
			throw new ValidationException(validationErrors);
		}
	}

}

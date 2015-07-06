package com.ethor.testbed.api.test.chain;

import static com.ethor.testbed.api.test.Assert.assertNotNull;

import com.ethor.testbed.api.domain.chain.Chain;
import com.ethor.testbed.api.test.AbstractAPITest;

/**
 * Abstract class for chain related test classes.
 *
 * @author Roy Fernando.
 */
public abstract class AbstractChainTest extends AbstractAPITest {

	protected void validateChain(final Chain chain) {
		assertNotNull("Chain id should not be null", chain.getId());
		assertNotNull("Chain name should not be null", chain.getName());
	}

}

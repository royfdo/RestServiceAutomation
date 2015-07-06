package com.ethor.testbed.api.test.data.filtering;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractFilterTestData {

	private String chainId;
	private Map<String, String> filterDataMap = new HashMap<String, String>();
	private int expectedCount;

	public String getChainId() {
		return chainId;
	}

	public void setChainId(final String chainId) {
		this.chainId = chainId;
	}

	public Map<String, String> getFilterDataMap() {
		return filterDataMap;
	}

	public void addFilterData(final String filterName, final String value) {
		this.filterDataMap.put(filterName, value);
	}

	public int getExpectedCount() {
		return expectedCount;
	}

	public void setExpectedCount(final int expectedCount) {
		this.expectedCount = expectedCount;
	}

}

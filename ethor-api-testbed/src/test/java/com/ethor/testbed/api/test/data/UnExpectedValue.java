package com.ethor.testbed.api.test.data;

public class UnExpectedValue {

	private final String attribute;
	private final String expectedValue;
	private final String actualValue;

	public UnExpectedValue(final String attribute, final String expectedValue, final String actualValue) {
		super();
		this.attribute = attribute;
		this.expectedValue = expectedValue;
		this.actualValue = actualValue;
	}

	public String getAttribute() {
		return attribute;
	}

	public String getExpectedValue() {
		return expectedValue;
	}

	public String getActualValue() {
		return actualValue;
	}

}

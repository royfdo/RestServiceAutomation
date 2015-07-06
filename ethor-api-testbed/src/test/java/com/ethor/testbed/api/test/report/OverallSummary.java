package com.ethor.testbed.api.test.report;

public class OverallSummary {

	private int totalTests;
	private int passTests;
	private int failTests;
	private int errorTests;

	public int getTotalTests() {
		return totalTests;
	}

	public void setTotalTests(int totalTests) {
		this.totalTests = totalTests;
	}

	public int getPassTests() {
		return passTests;
	}

	public void setPassTests(int passTests) {
		this.passTests = passTests;
	}

	public int getFailTests() {
		return failTests;
	}

	public void setFailTests(int failTests) {
		this.failTests = failTests;
	}

	public int getErrorTests() {
		return errorTests;
	}

	public void setErrorTests(int errorTests) {
		this.errorTests = errorTests;
	}
}

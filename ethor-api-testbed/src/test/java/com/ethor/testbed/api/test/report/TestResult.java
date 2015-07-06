package com.ethor.testbed.api.test.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ethor.testbed.api.test.data.UnExpectedValue;

public class TestResult {

	private String testName;
	private TestModule testModule;
	private String url;
	private Map<String, String> requestParams = new HashMap<String, String>();
	private TestStatus status;
	private TestFormat format;
	private List<String> testDetails = new ArrayList<String>();

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public TestModule getTestModule() {
		return testModule;
	}

	public void setTestModule(TestModule testModule) {
		this.testModule = testModule;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public TestStatus getStatus() {
		return status;
	}

	public void setStatus(TestStatus status) {
		this.status = status;
	}

	public TestFormat getFormat() {
		return format;
	}

	public void setFormat(TestFormat format) {
		this.format = format;
	}

	public Map<String, String> getRequestParams() {
		return requestParams;
	}

	public void addRequestParam(String name, String value) {
		requestParams.put(name, value);
	}

	public List<String> getTestDetails() {
		return testDetails;
	}

	public void addTestDetails(String detail) {
		testDetails.add(detail);
	}

	public void mergeTestDetails(List<String> list) {
		testDetails.addAll(list);
	}

	public void buildUnExpectedValueOutput(List<UnExpectedValue> expectedValues) {

		StringBuilder stringBuilder = new StringBuilder(
				"<table width='100%' class='expected'><th>Attribute</th><th>Expected</th><th>Actual</th>");

		for (UnExpectedValue unExpectedValue : expectedValues) {
			stringBuilder.append("<tr><td>" + unExpectedValue.getAttribute() + "</td><td>"
					+ unExpectedValue.getExpectedValue() + "</td><td>" + unExpectedValue.getActualValue()
					+ "</td></tr>");
		}
		stringBuilder.append("</table>");
		addTestDetails(stringBuilder.toString());
	}

}

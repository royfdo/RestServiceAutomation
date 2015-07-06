package com.ethor.testbed.api.test.report;

import java.io.IOException;
import java.util.List;

import freemarker.template.TemplateException;

public interface TestReportGenerator {

	public void generateReport(final List<TestResult> testResults) throws IOException, TemplateException;

}

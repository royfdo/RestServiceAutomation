package com.ethor.testbed.api.test.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.cache.FileTemplateLoader;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TestReportGeneratorImpl implements TestReportGenerator {

	@Override
	public void generateReport(List<TestResult> testResults) throws IOException, TemplateException {

		Map<TestModule, List<TestResult>> moduleViseMap = getModuleTestResults(testResults);
		List<ModuleSummary> moduleSummaries = new ArrayList<ModuleSummary>();
		OverallSummary overallSummary = new OverallSummary();

		String projectDir = System.getProperty("projectDir");
		if (projectDir == null || projectDir.isEmpty()) {
			projectDir = "ethor-api-testbed"; // in-order to run via eclipse
		}

		for (TestModule testModule : TestModule.values()) {
			File file = new File(projectDir + "/report/" + testModule.toString() + ".html");
			if (file.exists()) {
				file.delete();
			}
			List<TestResult> entry = moduleViseMap.get(testModule);
			if (entry != null) {

				// file.createNewFile();
				FileWriter fstream = new FileWriter(file);
				BufferedWriter out = new BufferedWriter(fstream);

				SimpleHash root = new SimpleHash();
				root.put("reportHeader", "ethor RestAPI test report");
				root.put("moduleHeader", testModule + " module");
				root.put("testResults", entry);

				Configuration cfg = new Configuration();
				FileTemplateLoader templateLoader = new FileTemplateLoader(new File(projectDir
						+ "/src/test/resources/templates"));
				cfg.setTemplateLoader(templateLoader);
				BeansWrapper beansWrapper = new BeansWrapper();
				beansWrapper.setExposeFields(true);
				Template temp = cfg.getTemplate("module.ftl");
				temp.process(root, out, beansWrapper);

				ModuleSummary moduleSummary = new ModuleSummary();
				moduleSummary.setModule(testModule);
				moduleSummaries.add(moduleSummary);
				for (TestResult testResult : entry) {
					updateTestCounts(testResult.getStatus(), moduleSummary, overallSummary);
				}
			}

		}

		File file = new File(projectDir + "/report/index.html");
		file.delete();

		FileWriter fstream = new FileWriter(file);
		BufferedWriter out = new BufferedWriter(fstream);

		SimpleHash root = new SimpleHash();
		root.put("reportHeader", "ethor RestAPI test report");
		root.put("overallSummary", overallSummary);
		root.put("moduleSummary", moduleSummaries);

		Configuration cfg = new Configuration();
		FileTemplateLoader templateLoader = new FileTemplateLoader(new File(projectDir
				+ "/src/test/resources/templates"));
		cfg.setTemplateLoader(templateLoader);
		BeansWrapper beansWrapper = new BeansWrapper();
		beansWrapper.setExposeFields(true);
		Template temp = cfg.getTemplate("summary.ftl");
		temp.process(root, out, beansWrapper);

	}

	private Map<TestModule, List<TestResult>> getModuleTestResults(List<TestResult> testResults) {
		Map<TestModule, List<TestResult>> map = new HashMap<TestModule, List<TestResult>>();

		for (TestResult testResult : testResults) {
			if (map.containsKey(testResult.getTestModule())) {
				map.get(testResult.getTestModule()).add(testResult);

			} else {
				List<TestResult> list = new ArrayList<TestResult>();
				list.add(testResult);
				map.put(testResult.getTestModule(), list);
			}
		}

		return map;
	}

	private void updateTestCounts(TestStatus testStatus, ModuleSummary moduleSummary, OverallSummary overallSummary) {
		moduleSummary.setTotalTests(moduleSummary.getTotalTests() + 1);
		overallSummary.setTotalTests(overallSummary.getTotalTests() + 1);
		switch (testStatus) {
		case PASS:
			moduleSummary.setPassTests(moduleSummary.getPassTests() + 1);
			overallSummary.setPassTests(overallSummary.getPassTests() + 1);
			break;
		case FAIL:
			moduleSummary.setFailTests(moduleSummary.getFailTests() + 1);
			overallSummary.setFailTests(overallSummary.getFailTests() + 1);
			break;
		case ERROR:
			moduleSummary.setErrorTests(moduleSummary.getErrorTests() + 1);
			overallSummary.setErrorTests(overallSummary.getErrorTests() + 1);
			break;
		default:
			break;
		}
	}

	public static void main(String[] args) throws IOException, TemplateException {
		List<TestResult> testResults = new ArrayList<TestResult>();

		TestResult testResult = new TestResult();
		testResult.setTestName("Retrieve all avilable chains");
		testResult.setStatus(TestStatus.PASS);
		testResult.addTestDetails("Six chains return");
		testResult.addTestDetails("Six chains return");
		testResult.addTestDetails("Six chains return");
		testResult.setTestModule(TestModule.CHAIN);
		testResults.add(testResult);

		testResult = new TestResult();
		testResult.setTestName("Retrieve a chain by id");
		testResult.setStatus(TestStatus.FAIL);
		testResult.addRequestParam("chainId", "grtrsddflo");
		testResult.addTestDetails("Invalid chain id");
		testResult.setTestModule(TestModule.RESTAURANT);
		testResults.add(testResult);

		new TestReportGeneratorImpl().generateReport(testResults);
	}

}

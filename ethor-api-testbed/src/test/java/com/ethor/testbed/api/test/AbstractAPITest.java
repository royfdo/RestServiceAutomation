package com.ethor.testbed.api.test;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestTemplate;

import com.ethor.testbed.RestClient;
import com.ethor.testbed.api.test.exception.ResponseException;
import com.ethor.testbed.api.test.exception.ValidationException;
import com.ethor.testbed.api.test.report.TestFormat;
import com.ethor.testbed.api.test.report.TestModule;
import com.ethor.testbed.api.test.report.TestResult;
import com.ethor.testbed.api.test.report.TestStatus;

/**
 * Base class for ethor API tests.
 * 
 * @author Roy Fernando.
 * 
 */
public abstract class AbstractAPITest {

	protected static final String ACCESS_KEY = "ACCESS_KEY";

	/** Holds Url path separator constant. */
	private static final String PATH_SEPARATOR = "/";

	protected static final String REFERENCE_STORE = "REFERENCE_STORE";

	/** Holds RestClient instance. */
	protected static RestClient restClient;

	protected static Map<String, Object> testSession;

	/**
	 * Getter method for spring RestTemplate.
	 * 
	 * @return spring RestTemplate
	 */
	protected RestTemplate getTemplate() {
		return restClient.getRestTemplate();
	}

	/**
	 * Getter method for baseUrl.
	 * 
	 * @return base url string value.
	 */
	protected String getBaseUrl() {
		return restClient.getBaseUrl();
	}

	protected static String getAccessKey() {
		return testSession.get(ACCESS_KEY) != null ? testSession.get(ACCESS_KEY).toString() : null;
	}

	protected static Map<String, Object> getTestSession() {
		return testSession;
	}

	/**
	 * Combines base url and path and returns the final url.
	 * 
	 * @param pathValue
	 *            string value of the request path.
	 * @return Url string.
	 */
	protected static String getUrl(final String pathValue) {
		String result;
		if (restClient.getBaseUrl().endsWith(PATH_SEPARATOR) && pathValue.startsWith(PATH_SEPARATOR)) {
			result = restClient.getBaseUrl() + pathValue.substring(1, pathValue.length());
		} else if ((!restClient.getBaseUrl().endsWith(PATH_SEPARATOR) && pathValue.startsWith(PATH_SEPARATOR))
				|| (restClient.getBaseUrl().endsWith(PATH_SEPARATOR) && !pathValue.startsWith(PATH_SEPARATOR))) {
			result = restClient.getBaseUrl() + pathValue;
		} else if (!restClient.getBaseUrl().endsWith(PATH_SEPARATOR) && !pathValue.startsWith(PATH_SEPARATOR)) {
			result = restClient.getBaseUrl() + PATH_SEPARATOR + pathValue;
		} else {
			throw new IllegalStateException("Invalid url format with base url " + restClient.getBaseUrl()
					+ " and path " + pathValue);
		}
		return result;
	}

	protected void setXmlMarshaller(Class... classes) {

		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setClassesToBeBound(classes);

		if (restClient.getRestTemplate().getMessageConverters() != null) {
			for (HttpMessageConverter<?> converter : restClient.getRestTemplate().getMessageConverters()) {
				if (converter instanceof MarshallingHttpMessageConverter) {
					((MarshallingHttpMessageConverter) converter).setMarshaller(jaxb2Marshaller);
					((MarshallingHttpMessageConverter) converter).setUnmarshaller(jaxb2Marshaller);
				}
			}
		}
	}

	/**
	 * Validates whether response is valid.
	 * 
	 * @param responseStatus
	 *            Http response status.
	 */
	protected void validateResponse(final HttpStatus responseStatus) {
		if (!HttpStatus.OK.equals(responseStatus)) {
			throw new ValidationException("Invalid Response");
		}

	}
	
	protected void validateResponseType(final TestFormat expectedFormat, final MediaType responseContentType) {
		if (expectedFormat.equals(TestFormat.JSON)) {
			validateJsonResponse(responseContentType);
		} else {
			validateXmlResponse(responseContentType);
		}
	}

	/**
	 * Validates whether response is in json format.
	 * 
	 * @param responseContentType
	 *            Response content type.
	 */
	protected void validateJsonResponse(final MediaType responseContentType) {
		if (!MediaType.APPLICATION_JSON.getSubtype().endsWith(responseContentType.getSubtype())) {
			throw new ValidationException("Response is not in json format");
		}
	}

	/**
	 * Validates whether response is in xml format.
	 * 
	 * @param responseContentType
	 *            Response content type.
	 */
	protected void validateXmlResponse(final MediaType responseContentType) {
		if (!MediaType.APPLICATION_XML.getSubtype().endsWith(responseContentType.getSubtype())) {
			throw new ValidationException("Response is not in xml format");
		}
	}

	/**
	 * Handles exceptions to update TestResult object.
	 * 
	 * @param testResult
	 *            TestResult instance.
	 * @param e
	 *            Exception.
	 */
	protected void handleExceptions(final TestResult testResult, final Exception e) {
		if (e instanceof ValidationException) {
			testResult.setStatus(TestStatus.FAIL);
			if (((ValidationException) e).getMessages() != null) {
				for (String validationMsg : ((ValidationException) e).getMessages()) {
					testResult.addTestDetails(validationMsg);
				}
			} else {
				testResult.addTestDetails(e.getMessage());
			}
		} else if (e instanceof ResponseException) {
			testResult.setStatus(TestStatus.ERROR);
			ResponseException responseException = (ResponseException) e;
			if (responseException.getCode() != null) {
				testResult.addTestDetails("Error Code : " + responseException.getCode());
				testResult.addTestDetails("Error Message : " + responseException.getMessage());
			} else {
				testResult.addTestDetails(responseException.getMessage());
			}
		} else {
			testResult.setStatus(TestStatus.ERROR);
			testResult.addTestDetails(e.getMessage());
		}
	}

	/**
	 * Initializes and sets the default values to TestResult object.
	 * 
	 * @param testName
	 *            Test method name.
	 * @param format
	 *            Test format
	 * @param module
	 *            Test module
	 * @return TestResult instance.
	 */
	protected TestResult initializeTestResults(final String testName, final TestFormat format, final TestModule module) {
		TestResult testResult = new TestResult();
		testResult.setTestName(testName);
		testResult.setFormat(format);
		testResult.setTestModule(module);
		testResult.setStatus(TestStatus.PASS);
		((List<TestResult>) getTestSession().get("TEST_RESULTS")).add(testResult);
		return testResult;
	}
}

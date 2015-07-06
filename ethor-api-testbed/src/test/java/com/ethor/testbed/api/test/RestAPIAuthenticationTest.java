package com.ethor.testbed.api.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import junit.framework.Assert;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.ethor.testbed.BasicSecureSimpleClientHttpRequestFactory;
import com.ethor.testbed.Credentials;
import com.ethor.testbed.CredentialsProvider;
import com.ethor.testbed.SimpleCrendentialsProvider;
import com.ethor.testbed.api.domain.Error;
import com.ethor.testbed.api.domain.Errors;

/**
 * Tests ethor Rest API authentications.
 * 
 * @author Roy Fernando.
 */
public class RestAPIAuthenticationTest extends AbstractAPITest {

	/**
	 * Test authentication details not provided scenario.
	 */
	@Test
	public void authenticationNotProvidedTest() {

		BasicSecureSimpleClientHttpRequestFactory noAuthenticationFactory = new BasicSecureSimpleClientHttpRequestFactory() {
			@Override
			protected void prepareSecureConnection(java.net.HttpURLConnection connection) {
				// Authentication details left to be blank
			}
		};
		RestTemplate restTemplate = new RestTemplate(noAuthenticationFactory);
		ResponseEntity<Errors> errorResponse = restTemplate.getForEntity(getBaseUrl() + "/json", Errors.class);

		Assert.assertNotNull("Errors may not be null", errorResponse.getBody());
		assertEquals("Only one error expected", 1, errorResponse.getBody().size());

		for (Error error : errorResponse.getBody()) {
			assertNotNull("Error status may not be null", error.getCode());
			assertEquals("Unexpected error status", 401, error.getDescription());

			assertNotNull("Error message may not be null", error.getDescription());
			assertEquals("Unexpected error message", "No  credentials were supplied in the request.", error.getDescription());
		}
	}

	/**
	 * Test invalid authentication details scenario.
	 */
	@Test
	public void invalidAuthenticationTest() {

		BasicSecureSimpleClientHttpRequestFactory invalidAuthenticationFactory = new BasicSecureSimpleClientHttpRequestFactory();
		CredentialsProvider credentialsProvider = new SimpleCrendentialsProvider(new Credentials("invalidUser", "invalidToken", ""));
		invalidAuthenticationFactory.setCredentialsProvider(credentialsProvider);

		RestTemplate restTemplate = new RestTemplate(invalidAuthenticationFactory);
		ResponseEntity<Errors> errorResponse = restTemplate.getForEntity(getBaseUrl() + "/json", Errors.class);

		Assert.assertNotNull("Errors may not be null", errorResponse.getBody());
		assertEquals("Only one error expected", 1, errorResponse.getBody().size());

		for (Error error : errorResponse.getBody()) {
			assertNotNull("Error status may not be null", error.getCode());
			assertEquals("Unexpected error status", 401, error.getCode());

			assertNotNull("Error message may not be null", error.getDescription());
			assertEquals("Unexpected error message", "The specified credentials were invalid.", error.getDescription());
		}
	}

}

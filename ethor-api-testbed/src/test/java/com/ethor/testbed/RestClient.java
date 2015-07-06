package com.ethor.testbed;

import org.springframework.web.client.RestTemplate;

public class RestClient {

	private final RestTemplate restTemplate;
	private final String baseUrl;
	private final Credentials credentials;

	public RestClient(final RestTemplate restTemplate, final String baseUrl, final Credentials credentials) {
		this.restTemplate = restTemplate;
		this.baseUrl = baseUrl;
		this.credentials = credentials;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public Credentials getCredentials() {
		return credentials;
	}

}
package com.ethor.testbed;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

abstract public class SecureSimpleClientHttpRequestFactory extends SimpleClientHttpRequestFactory {
	protected CredentialsProvider credentialsProvider;
	protected String realm;

	public SecureSimpleClientHttpRequestFactory() {
	}

	public CredentialsProvider getCredentialsProvider() {
		return credentialsProvider;
	}

	public void setCredentialsProvider(final CredentialsProvider credentialsProvider) {
		this.credentialsProvider = credentialsProvider;
	}

	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

	@Override
	public ClientHttpRequest createRequest(final URI uri, final HttpMethod httpMethod) throws IOException {
		HttpURLConnection connection = openConnection(uri.toURL(), null);
		prepareConnection(connection, httpMethod.name());
		prepareSecureConnection(connection);
		return createRequest(connection);
	}

	abstract protected void prepareSecureConnection(final HttpURLConnection connection);

	protected ClientHttpRequest createRequest(final HttpURLConnection connection) {
		return new SecureSimpleClientHttpRequest(connection, this);
	}
}

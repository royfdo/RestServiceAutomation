package com.ethor.testbed;

import java.net.HttpURLConnection;

import org.apache.commons.codec.binary.Base64;

public class BasicSecureSimpleClientHttpRequestFactory extends SecureSimpleClientHttpRequestFactory {

	public BasicSecureSimpleClientHttpRequestFactory() {
	}

	@Override
	protected void prepareSecureConnection(final HttpURLConnection connection) {
		if (credentialsProvider == null) {
			return;
		}
		Credentials credentials = credentialsProvider.getCredentials(realm);
		String token = credentials.getUserName() + ":" + credentials.getUserName();
		byte[] authEncBytes = Base64.encodeBase64(token.getBytes());
		String encodedAuthorization = new String(authEncBytes);
		connection.setRequestProperty("Authorization", "Basic " + encodedAuthorization);
		System.out.println("connection ::: " + connection.toString());
	}

}

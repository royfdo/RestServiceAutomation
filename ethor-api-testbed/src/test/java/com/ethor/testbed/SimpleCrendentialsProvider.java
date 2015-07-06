package com.ethor.testbed;

public class SimpleCrendentialsProvider implements CredentialsProvider {
	Credentials defaultCredentials;
	
	public SimpleCrendentialsProvider() {
	}

	public SimpleCrendentialsProvider(final Credentials defaultCredentials) {
		this.defaultCredentials = defaultCredentials;
	}
	
	public Credentials getCredentials(final String realm) {
		return defaultCredentials;
	}

}

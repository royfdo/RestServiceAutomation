package com.ethor.testbed;

public interface CredentialsProvider {
	Credentials getCredentials(String realm);
}

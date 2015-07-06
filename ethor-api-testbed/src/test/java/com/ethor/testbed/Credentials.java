package com.ethor.testbed;

public class Credentials {
	private String userName;
	private String password;
	private String apiKey;

	public Credentials(final String userName, final String password, final String apiKey) {
		this.userName = userName;
		this.password = password;
		this.apiKey = apiKey;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(final String apiKey) {
		this.apiKey = apiKey;
	}

}

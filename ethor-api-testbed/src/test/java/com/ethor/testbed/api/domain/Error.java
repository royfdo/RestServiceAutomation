package com.ethor.testbed.api.domain;

import java.util.List;

/**
 * This class encapsulates ethor rest API error.
 * 
 * @author Roy Fernando.
 */

public class Error {

	private String code;

	private String description;

	private List<Error> error;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Error> getError() {
		return error;
	}

	public void setError(List<Error> error) {
		this.error = error;
	}
}

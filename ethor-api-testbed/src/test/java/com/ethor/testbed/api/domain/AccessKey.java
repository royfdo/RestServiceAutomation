package com.ethor.testbed.api.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "accessKey")
public class AccessKey {

	@XmlElement
	private String accessKey;

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(final String accessKey) {
		this.accessKey = accessKey;
	}

}

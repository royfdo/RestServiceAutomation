package com.ethor.testbed.api.domain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;


/**
 * Class to encapsulate count.
 * 
 * @author Roy Fernando.
 */
@XmlRootElement (name="count")
public class Count {

	
	private Integer count;

	public Integer getCount() {
		return count;
	}

	@XmlValue
	public void setCount(final Integer count) {
		this.count = count;
	}
}

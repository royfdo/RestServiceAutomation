package com.ethor.testbed.api.domain;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * This class holds list of API errors.
 * 
 * @author Roy Fernando.
 */
@XmlRootElement(name = "objects")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Errors extends ArrayList<Error> {

	private static final long serialVersionUID = 2952024460092682299L;

	@XmlElement(name = "error")
	public void setErrors(final Error error) {
		this.add(error);
	}

}

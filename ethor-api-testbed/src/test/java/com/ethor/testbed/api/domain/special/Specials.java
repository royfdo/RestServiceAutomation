package com.ethor.testbed.api.domain.special;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * This class encapsulates list of specials.
 * 
 * @author Roy Fernando.
 */
@XmlRootElement(name = "specials")
public class Specials {

	@JsonProperty(value = "specials")
	private List<Special> specials;

	public List<Special> getSpecials() {
		return specials;
	}

	@XmlElement(name = "special")
	public void setSpecials(final List<Special> specials) {
		this.specials = specials;
	}

}

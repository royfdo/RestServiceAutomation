package com.ethor.testbed.api.domain.category;

import javax.xml.bind.annotation.XmlRootElement;

import com.ethor.testbed.api.domain.Entity;

/**
 * This class encapsulate attributes of a restaurant menu category.
 * 
 * @author Roy Fernando.
 * 
 */
@XmlRootElement(name = "category")
public class Category extends Entity {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

}

package com.ethor.testbed.api.domain.chain;

import javax.xml.bind.annotation.XmlRootElement;

import com.ethor.testbed.api.domain.Entity;

/**
 * This class encapsulate attributes of a chain.
 * 
 * @author Roy Fernando.
 * 
 */

@XmlRootElement(name = "chain")
public class Chain extends Entity {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Chain [name=" + name + ", getId()=" + getId() + ", getClass()=" + getClass() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chain other = (Chain) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}

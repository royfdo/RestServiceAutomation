package com.ethor.testbed.api.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Base class for all domain classes.
 * 
 * @author Roy Fernando.
 * 
 */
public abstract class Entity {

	private String id;
	
	private Map<String, String> metaDataMap = new HashMap<String, String>();

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}
	
	public void addMetaData(final String key, final String value) {
		metaDataMap.put(key, value);
	}
	
	public Map<String, String> getMetaDataMap() {
		return metaDataMap;
	}
	
	public String getMetaDataByKey(final String key) {
		return metaDataMap.get(key);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

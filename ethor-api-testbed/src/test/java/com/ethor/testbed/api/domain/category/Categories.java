package com.ethor.testbed.api.domain.category;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * This class holds list of categories.
 * 
 * @author Roy Fernando.
 */
@XmlRootElement(name = "categories")
public class Categories {

	@JsonProperty(value = "Categories")
	private List<Category> categories;

	public List<Category> getCategories() {
		return categories;
	}

	@XmlElement(name = "category")
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Category getCategoryById(final String categoryId) {
		if (categories != null) {
			for (Category category : categories) {
				if (category.getId().equals(categoryId)) {
					return category;
				}
			}
		}
		return null;
	}
}

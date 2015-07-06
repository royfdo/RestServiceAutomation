package com.ethor.testbed.api.domain.menuitem;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ethor.testbed.api.domain.Entity;

/**
 * This class encapsulate attributes of a restaurant menu items.
 * 
 * @author Roy Fernando.
 * 
 */
@XmlType
@XmlRootElement (name="menuItem")
public class MenuItem extends Entity {

	private String name;
	private String description;
	private String additionalInfo;
	private String lastModified;
	private String image;
	private String displayOrder;
	private List<Size> sizes;
	private List<ContentGroups> contentGroups;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(final String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(final String lastModified) {
		this.lastModified = lastModified;
	}

	public String getImage() {
		return image;
	}

	public void setImage(final String image) {
		this.image = image;
	}

	public String getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(final String displayOrder) {
		this.displayOrder = displayOrder;
	}

	public List<Size> getSizes() {
		return sizes;
	}
	
	public List<String> getSizesAsStringList() {
		List<String> list = new ArrayList<String>();
		if (sizes != null && !sizes.isEmpty()) {
			for (Size size : sizes) {
				list.add(size.getName());
			}
		}
		return list;
	}

	public void setSizes(final List<Size> sizes) {
		this.sizes = sizes;
	}

	public List<ContentGroups> getContentGroups() {
		return contentGroups;
	}

	public void setContentGroups(final List<ContentGroups> contentGroups) {
		this.contentGroups = contentGroups;
	}

}

package com.ethor.testbed.api.domain.menuitem;

import java.util.List;

public class ContentGroups {
	
	private String minContents;
	private String maxContents;
	
	private List<Contents> contents;
	
	
	public String getMinContents() {
		return minContents;
	}

	public void setMinContents(final String minContents) {
		this.minContents = minContents;
	}

	public String getMaxContents() {
		return maxContents;
	}

	public void setMaxContents(final String maxContents) {
		this.maxContents = maxContents;
	}

	public List<Contents> getContents() {
		return contents;
	}

	public void setContents(List<Contents> contents) {
		this.contents = contents;
	}
	
	

}

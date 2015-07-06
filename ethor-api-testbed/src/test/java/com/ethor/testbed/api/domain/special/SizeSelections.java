package com.ethor.testbed.api.domain.special;

import java.util.ArrayList;

/**
 * This class encapsulates list of size selections.
 * 
 * @author Roy Fernando.
 */
public class SizeSelections extends ArrayList<SizeSelection> {

	public void setSizeSelection(final SizeSelection selection) {
		add(selection);
	}

}

package com.ethor.testbed.api.domain.restaurant;

import javax.xml.bind.annotation.XmlEnum;

/**
 * Enum class representing restaurant status.
 * 
 * @author Roy Fernando.
 * 
 */
@XmlEnum
public enum RestaurantStatus {

	OPEN, CLOSED, TEST, UNLISTED, COMINGSOON;

	public static boolean contains(final String value) {
		for (RestaurantStatus status : RestaurantStatus.values()) {
			if (status.toString().equals(value)) {
				return true;
			}
		}
		return false;
	}

}

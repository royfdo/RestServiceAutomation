package com.ethor.testbed.api.domain.restaurant;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * This class holds list of restaurants.
 * 
 * @author Roy Fernando.
 */
@XmlRootElement(name = "restaurants")
public class Restaurants {

	@JsonProperty(value = "restaurants")
	private List<Restaurant> restaurants;

	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	@XmlElement(name = "restaurant")
	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	public Restaurant getRestaurantById(final String restaurantId) {
		if (restaurants != null) {
			for (Restaurant restaurant : restaurants) {
				if (restaurant.getId().equals(restaurantId)) {
					return restaurant;
				}
			}
		}
		return null;
	}
	
	public Restaurant getRestaurantByStreet(final String street) {
		if (restaurants != null) {
			for (Restaurant restaurant : restaurants) {
				if (restaurant.getStreet().trim().equals(street)) {
					return restaurant;
				}
			}
		}
		return null;
	}

}

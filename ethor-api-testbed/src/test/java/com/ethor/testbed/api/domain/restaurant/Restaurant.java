package com.ethor.testbed.api.domain.restaurant;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ethor.testbed.api.domain.Entity;

/**
 * This class encapsulates attributes of a restaurant.
 * 
 * @author Roy Fernando.
 * 
 */
@XmlType
@XmlRootElement (name="restaurant")
public class Restaurant extends Entity {

	private String chainId;
	private String country;
	private String provState;
	private String city;
	private String postalZip;
	private String street;
	private String phoneNumber;
	private String longitude;
	private String latitude;
	private String timeZone;
	private List<String> openTime;
	private List<String> closeTime;
	private String minDelivery;
	private String deliveryFee;
	private List<AcceptedPayments> acceptedPayments;
	private RestaurantStatus status;

	public String getChainId() {
		return chainId;
	}

	public void setChainId(final String chainId) {
		this.chainId = chainId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public String getProvState() {
		return provState;
	}

	public void setProvState(final String provState) {
		this.provState = provState;
	}

	public String getCity() {
		return city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	public String getPostalZip() {
		return postalZip;
	}

	public void setPostalZip(final String postalZip) {
		this.postalZip = postalZip;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(final String street) {
		this.street = street;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(final String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(final String latitude) {
		this.latitude = latitude;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(final String timeZone) {
		this.timeZone = timeZone;
	}

	public List<String> getOpenTime() {
		return openTime;
	}

	public void setOpenTime(final List<String> openTime) {
		this.openTime = openTime;
	}

	public List<String> getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(final List<String> closeTime) {
		this.closeTime = closeTime;
	}

	public String getMinDelivery() {
		return minDelivery;
	}

	public void setMinDelivery(final String minDelivery) {
		this.minDelivery = minDelivery;
	}

	public String getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(final String deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public List<AcceptedPayments> getAcceptedPayments() {
		return acceptedPayments;
	}

	public void setAcceptedPayments(final List<AcceptedPayments> acceptedPayments) {
		this.acceptedPayments = acceptedPayments;
	}

	public RestaurantStatus getStatus() {
		return status;
	}

	public void setStatus(final RestaurantStatus status) {
		this.status = status;
	}
}

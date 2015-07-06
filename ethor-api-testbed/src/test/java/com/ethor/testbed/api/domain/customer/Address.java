package com.ethor.testbed.api.domain.customer;

public class Address {

	private String type;
	private String suiteNumber;
	private String streetNumber;
	private String street;
	private String city;
	private String postalZip;
	private String provState;
	private String country;

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getSuiteNumber() {
		return suiteNumber;
	}

	public void setSuiteNumber(final String suiteNumber) {
		this.suiteNumber = suiteNumber;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(final String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(final String street) {
		this.street = street;
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

	public String getProvState() {
		return provState;
	}

	public void setProvState(final String provState) {
		this.provState = provState;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}
}

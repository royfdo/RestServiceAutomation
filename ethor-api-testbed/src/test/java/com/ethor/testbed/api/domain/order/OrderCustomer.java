package com.ethor.testbed.api.domain.order;

import java.util.Calendar;

import com.ethor.testbed.api.domain.Entity;

/**
 * This class encapsulates customer attributes and methods.
 * 
 * @author Roy Fernando.
 */

public class OrderCustomer extends Entity {

	private String email;
	private String firstName;
	private String lastName;
	private String gender;
	private String birthDate;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}


}

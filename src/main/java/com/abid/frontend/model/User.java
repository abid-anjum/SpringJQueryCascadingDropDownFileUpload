package com.abid.frontend.model;

import java.util.Date;

public class User {
	private String username;
	private String password;
	private String email;
	private boolean isPostback = false;

	public boolean isPostback() {
		return isPostback;
	}

	public void setPostback(boolean isPostback) {
		this.isPostback = isPostback;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	private String birthDate;

	String country;
	String javaSkills;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getJavaSkills() {
		return javaSkills;
	}

	public void setJavaSkills(String javaSkills) {
		this.javaSkills = javaSkills;
	}

	private String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
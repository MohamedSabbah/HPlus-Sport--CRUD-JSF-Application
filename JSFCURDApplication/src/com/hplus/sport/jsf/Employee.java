package com.hplus.sport.jsf;

import javax.servlet.http.Part;

public class Employee {
	private int ID;
	private String firstName;
	private String lastName;
	private String degree;
	private String title;
	private String photo;
	private String description;

	private String fullName;
	private Part uploadedFile;

	public Employee() {

	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		this.ID = iD;
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

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFullName() {
		String fName = firstName + " " + lastName;
		if (!degree.isEmpty()) {
			fName += ", " + degree;
		}
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

}

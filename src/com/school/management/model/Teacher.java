package com.school.management.model;

import javax.persistence.Entity;

import com.school.management.model.abstr.User;

@Entity
public class Teacher extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Teacher() {
	}
	
	public Teacher(User user) {
		this();
		this.setId(user.getId());
		this.setUsername(user.getUsername());
		this.setRawPassword(user.getRawPassword());
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
	}
	
}

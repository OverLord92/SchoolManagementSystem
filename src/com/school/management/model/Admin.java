package com.school.management.model;

import javax.persistence.Entity;

import com.school.management.model.abstr.User;

@Entity
public class Admin extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Admin() {
	}
	
	public Admin(User user) {
		this.setId(user.getId());
		this.setUsername(user.getUsername());
		this.setRawPassword(user.getRawPassword());
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
	}
}

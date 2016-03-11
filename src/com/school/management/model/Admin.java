package com.school.management.model;

import javax.persistence.Entity;

import com.school.management.model.abstr.User;

/** Represents the administrator of the System */
@Entity
public class Admin extends User {
	
	private static final long serialVersionUID = 1L;

	public Admin() {
	}
	
	/** 
	 * Constructor which wraps user object into a Admin object.
	 */
	public Admin(User user) {
		this.setId(user.getId());
		this.setUsername(user.getUsername());
		this.setRawPassword(user.getRawPassword());
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
	}
}

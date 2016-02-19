package com.school.management.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.school.management.model.abstr.User;

@Entity
public class Teacher extends User {

	@OneToMany(mappedBy="teacher", cascade=CascadeType.ALL)
	private Set<Course> courses;
	
	public Teacher() {
		courses = new HashSet<>();
	}
	
	public Teacher(User user) {
		this();
		this.setId(user.getId());
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
	}
	
	
	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public void addCourse(Course course) {
		// TODO Auto-generated method stub
		
	}

}

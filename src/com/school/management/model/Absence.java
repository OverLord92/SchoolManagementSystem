package com.school.management.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.school.management.model.abstr.BaseEntity;

@Entity
public class Absence extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	private Course course;

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}

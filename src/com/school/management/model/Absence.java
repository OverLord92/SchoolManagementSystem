package com.school.management.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable 
public class Absence {

	@ManyToOne
	private Course course;
	
	private boolean justified;
	private String comment;
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public boolean isJustified() {
		return justified;
	}

	public void setJustified(boolean justified) {
		this.justified = justified;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}

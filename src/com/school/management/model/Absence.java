package com.school.management.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/** Represents a students absence from a class. 
 *  Embedded in the Student class */
@Embeddable 
public class Absence {

	@ManyToOne
	private Course course;
	
	private boolean justified;
	
	// the teacher can define a comment for the absence
	private String comment;
	
	
	// plain getters and setters
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

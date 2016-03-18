package com.school.management.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Range;

/** Represents a students grade for a class. 
 *  Embedded in the Student class */
@Embeddable  
public class Grade implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Course course;
	
	@Range(min = 1, max = 5)
	private int gradeValue;
	
	
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public int getGradeValue() {
		return gradeValue;
	}
	public void setGradeValue(int gradeValue) {
		this.gradeValue = gradeValue;
	}
	
	
	
}

package com.school.management.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable  
public class Grade  {

	@ManyToOne
	private Course course;
	
	private int grade;
	
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	
	
}

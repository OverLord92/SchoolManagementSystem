package com.school.management.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.school.management.model.abstr.BaseEntity;

@Entity
public class Course extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	// the code of the course is an abbreviation of the course name
	private String code;
	
	@ManyToOne    
	private Teacher teacher;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}

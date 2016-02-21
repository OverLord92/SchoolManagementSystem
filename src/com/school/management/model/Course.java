package com.school.management.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.school.management.model.abstr.BaseEntity;

@Entity
public class Course extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String code;
	
	@OneToOne
	private Teacher teacher;
	
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="attendingCourses")
	private Set<Student> students;
	
	
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
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	@Override
	public String toString() {
		return "Course [name=" + name + ", code=" + code + ", teacher=" + teacher + ", students=" + students + "]";
	}
	
	
	
}

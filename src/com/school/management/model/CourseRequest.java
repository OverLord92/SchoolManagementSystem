package com.school.management.model;

import javax.persistence.Entity;

import com.school.management.model.abstr.BaseEntity;

@Entity
public class CourseRequest extends BaseEntity {
	
	private Long studentId;
	private Long courseId;
	
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long userId) {
		this.studentId = userId;
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	
	
}

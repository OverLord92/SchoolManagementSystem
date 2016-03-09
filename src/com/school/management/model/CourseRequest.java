package com.school.management.model;

import javax.persistence.Entity;

import com.school.management.model.abstr.BaseEntity;

/** Student is able to request to attend a course.
 *  This class represents the course requests of students. */
@Entity
public class CourseRequest extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	// id and username of the student who made the request
	private Long studentId;
	private String studentUsername;
	
	// id and name of the requested course
	private Long courseId;
	private String courseName;
	
	
	
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public String getStudentUsername() {
		return studentUsername;
	}
	public void setStudentUsername(String studentUsername) {
		this.studentUsername = studentUsername;
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	
	
}

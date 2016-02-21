package com.school.management.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.school.management.model.abstr.User;

@Entity
public class Student extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(cascade=CascadeType.ALL)
	private Set<CourseRequest> courseRequests;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name="student_course",
			joinColumns=@JoinColumn(name="studentId"),
			inverseJoinColumns=@JoinColumn(name="courseId")      
	)
	private Set<Course> attendingCourses;
	
	@Transient
	private Set<Grade> grades;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Absence> absences;
	
	
	public Student() {
		grades = new HashSet<>();
		absences = new HashSet<>();
	}
	
	public Student(User user) {
		this();
		this.setId(user.getId());
		this.setUsername(user.getUsername());
		this.setRawPassword(user.getRawPassword());
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
	}

	
	public Set<CourseRequest> getCourseRequests() {
		return courseRequests;
	}

	public void setCourseRequests(Set<CourseRequest> courseRequests) {
		this.courseRequests = courseRequests;
	}

	public Set<Course> getAttendingCourses() {
		return attendingCourses;
	}

	public void setAttendingCourses(Set<Course> attendingCourses) {
		this.attendingCourses = attendingCourses;
	}

	public Set<Grade> getGrades() {
		return grades;
	}

	public void setGrades(Set<Grade> grades) {
		this.grades = grades;
	}

	public Set<Absence> getAbsences() {
		return absences;
	}

	public void setAbsences(Set<Absence> absences) {
		this.absences = absences;
	}

}

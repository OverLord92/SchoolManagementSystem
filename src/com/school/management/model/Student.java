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

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<CourseRequest> courseRequests;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(
			joinColumns=@JoinColumn(name="courseId"),
			inverseJoinColumns=@JoinColumn(name="")      
	)
	private Set<Course> attendedCourses;
	
	@Transient
	private Set<Grade> grades;
	@Transient
	private Set<Absence> absences;
	
	
	public Student() {
		grades = new HashSet<>();
		absences = new HashSet<>();
	}
	
	public Student(User user) {
		this();
		this.setId(user.getId());
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
	}

	
	public Set<CourseRequest> getWantedCourses() {
		return courseRequests;
	}

	public void setWantedCourses(Set<CourseRequest> wantedCourses) {
		this.courseRequests = wantedCourses;
	}

	public Set<Course> getAttendourse() {
		return attendedCourses;
	}

	public void setAttendourse(Set<Course> attendourse) {
		this.attendedCourses = attendourse;
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

	public void addCourse(Course course) {
		this.attendedCourses.add(course);
	}

	public void deleteRequestFromUser(CourseRequest courseRequest) {
		attendedCourses.remove(courseRequest);
	}
	
	public void addAbsence(Absence absence) {
		// TODO Auto-generated method stub
		
	}

	public void addCourseRequest(CourseRequest courseRequest) {
		this.getWantedCourses().add(courseRequest);
	}

}

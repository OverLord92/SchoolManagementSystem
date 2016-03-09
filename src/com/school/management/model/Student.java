package com.school.management.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.school.management.model.abstr.User;

@Entity
public class Student extends User {

	private static final long serialVersionUID = 1L;

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	private Set<CourseRequest> courseRequests;
	
	@ElementCollection(fetch=FetchType.LAZY) 
	private Set<Grade> grades;
	
	@ElementCollection(fetch=FetchType.LAZY)
	private Set<Absence> absences;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name="student_course",
			joinColumns=@JoinColumn(name="studentId"),
			inverseJoinColumns=@JoinColumn(name="courseId")      
	)
	private Set<Course> attendingCourses;
	
	public Student() {
	}
	
	/** 
	 * Constructor which wraps user object into a Student object.
	 *  Is currently not used, but could be useful in future development.
	 */
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

	public Set<Course> getAttendingCourses() {
		return attendingCourses;
	}

	public Set<Grade> getGrades() {
		return grades;
	}

	public Set<Absence> getAbsences() {
		return absences;
	}

}

package com.school.management.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.school.management.model.Absence;
import com.school.management.model.Admin;
import com.school.management.model.Course;
import com.school.management.model.Grade;
import com.school.management.model.Student;
import com.school.management.model.Teacher;

@Service
public interface UserService {

	// admin methods
	boolean saveAdmin(Admin admin);
	boolean approveCourseRequest(Long courseRequstId);
	boolean isUsernameAvaiable(String username);
	
	// teacher methods
	boolean saveTeacher(Teacher teeacher);
	Teacher getTeacherByUsername(String username);
	Set<Course> getTeachersCourses(Teacher teacher);
	
	// student methods
	Student getStudent(long id);
	Student getStudentByUsername(String username);
	Student getStudentFullyInitializedByUsername(String username);
	boolean saveStudent(Student student);
	boolean requestCourse(Student student, Long courseId);
	boolean addAbsence(Long studentId, Absence absence);
	boolean addGrade(Long studentId, Grade grade);
	
	
	
	
}

package com.school.management.services;

import org.springframework.stereotype.Service;

import com.school.management.model.Absence;
import com.school.management.model.Admin;
import com.school.management.model.CourseRequest;
import com.school.management.model.Grade;
import com.school.management.model.Student;
import com.school.management.model.Teacher;

@Service
public interface UserService {

	boolean saveAdmin(Admin admin);
	
	boolean saveStudent(Student student);
	boolean updateStudent(Student student);
	boolean requestCourse(Long studentId, Long courseId);
	boolean saveCourseRequest(CourseRequest courseRequest);
	Student getStudent(long id);
	Student getStudentByUsername(String username);
	Student getStudentByIdWithCollections(
			Long userId, boolean requests, boolean courses, boolean grades, boolean absences);
	Student getStudentByUsernameWithCollections(
			String username, boolean requests, boolean courses, boolean grades, boolean absences);
	boolean addCourseToStudent(Long courseRequstId);
	
	
	boolean saveTeacher(Teacher teeacher);
	Teacher getTeacherByUsername(String teacherUsername);
	Teacher getTeacherByUsernameWithCourses(String teacherUsername);
	
	boolean addAbsence(Long studentId, Absence absence);
	boolean addGrade(Long studentId, Grade grade);
	
}

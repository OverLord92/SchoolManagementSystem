package com.school.management.services;

import org.springframework.stereotype.Service;

import com.school.management.model.Absence;
import com.school.management.model.Admin;
import com.school.management.model.Course;
import com.school.management.model.CourseRequest;
import com.school.management.model.Grade;
import com.school.management.model.Student;
import com.school.management.model.Teacher;

@Service
public interface UserService {

	boolean saveStudent(Student student);
	boolean updateStudent(Student student);
	boolean mergeStudent(Student student);
	boolean saveTeacher(Teacher teeacher);
	boolean saveAdmin(Admin admin);
	boolean requestCourse(Long studentId, Long courseId);
	boolean addAbsence(Long studentId, Absence absence);
	boolean addGrade(Long studentId, Grade grade);
	boolean saveCourseRequest(CourseRequest courseRequest);
	Student getStudent(long id);
	Student getStudentByUsername(String username);
	Student getStudentByIdWithCollections(
			Long userId, boolean requests, boolean courses, boolean grades, boolean absences);
	Student getStudentByUsernameWithCollections(
			String username, boolean requests, boolean courses, boolean grades, boolean absences);
	boolean addCourseToStudent(Long courseRequstId);
	Teacher getTeacherByUsername(String teacherUsername);
	
	
}

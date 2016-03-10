package com.school.management.services.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.school.management.dao.interfaces.AdminDao;
import com.school.management.dao.interfaces.CourseDao;
import com.school.management.dao.interfaces.StudentDao;
import com.school.management.dao.interfaces.TeacherDao;
import com.school.management.model.Absence;
import com.school.management.model.Admin;
import com.school.management.model.Course;
import com.school.management.model.CourseRequest;
import com.school.management.model.Grade;
import com.school.management.model.Student;
import com.school.management.model.Teacher;
import com.school.management.services.UserService;

@Component
public class UserServiceImpl implements UserService{

	@Autowired
	StudentDao studentDao;
	
	@Autowired
	TeacherDao teacherDao;
	
	@Autowired
	AdminDao adminDao;
	
	@Autowired
	CourseDao courseDao;
	

	@Override
	public boolean saveAdmin(Admin admin) {
		adminDao.save(admin);
		return true;
	}
	
	@Override
	public boolean approveCourseRequest(Long courseRequestId) {
		studentDao.approveCourseRequest(courseRequestId);
		return true;
	}
	
	@Override
	public boolean isUsernameAvaiable(String username) {
		return adminDao.isUsernameAvaiable(username);
	}
	
	
	@Override
	public boolean saveTeacher(Teacher teacher) {
		teacherDao.save(teacher);
		return true;
	}
	
	@Override
	public Teacher getTeacherByUsername(String username) {
		return teacherDao.getTeacherByUsername(username);
	}
	
	@Override
	public Set<Course> getTeachersCourses(Teacher teacher) {
		return courseDao.getTeachersCourses(teacher);
	}
	
	
	@Override
	public Student getStudent(long id) {
		return studentDao.get(id);
	}

	@Override
	public Student getStudentByUsername(String username) {
		return studentDao.getStudentByUsername(username);
	}
	
	@Override
	public Student getStudentFullyInitializedByUsername(String username) {
		return studentDao.getStudentFullyInitializedByUsername(username);
	}
	
	@Override
	public boolean saveStudent(Student student) {
		studentDao.save(student);
		return true;
	}
	
	@Override
	public boolean requestCourse(Student student, Long courseId) {
		
		Course requestedCourse = courseDao.get(courseId);
		
		CourseRequest courseRequest = new CourseRequest();
		courseRequest.setStudentId(student.getId());
		courseRequest.setStudentUsername(student.getUsername());
		courseRequest.setCourseId(requestedCourse.getId());
		courseRequest.setCourseName(requestedCourse.getName());
		
		studentDao.addCourseRequestToStudent(student, courseRequest);
		
		return true;
	}
	
	@Override
	public boolean addAbsence(Long studentId, Absence absence) {
		studentDao.addAbsenceToStudent(studentId, absence);
		return true;
	}

	@Override
	public boolean addGrade(Long studentId, Grade grade) {
		studentDao.addGradeToStudent(studentId, grade);
		return true;
	}








	
	
	
	
	
	
	
	
	
	
	
	
	
}

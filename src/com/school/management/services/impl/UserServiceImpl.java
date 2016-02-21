package com.school.management.services.impl;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.school.management.dao.interfaces.AdminDao;
import com.school.management.dao.interfaces.CourseDao;
import com.school.management.dao.interfaces.CourseRequestDao;
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
	
	@Autowired
	CourseRequestDao courseRequestDao;
	
	@Override
	public boolean saveStudent(Student student) {
		studentDao.save(student);
		return true;
	}

	@Override
	public boolean saveTeacher(Teacher teacher) {
		teacherDao.save(teacher);
		return true;
	}

	@Override
	public boolean saveAdmin(Admin admin) {
		adminDao.save(admin);
		return true;
	}

	@Override
	public boolean requestCourse(Long studentId, Long courseId) {
		Student student = studentDao.get(studentId);
		Course course = courseDao.get(courseId);
		student.getAttendingCourses().add(course);                       ////////???
		return true;
	}

	@Override
	public boolean addAbsence(Long studentId, Absence absence) {
		studentDao.addAbsenceToStudent(studentId, absence);
		return true;
	}

	@Override
	public boolean addGrade(Long studentId, Grade grade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateStudent(Student student) {
		studentDao.update(student);
		return true;
	}
	
	@Override
	public boolean saveCourseRequest(CourseRequest courseRequest) {
		courseRequestDao.save(courseRequest);
		return true;
	}

	@Override
	public Student getStudent(long id) {
		return studentDao.get(id);
	}

	@Override
	public Student getStudentByUsernameWithCollections(String username, 
			boolean requests, boolean courses, boolean grades, boolean absences) {
		
		return studentDao.getStudentByUsernameWithCollections(username, requests, courses, grades, absences);
	}

	@Override
	public Student getStudentByIdWithCollections(Long userId, boolean requests, boolean courses, boolean grades,
			boolean absences) {
		return studentDao.getStudentByIdWithCollections(userId, requests, courses, grades, absences);
	}

	@Override
	public boolean addCourseToStudent(Long courseRequestId) {
		studentDao.addCourseToStudent(courseRequestId);
		return true;
	}

	@Override
	public Student getStudentByUsername(String username) {
		return studentDao.getStudentByUsername(username);
	}

	@Override
	public Teacher getTeacherByUsername(String teacherUsername) {
		return teacherDao.getTeacherByUsernameWithCourses(teacherUsername);
	}

	@Override
	public Teacher getTeacherByUsernameWithCourses(String teacherUsername) {
		Teacher teacher = getTeacherByUsername(teacherUsername);
		Hibernate.initialize(teacher.getCourses());
		return teacher;
	}


}

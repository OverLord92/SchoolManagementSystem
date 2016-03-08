package com.school.management.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.school.management.dao.interfaces.CourseDao;
import com.school.management.dao.interfaces.StudentDao;
import com.school.management.dao.interfaces.TeacherDao;
import com.school.management.model.Course;
import com.school.management.model.CourseRequest;
import com.school.management.model.Student;
import com.school.management.model.Teacher;
import com.school.management.services.CourseService;

@Component
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseDao courseDao;
	
	@Autowired
	StudentDao studentDao; 
	
	@Autowired
	TeacherDao teacherDao;
	
	@Override
	public boolean addNewCourse(Course course) {
		courseDao.save(course);
		return true;
	}

	@Override
	public List<Course> getAllCourses() {
		return courseDao.getAll();
	}
	
	@Override
	public List<Course> getAllFreeCourses() {
		return courseDao.getFreeCourses();
	}
	
	@Override
	public boolean assingCourseToTeacher(Long courseId, Long teacherId) {
		Course course = courseDao.get(courseId);
		Teacher teacher = teacherDao.get(teacherId);
		
		course.setTeacher(teacher);
		courseDao.update(course);
		return true;
	}


	@Override
	public List<Course> getAllNeitherRequestedNorAttendedCourses(Student student) {
		List<Course> result = courseDao.getAllNeitherRequestedNorAttendedCourses(student.getId());
		return result;
	}

	@Override
	public Course getCourse(Long courseId) {
		return courseDao.get(courseId);
	}


	@Override
	public List<Teacher> getAllTeachers() {
		return teacherDao.getAll();
	}


	@Override
	public void updateTeacher(Teacher teacher) {
		teacherDao.update(teacher);
	}

	@Override
	public List<CourseRequest> allPendingRequests() {
		return courseDao.getAllPendingRequests();
	}

	@Override
	public List<Student> getAllStudentsOfCourse(Long courseId) {
		return courseDao.getAllStudentsOfCourse(courseId);
	}

}

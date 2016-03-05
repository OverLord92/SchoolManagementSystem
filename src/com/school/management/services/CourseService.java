package com.school.management.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.school.management.model.Course;
import com.school.management.model.CourseRequest;
import com.school.management.model.Student;
import com.school.management.model.Teacher;

@Service
public interface CourseService {

	boolean assingCourseToTeacher(Long courseId, Long teacherId);
	List<CourseRequest> allPendingRequests();
	
	
	boolean addNewCourse(Course course);
	List<Course> getAllCourses();
	List<Course> getAllFreeCourses();
	List<Student> getAllStudentsOfCourse(Long courseId);
	
	
	
	List<Course> getAllNeitherRequestedNorAttendedCourses(Student student);
	
	Course getCourse(Long courseId);
	
	
	List<Teacher> getAllTeachers();
	
	
	void updateTeacher(Teacher teacher);
	

	
}

package com.school.management.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.school.management.model.Course;
import com.school.management.model.CourseRequest;
import com.school.management.model.Student;
import com.school.management.model.Teacher;

@Service
public interface CourseService {

	boolean addNewCourse(Course course);
	Course getCourse(Long courseId);
	List<Course> getAllCourses();
	List<Course> getAllUnassignedCourses();
	List<Course> getAllNeitherRequestedNorAttendedCourses(Student student);
	
	List<Student> getAllStudentsOfCourse(Long courseId);
	List<CourseRequest> allPendingRequests();
	
	List<Teacher> getAllTeachers();
	boolean assingCourseToTeacher(Long courseId, Long teacherId);
	
	
	
	
	
	

}

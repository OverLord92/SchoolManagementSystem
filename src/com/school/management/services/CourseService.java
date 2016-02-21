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
	List<Course> getAllCourses();
	List<Course> getAllFreeCourses();
	
	List<CourseRequest> getAllCourseRequests();
	
	List<Course> getAllNeitherRequiredNorAttendedCourses(Student student);
	
	CourseRequest getCourseRequest(Long requestId);
	
	Course getCourse(Long courseId);
	
	void deleteCourseRequest(CourseRequest request);
	
	
	
	boolean assingCourseToTeacher(Long courseId, Long teacherId);
	
	List<Teacher> getAllTeachers();
	
	Teacher getTeacherWithCourses(Long teacherId);
	
	void updateTeacher(Teacher teacher);
	
	void addCourseToTeacher(Long teacherId, Long courseId);
	
}

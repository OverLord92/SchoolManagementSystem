package com.school.management.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.school.management.model.Course;
import com.school.management.model.CourseRequest;
import com.school.management.model.Student;
import com.school.management.model.Teacher;

@Service
public interface CourseService {

//	boolean approveCourseRequest(Long studentId, Long courseId);
	boolean addNewCourse(Course course);
	boolean assingCourseToTeacher(Long courseId, Long teacherId);
	List<Course> getAllCourses();
	List<CourseRequest> getAllCourseRequests();
	List<Course> getAllNeitherRequiredNorAttendedCourses(Student student);
	CourseRequest getCourseRequest(Long requestId);
	Course getCourse(Long courseId);
	void deleteCourseRequest(CourseRequest request);
	List<Teacher> getAllTeachers();
	List<Course> getAllFreeCourses();
	
	Teacher getTeacherWithCourses(Long teacherId);
	void updateTeacher(Teacher teacher);
	
	void addCourseToTeacher(Long teacherId, Long courseId);
	
}

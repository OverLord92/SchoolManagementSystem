package com.school.management.dao.interfaces;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.school.management.dao.generic.GenericDao;
import com.school.management.model.Course;
import com.school.management.model.CourseRequest;
import com.school.management.model.Student;
import com.school.management.model.Teacher;

@Repository
public interface CourseDao extends GenericDao<Long, Course>{

	List<Course> getFreeCourses();

	List<CourseRequest> getAllPendingRequests();

	Set<Course> getTeachersCourses(Teacher teacher);

	List<Student> getAllStudentsOfCourse(Long courseId);
}

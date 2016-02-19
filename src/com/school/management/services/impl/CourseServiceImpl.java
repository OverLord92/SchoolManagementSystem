package com.school.management.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.school.management.dao.interfaces.CourseDao;
import com.school.management.dao.interfaces.CourseRequestDao;
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
	StudentDao studentDao;   /// premjestiti ovo u genericdao impl
	
	@Autowired
	TeacherDao teacherDao;
	
	@Autowired
	CourseRequestDao courseRequestDao;
	
//	@Override
//	public boolean approveCourseRequest(Long studentId, Long courseId) {
//		Student student = studentDao.get(studentId);
//		Course course = courseDao.get(courseId);
//		student.moveCourseFronRequestedToApproved(course);
//		studentDao.update(student);
//		return true;
//	}

	@Override
	public boolean addNewCourse(Course course) {
		courseDao.save(course);
		return true;
	}

	@Override
	public boolean assingCourseToTeacher(Long courseId, Long teacherId) {
		Course course = courseDao.get(courseId);
		Teacher teacher = teacherDao.get(teacherId);
		teacher.addCourse(course);
		return false;
	}

	@Override
	public List<Course> getAllCourses() {
		return courseDao.getAll();
	}

	@Override
	public List<Course> getAllNeitherRequiredNorAttendedCourses(Student student) {
		List<Course> firstResult = new ArrayList<>();
		
		List<Course> allCourses = courseDao.getAll();
		Set<Course> attendedCourses = student.getAttendourse();       //// popravi ourse na course
		
		// filter already attending courses from all courses
		boolean addCourse = true;
		for(int i = 0; i < allCourses.size(); i++) {
			System.out.println(allCourses.get(i));
			addCourse = true;
			Course course = null;
			for(Course attendedcourse: attendedCourses) {
				course = allCourses.get(i);
				if(attendedcourse.getId() == course.getId()){
					addCourse = false;
				}
			}
			
			if(addCourse && course != null) {
				firstResult.add(course);
			}	
		}
		
		if(attendedCourses.size() == 0) {
			firstResult.addAll(allCourses);
		}
		
		ArrayList<Course> endResult = new ArrayList<>();
		// filter already requested courses from remained courses
		Set<CourseRequest> courseRequests = student.getWantedCourses();
		
		// put all courseids of the courseRequest in a list
		ArrayList<Long> courseRequestCourseIds = new ArrayList<>();
		for(CourseRequest request: courseRequests) {
			courseRequestCourseIds.add(request.getCourseId());
		}
		
		for(Course course: firstResult) {
			if(!courseRequestCourseIds.contains(course.getId())) {
				endResult.add(course);
			}
		}
		
		return endResult;
	}

	@Override
	public List<CourseRequest> getAllCourseRequests() {
		return courseRequestDao.getAll();
	}

	@Override
	public CourseRequest getCourseRequest(Long requestId) {
		return courseRequestDao.get(requestId);
	}

	@Override
	public Course getCourse(Long courseId) {
		return courseDao.get(courseId);
	}

	@Override
	public void deleteCourseRequest(CourseRequest request) {
		courseRequestDao.delete(request);
	}

	@Override
	public List<Teacher> getAllTeachers() {
		return teacherDao.getAll();
	}

	@Override
	public List<Course> getAllFreeCourses() {
		return courseDao.getFreeCourses();
	}

	@Override
	public Teacher getTeacherWithCourses(Long teacherId) {
		return teacherDao.getTeacherWithCourses(teacherId);
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		teacherDao.update(teacher);
	}

	@Override
	public void addCourseToTeacher(Long teacherId, Long courseId) {
		teacherDao.addCourseToTeacher(teacherId, courseId);
	}

}

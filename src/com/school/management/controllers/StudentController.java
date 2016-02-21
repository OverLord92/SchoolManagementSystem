package com.school.management.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.school.management.model.Course;
import com.school.management.model.CourseRequest;
import com.school.management.model.Student;
import com.school.management.services.CourseService;
import com.school.management.services.UserService;

@Controller
public class StudentController {

	@Autowired
	UserService userService;
	
	@Autowired
	CourseService courseService;
	
	
	@RequestMapping("/studentAccount")
	public String showAccount(Model model, Principal principal) {
		
		String username = principal.getName();
		Student student = userService.getStudentByUsernameWithCollections(username, true, true, false, false);
		
		// get all requestable courses
		List<Course>allAvailableCourses = courseService.getAllNeitherRequiredNorAttendedCourses(student);
		model.addAttribute("allAvailableCourses", allAvailableCourses);
		
		model.addAttribute("attendingCourses", student.getAttendingCourses());
		model.addAttribute("pendingRequests", student.getCourseRequests());
	
		return "studentAccount";
	}
	
	@RequestMapping(value="/requestCourse/{courseId}", method=RequestMethod.POST,
			consumes="application/json")
	public @ResponseBody boolean requestCourse(@PathVariable Long courseId, Principal principal) {
		
		String username = principal.getName();
		Student student = userService.getStudentByUsernameWithCollections(username, true, false, false, false);
		
		Course course = courseService.getCourse(courseId);
		String courseName = course.getName();
		
		CourseRequest courseRequest = new CourseRequest();
		courseRequest.setStudentId(student.getId());
		courseRequest.setStudentUsername(username);
		courseRequest.setCourseId(courseId);
		courseRequest.setCourseName(courseName);
		
		student.getCourseRequests().add(courseRequest);
		
		userService.updateStudent(student);

		return true;
	}
}

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
	public String showStudentAccount(Model model, Principal principal) {
		
		String username = principal.getName();
		Student student = userService.getStudentFullyInitializedByUsername(username);
		
		// get all courses witch can be requested 
		// (excluding attending courses and already requested courses)
		List<Course>allAvailableCourses = courseService.getAllNeitherRequestedNorAttendedCourses(student);
		model.addAttribute("allAvailableCourses", allAvailableCourses);
		
		model.addAttribute("attendingCourses", student.getAttendingCourses());
		model.addAttribute("pendingRequests", student.getCourseRequests());
		
		model.addAttribute("loggedStudent", student);
		
		return "studentAccount";
	}
	
	@RequestMapping(value="/requestCourse/{courseId}", method=RequestMethod.POST,
			consumes="application/json")
	public @ResponseBody boolean requestCourse(@PathVariable Long courseId, Principal principal) {
		
		String username = principal.getName();
		Student student = userService.getStudentByUsername(username);
		
		userService.requestCourse(student, courseId);

		return true;
	}
}

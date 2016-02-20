package com.school.management.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.school.management.model.Course;
import com.school.management.model.CourseRequest;
import com.school.management.model.Student;
import com.school.management.model.Teacher;
import com.school.management.services.CourseService;
import com.school.management.services.UserService;

@Controller
public class AdminController {

	@Autowired
	UserService useService;
	
	@Autowired
	CourseService courseService;
	
	@RequestMapping("/adminPage")
	public String showAdminPage(Model model, @ModelAttribute Course course) {
		
		List<Course>allCourses = courseService.getAllCourses();
		model.addAttribute("allCourses", allCourses);
		
		List<Teacher>allTeachers = courseService.getAllTeachers();
		model.addAttribute("allTeachers", allTeachers);
		
		List<CourseRequest> allCourseRequests = courseService.getAllCourseRequests();
		model.addAttribute("allCourseRequests", allCourseRequests);
		
		return "admin";
	}
	
	@RequestMapping(value="/addCourse", method=RequestMethod.POST)
	public String addNewCourse(Course course) {
		
		courseService.addNewCourse(course);
		return "redirect:/adminPage";
	}
		
	@RequestMapping(value="getCoursesWithoutTeachers", method=RequestMethod.POST, 
			produces="application/json")
	public @ResponseBody Map<String, Object> getAllFreeCourses(@RequestBody Map<String, Object> requestData) {
		
		List<Course> allFreeCourses = courseService.getAllFreeCourses();  

		Map<String, Object> resultData  = new HashMap<>();
		resultData.put("allFreeCourses", allFreeCourses);
		
		return resultData;
	}
	
	@RequestMapping(value="assignCourseToTeacher", method=RequestMethod.POST, 
			consumes="application/json", produces="application/json")
	public @ResponseBody boolean assignCourseToTeacher(@RequestBody Map<String, Object> requestData) {
		Long teacherId = Long.parseLong((String)requestData.get("teacherId"));
		Long courseId = Long.parseLong((String)requestData.get("courseId"));
		
		courseService.addCourseToTeacher(teacherId, courseId);
		
		return true;
	}
	
	@RequestMapping(value="approveCourserequest", method=RequestMethod.POST, 
			consumes="application/json")
	public @ResponseBody boolean approveCourseRequest(@RequestBody Map<String, Object> requestData) {
		
		Long courseRequestId = Long.parseLong((String)requestData.get("courseRequestId"));
		CourseRequest courseRequest = courseService.getCourseRequest(courseRequestId);
	
		useService.addCourseToStudent(courseRequestId);
		
		courseService.deleteCourseRequest(courseRequest);
		
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
}


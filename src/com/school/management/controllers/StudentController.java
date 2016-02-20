package com.school.management.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.school.management.model.Teacher;
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
		
		model.addAttribute("attendingCourses", student.getAttendourse());
		model.addAttribute("pendingRequests", student.getWantedCourses());
	
		return "studentAccount";
	}
	
	@RequestMapping(value="/requestCourse/{courseId}", method=RequestMethod.POST,
			consumes="application/json")
	public @ResponseBody boolean requestCourse(@PathVariable Long courseId, Principal principal) {
		
		String username = principal.getName();
		Student student = userService.getStudentByUsername(username);
		
		CourseRequest courseRequest = new CourseRequest();
		courseRequest.setStudentId(student.getId());
		courseRequest.setCourseId(courseId);
		
		student.addCourseRequest(courseRequest);
		
		userService.mergeStudent(student);

		return true;
	}
	
	@RequestMapping("/teacherAccount")
	public String showteacherAccount(Model model, Principal principal) {
		
		String teacherUsername = principal.getName();
		
		Teacher teacher = userService.getTeacherByUsername(teacherUsername);
		Set<Course> courses = teacher.getCourses();
		
		model.addAttribute("teachersCourses", courses);
		return "teacherAccount";
	}
	
	@RequestMapping(value="/getStudentsCourse/{courseId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody Map<String, Object> getCourseStudents(@PathVariable Long courseId) {
		
		Map<String, Object> resultData = new HashMap<>();
		Course course = courseService.getCourseWithStudents(courseId);
		
		Set<Student> students = course.getStudents();
		
		Iterator<Student> iter = students.iterator();
		while(iter.hasNext()) {
			Student student = (Student)iter.next();
			student.setAttendourse(null);
		}
		
		resultData.put("studentsAtendingCourse", students);
		
		return resultData;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

package com.school.management.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.school.management.model.Absence;
import com.school.management.model.Course;
import com.school.management.model.Grade;
import com.school.management.model.Student;
import com.school.management.model.Teacher;
import com.school.management.services.CourseService;
import com.school.management.services.UserService;

@Controller
public class TeacherController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CourseService courseService;

	@RequestMapping("/teacherAccount")
	public String showteacherAccount(Model model, Principal principal) {
		
		String teacherUsername = principal.getName();
		Teacher teacher = userService.getTeacherByUsername(teacherUsername);
		
		Set<Course> courses = userService.getTeachersCourses(teacher);
		model.addAttribute("teachersCourses", courses);
		return "teacherAccount";
	}
	
	@RequestMapping(value="/getStudentsCourse/{courseId}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody Map<String, Object> getCourseStudents(@PathVariable Long courseId) {
		
		Map<String, Object> resultData = new HashMap<>();
		
		List<Student> students = courseService.getAllStudentsOfCourse(courseId);
		resultData.put("studentsAtendingCourse", students);
		
		return resultData;
	}
	
	@RequestMapping(value="/addAbsenceToUser", method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody boolean addAbsenceToUser(@RequestBody Map<String, Object> requestData) {
		
		Long studentId = Long.parseLong((String)requestData.get("studentId"));
		Long courseId = Long.parseLong((String)requestData.get("courseId"));
		
		String comment = (String)requestData.get("comment");
		Boolean justified = (Boolean)requestData.get("justified");
		
		Course course = courseService.getCourse(courseId);
		Absence absence = new Absence();
		absence.setCourse(course);
		absence.setJustified(justified);
		absence.setComment(comment);
		
		userService.addAbsence(studentId, absence);
		
		return true;
	}
	
	@RequestMapping(value="/addGradeToUser", method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody boolean addGradeToUser(@RequestBody Map<String, Object> requestData) {
		
		Long studentId = Long.parseLong((String)requestData.get("studentId"));
		Long courseId = Long.parseLong((String)requestData.get("courseId"));
		
		int gradeValue = Integer.parseInt((String)requestData.get("grade"));
		
		Course course = courseService.getCourse(courseId);
		
		Grade grade = new Grade();
		grade.setCourse(course);
		grade.setGrade(gradeValue);
		
		userService.addGrade(studentId, grade);
		return false;
	}
	
	
}

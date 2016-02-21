package com.school.management.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.Iterator;
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
		Teacher teacher = userService.getTeacherByUsernameWithCourses(teacherUsername);
		
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
			student.setAttendingCourses(null);
		}
		
		resultData.put("studentsAtendingCourse", students);
		
		return resultData;
	}
	
	@RequestMapping(value="/addAbsenceToUser", method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody boolean addAbsenceToUser(@RequestBody Map<String, Object> requestData) {//@RequestParam("studentId") String studentId, @RequestParam("courseId") String courseId) {
		
		Long studentId = Long.parseLong((String)requestData.get("studentId"));
		Long courseId = Long.parseLong((String)requestData.get("courseId"));
		
		Course course = courseService.getCourse(courseId);
		Absence absence = new Absence();
		absence.setCourse(course);
		
		userService.addAbsence(studentId, absence);
		
		return true;
	}
	
	
}

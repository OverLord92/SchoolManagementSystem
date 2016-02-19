package com.school.management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.school.management.model.Admin;
import com.school.management.model.Student;
import com.school.management.model.Teacher;
import com.school.management.model.abstr.User;
import com.school.management.services.UserService;

@Controller
public class LoginAndRegisterController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/")
	public String showHome() {
		return "home";
	}
	
	@RequestMapping("/register")
	public String showRegisterPage(@ModelAttribute User user) {
		return "registration";
	}
	
	@RequestMapping(value="/registerStudent", method=RequestMethod.POST)
	public String registerStudent(User user) {
		Student student = new Student(user);
		student.setEnabled(true);
		student.setAuthority("STUDENT");
		if(userService.saveStudent(student)) {
			System.out.println("student " + student);
			return "redirect:/register";
		} else { 
			return "registration";
		}
	}
	
	@RequestMapping(value="/registerTeacher", method=RequestMethod.POST)
	public String registerTeacher(User user) {
		Teacher teacher = new Teacher(user);
		teacher.setEnabled(true);
		if(userService.saveTeacher(teacher)) {
			System.out.println("teacher " + teacher);
			return "redirect:/register";
		} else {
			return "registration";
		}
	}
	
	@RequestMapping(value="/registerAdmin", method=RequestMethod.POST)
	public String registerAdmin(User user) {
		Admin admin = new Admin(user);
		
		if(userService.saveAdmin(admin)) {
			return "redirect:/register";
		} else {
			return "registration";
		}
	}
}













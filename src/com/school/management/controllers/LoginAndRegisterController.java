package com.school.management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.school.management.model.Student;
import com.school.management.model.Teacher;
import com.school.management.model.User;
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
	public String registerStudent(Student student) {
		
		if(userService.saveStudent(student)) {
			return "admin";
		} else { 
			return "registrationPage";
		}
	}
	
	@RequestMapping(value="/registerTeacher", method=RequestMethod.POST)
	public String registerTeacher(Teacher teacher) {
		if(userService.saveTeacher(teacher)) {
			return "admin";
		} else {
			return "registrationPage";
		}
	}
}

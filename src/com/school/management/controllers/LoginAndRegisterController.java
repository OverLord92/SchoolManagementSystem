package com.school.management.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.school.management.model.Admin;
import com.school.management.model.Student;
import com.school.management.model.Teacher;
import com.school.management.services.UserService;

@Controller
public class LoginAndRegisterController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/")
	public String showHome() {
		return "home";
	}
	
	@RequestMapping("/login")
	public String showLoginPage() {
		return "loginPage";
	}
	
	@RequestMapping("/register")
	public String showRegisterPage(
			@ModelAttribute Student student,
			@ModelAttribute Teacher teacher,
			@ModelAttribute Admin admin) {
		return "registration";
	}
	

	@RequestMapping(value="/registerStudent", method=RequestMethod.POST)
	public String registerStudent(@Valid Student student, Errors errors, Model model) {
		if(!errors.hasErrors()) {
			student.setEnabled(true);
			student.setAuthority("STUDENT");
			userService.saveStudent(student);
			return "redirect:/register";
		} else { 
			model.addAttribute("teacher", new Teacher());
			model.addAttribute("admin", new Admin());
			return "registration";
		}
	}
	
	@RequestMapping(value="/registerTeacher", method=RequestMethod.POST)
	public String registerTeacher(@Valid Teacher teacher, Errors errors, Model model) {
		if(!errors.hasErrors()) {
			teacher.setEnabled(true);
			teacher.setAuthority("TEACHER");
			userService.saveTeacher(teacher);
			return "redirect:/register";
		} else {
			model.addAttribute("student", new Student());
			model.addAttribute("admin", new Admin());
			return "registration";
		}
	}
	
	@RequestMapping(value="/registerAdmin", method=RequestMethod.POST)
	public String registerAdmin(@Valid Admin admin, Errors errors, Model model) {
		if(!errors.hasErrors()) {
			admin.setEnabled(true);
			admin.setAuthority("ADMIN");
			userService.saveAdmin(admin);
			return "redirect:/register";
		} else {
			model.addAttribute("student", new Student());
			model.addAttribute("teacher", new Teacher());
			return "registration";
		}
	}
}













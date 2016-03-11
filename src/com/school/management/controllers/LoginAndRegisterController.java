package com.school.management.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.school.management.model.Admin;
import com.school.management.model.Student;
import com.school.management.model.Teacher;
import com.school.management.services.UserService;

@Controller
@SessionAttributes({ "student", "teacher", "admin" }) 
public class LoginAndRegisterController {
	
	@Autowired
	UserService userService;
	
	@ModelAttribute("student")
	public Student prepareStudent() {
		return new Student();
	}
	@ModelAttribute("teacher")
	public Teacher prepareTeacher() {
		return new Teacher();
	}
	@ModelAttribute("admin")
	public Admin prepareAdmin() {
		return new Admin();
	}
	
	@RequestMapping({ "/", "/home"})
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
	
	@RequestMapping("checkUsernameAvailability")
	public @ResponseBody boolean isUsernameAvaiable(@RequestParam String username) {
		return userService.isUsernameAvaiable(username);
	}
	
	/** Handles student registration form */
	@RequestMapping(value="/registerStudent", method=RequestMethod.POST)
	public String registerStudent(@Valid @ModelAttribute Student student, 
			Errors errors, SessionStatus status) {
		
		if(!userService.isUsernameAvaiable(student.getUsername())){
			errors.reject("username", "Username is not available");
			return "/registration";
		}
		
		if(!errors.hasErrors()) {
			student.setEnabled(true);
			student.setAuthority("STUDENT");
			userService.saveStudent(student);
			
			status.setComplete();
			return "redirect:/register";
		} 
		
		return "/registration";
		
	}
	
	/** Handles teacher registration form */
	@RequestMapping(value="/registerTeacher", method=RequestMethod.POST)
	public String registerTeacher(@Valid Teacher teacher, 
			Errors errors, SessionStatus status) {
		
		if(!userService.isUsernameAvaiable(teacher.getUsername())){
			errors.reject("username", "DuplicateKey.user.username");
			return "/registration";
		}
		
		if(!errors.hasErrors()) {
			teacher.setEnabled(true);
			teacher.setAuthority("TEACHER");
			userService.saveTeacher(teacher);
			
			status.setComplete();
			return "redirect:/register";
		} 
		
		return "/registration";
		
	}
	
	/** Handles administrator registration form */
	@RequestMapping(value="/registerAdmin", method=RequestMethod.POST)
	public String registerAdmin(@Valid Admin admin, 
			Errors errors, SessionStatus status) {
		
		if(!userService.isUsernameAvaiable(admin.getUsername())){
			errors.reject("username", "DuplicateKey.user.username");
			return "/registration";
		}
		
		if(!errors.hasErrors()) {
			admin.setEnabled(true);
			admin.setAuthority("ADMIN");
			userService.saveAdmin(admin);
			
			status.setComplete();
			return "redirect:/register";
		} 
		
		return "/registration"; 
	}
}













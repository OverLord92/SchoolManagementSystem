package com.school.management.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

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
	
	@RequestMapping("checkUsernameAvailability")
	public @ResponseBody boolean isUsernameAvaiable(@RequestParam String username) {
		return userService.isUsernameAvaiable(username);
	}
	
	/** Handles student registration form */
	@RequestMapping(value="/registerStudent", method=RequestMethod.POST)
	public String registerStudent(@Valid @ModelAttribute Student student, Errors errors, Model model) {
		
		if(!userService.isUsernameAvaiable(student.getUsername())){
			errors.reject("username", "DuplicateKey.user.username");
			return "/registration";
		}
		
		if(!errors.hasErrors()) {
			student.setEnabled(true);
			student.setAuthority("STUDENT");
			userService.saveStudent(student);
			return "redirect:/register";
		} 
		
		// adds attributes to the model if the validation fails
		// the attributes are needed as modelAttributes in the spring forms
		model.addAttribute("teacher", new Teacher());
		model.addAttribute("admin", new Admin());
		return "/registration";
		
	}
	
	/** Handles teacher registration form */
	@RequestMapping(value="/registerTeacher", method=RequestMethod.POST)
	public String registerTeacher(@Valid Teacher teacher, Errors errors, Model model) {
		if(!errors.hasErrors()) {
			teacher.setEnabled(true);
			teacher.setAuthority("TEACHER");
			userService.saveTeacher(teacher);
			return "redirect:/register";
		} 
		
		// adds attributes to the model if the validation fails
		// the attributes are needed as modelAttributes in the spring forms
		model.addAttribute("student", new Student());
		model.addAttribute("admin", new Admin());
		return "/registration";
		
	}
	
	/** Handles administrator registration form */
	@RequestMapping(value="/registerAdmin", method=RequestMethod.POST)
	public String registerAdmin(@Valid Admin admin, Errors errors, Model model) {
		if(!errors.hasErrors()) {
			admin.setEnabled(true);
			admin.setAuthority("ADMIN");
			userService.saveAdmin(admin);
			return "redirect:/register";
		} 
		
		// adds attributes to the model if the validation fails
		// the attributes are needed as modelAttributes in the spring forms
		model.addAttribute("student", new Student());
		model.addAttribute("teacher", new Teacher());
		return "/registration"; 
	}
}













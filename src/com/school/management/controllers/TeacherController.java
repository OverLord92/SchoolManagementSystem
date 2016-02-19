package com.school.management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.school.management.model.Absence;
import com.school.management.model.Grade;
import com.school.management.services.UserService;

@Controller
public class TeacherController {
	
	@Autowired
	UserService userService;

	@RequestMapping(value="/addAbsence/{userId}/{courseId}", method=RequestMethod.POST)
	public String addAbsence(@PathVariable Long userId) {//, @PathVariable Long courseId) {
		userService.addAbsence(userId, new Absence());
		return "class";
	}
	
	@RequestMapping(value="/addGrade/{userId}/{courseId}", method=RequestMethod.POST)
	public String addGrade(@PathVariable Long userId) { //, @PathVariable Long courseId) {
		System.out.println("vracal");
		userService.addGrade(userId, new Grade());
		return "class";
	}
	
}

package com.school.management.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPagesController {

	@RequestMapping("/accessDenied")
	public String handleAccessDenied() {
		return "errorPages/accessDenied";
	}
	
	@RequestMapping("/pageNotFound")
	public String handlePageNotFound() {
		return "errorPages/pageNotFound";
	}
	
	@RequestMapping("/internalServerException")
	public String handleInternalServerError() {
		return "errorPages/internalServerException";
	}
	
}

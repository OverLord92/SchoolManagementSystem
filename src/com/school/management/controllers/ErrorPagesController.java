package com.school.management.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPagesController {
	public static final String ACCESS_DENIED_PAGE = "errorPages/accessDenied";
	public static final String PAGE_NOT_FOUND_PAGE = "errorPages/pageNotFound";
	public static final String INTERNAL_SERVER_EXCEPTION_PAGE = "errorPages/internalServerException";

	@RequestMapping("/accessDenied")
	public String handleAccessDenied() {
		return ACCESS_DENIED_PAGE;
	}
	
	@RequestMapping("/pageNotFound")
	public String handlePageNotFound() {
		return PAGE_NOT_FOUND_PAGE;
	}
	
	@RequestMapping("/internalServerException")
	public String handleInternalServerError() {
		return INTERNAL_SERVER_EXCEPTION_PAGE;
	}
	
}

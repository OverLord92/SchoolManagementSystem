package com.school.management.web.layer.spec;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.school.management.config.RootConfig;
import com.school.management.controllers.LoginAndRegisterController;
import com.school.management.services.UserService;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes={ RootConfig.class })
public class LoginSpec {
	
	
	@Mock
	UserService userService;
	
	@InjectMocks
	LoginAndRegisterController controller;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = standaloneSetup(controller).build();
	}
	
	@Test
	public void shouldShowHomePage() throws Exception {
		mockMvc.perform(get("/"))
			.andExpect(view().name("home"));
		mockMvc.perform(get("/login"))
		.andExpect(view().name("loginPage"));
	}
	
	@Test
	public void shouldShowRegistrationPage() throws Exception {
		mockMvc.perform(get("/register"))
			.andExpect(view().name("registration"))
			.andExpect(model().attribute("student", notNullValue()))
			.andExpect(model().attribute("teacher", notNullValue()))
			.andExpect(model().attribute("admin", notNullValue()));
	}
	
	@Test
	public void shouldRegisterStudent() throws Exception {
		doReturn(true).when(userService).saveStudent(any());
		mockMvc.perform(post("/registerStudent"))
			.andExpect(view().name("redirect:/register"));
	}
	
	@Test
	public void shouldRegisterTeacher() throws Exception {
		doReturn(true).when(userService).saveTeacher(any());
		mockMvc.perform(post("/registerTeacher"))
			.andExpect(view().name("redirect:/register"));
	}
	
	@Test
	public void shouldRegisterAdmin() throws Exception {
		doReturn(true).when(userService).saveAdmin(any());
		mockMvc.perform(post("/registerAdmin"))
			.andExpect(view().name("redirect:/register"));
		
	}

}

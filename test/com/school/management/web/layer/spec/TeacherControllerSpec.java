package com.school.management.web.layer.spec;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.school.management.config.RootConfig;
import com.school.management.controllers.TeacherController;
import com.school.management.services.UserService;

@ContextConfiguration(classes= { RootConfig.class })
@RunWith(MockitoJUnitRunner.class)
public class TeacherControllerSpec {

	public static final Long STUDENT_ID = 5L;
	public static final Long COURSE_ID = 10L;
	
	@Mock
	UserService userService;
	
	@InjectMocks
	TeacherController controller;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = standaloneSetup(controller).build();
	}

	//////////need to figure out how to properly test controllers which handle ajax calls ///////////////
	
	@Ignore
	@Test
	public void whenTeacherAddsCourseAddCourseToDatabase() throws Exception {
		doReturn(true).when(userService).addAbsence(any(), any());
		
		mockMvc.perform(post("/addAbsence/{userId}/{courseId}", STUDENT_ID, COURSE_ID))
			.andExpect(view().name("class"));
	}
	
	@Ignore
	@Test
	public void whenTeacherAddsGradeAddGradeToDatabase() throws Exception {
		doReturn(true).when(userService).addGrade(any(), any());
			
		mockMvc.perform(post("/addGrade/{userId}/{courseId}", STUDENT_ID, COURSE_ID))
			.andExpect(view().name("class"));
	}
	

























}

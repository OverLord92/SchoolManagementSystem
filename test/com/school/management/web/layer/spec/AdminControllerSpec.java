package com.school.management.web.layer.spec;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.school.management.controllers.AdminController;
import com.school.management.services.CourseService;

@ContextConfiguration(classes={ RootConfig.class })
@RunWith(MockitoJUnitRunner.class)
public class AdminControllerSpec {
	
	public static final Long STUDENT_ID = 5L;
	public static final Long TEACHER_ID = 7L;
	public static final Long COURSE_ID = 10L;
	
	@Mock
	CourseService courseService;
	
	@InjectMocks
	AdminController controller;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = standaloneSetup(controller).build();
	}

	@Test
	public void whenAdminAddsCourseCallControllerMethod() throws Exception {
		mockMvc.perform(post("/addCourse"))
			.andExpect(view().name("admin"));
	}
	
	@Test
	public void whenAdminApprovesCourseRequestCallControllerMethod() throws Exception {
		
		mockMvc.perform(post("/approveCourseRequest/{userId}/{courseId}", STUDENT_ID, COURSE_ID))
			.andExpect(view().name("admin"));
	}
	
	@Test
	public void whenAdminAssignsCourseToTeacherCallControllerMethod() throws Exception {
		mockMvc.perform(post("/assignCourseToTeacher/{teacherId}/{courseId}", TEACHER_ID, COURSE_ID))
			.andExpect(view().name("admin"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
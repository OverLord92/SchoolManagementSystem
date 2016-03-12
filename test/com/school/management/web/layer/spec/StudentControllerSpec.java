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
import com.school.management.controllers.StudentController;
import com.school.management.services.UserService;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration( classes = { RootConfig.class } )
public class StudentControllerSpec {
	
	public static final Long STUDENT_ID = 5L;
	public static final Long COURSE_ID = 10L;

	@Mock
	UserService userService;
	
	@InjectMocks
	StudentController controller;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = standaloneSetup(controller).build();
	}
	
	
	//////////need to figure out how to properly test controllers which handle ajax calls ///////////////
	
	@Ignore
	@Test
	public void whenUserRequestsAccountShowAccountPage() throws Exception {
		mockMvc.perform(post("/account/{userId}", STUDENT_ID))
			.andExpect(view().name("account"));
		
	}
	
	@Ignore
	@Test
	public void whenUserRequestsToAddACourseReturnTrue() throws Exception {
		doReturn(true).when(userService).requestCourse(any(), any());
		
		mockMvc.perform(post("/requestCourse/{studentId}/{CourseId}", STUDENT_ID, COURSE_ID))
			.andExpect(view().name("account"));
	}
		
}

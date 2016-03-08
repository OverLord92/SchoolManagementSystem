package com.school.management.web.layer.spec;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.school.management.config.RootConfig;
import com.school.management.controllers.AdminController;
import com.school.management.services.CourseService;
import com.school.management.services.UserService;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes={ RootConfig.class })
public class AdminControllerSpec {
	
	public static final Long STUDENT_ID = 5L;
	public static final Long TEACHER_ID = 7L;
	public static final Long COURSE_ID = 10L;
	
	@Mock
	UserService userService;
	@Mock
	CourseService courseService;
	
	@InjectMocks
	AdminController controller;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = standaloneSetup(controller).build();
		
//		doReturn(new ArrayList<String>()).when(courseService).getAllCourses();
	}
	
	@Test
	public void whenRequestedShowAdminPage() throws Exception {
		mockMvc.perform(post("/adminPage"))
		.andExpect(view().name("admin"))
		.andExpect(model().attribute("allCourses", notNullValue()))
		.andExpect(model().attribute("allTeachers", notNullValue()))
		.andExpect(model().attribute("allCourseRequests", notNullValue()));
	}

	@Test
	public void whenAdminAddsCourseCallControllerMethod() throws Exception {
		mockMvc.perform(post("/addCourse"))
			.andExpect(view().name("redirect:/adminPage"));
	}
	
	@Test
	public void whenAdminApprovesCourseRequestCallControllerMethod() throws Exception {
		mockMvc.perform(post("approveCourserequest"))
			.andExpect(content().contentType(MediaType.ALL));
	}
	
	@Test
	public void whenAdminAssignsCourseToTeacherCallControllerMethod() throws Exception {
		mockMvc.perform(post("/assignCourseToTeacher/{teacherId}/{courseId}", TEACHER_ID, COURSE_ID))
		.andExpect(content().contentType(MediaType.ALL));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
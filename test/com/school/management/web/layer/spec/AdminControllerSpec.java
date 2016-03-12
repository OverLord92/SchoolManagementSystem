package com.school.management.web.layer.spec;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Ignore;
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
import com.school.management.config.WebConfig;
import com.school.management.controllers.AdminController;
import com.school.management.model.Course;
import com.school.management.model.CourseRequest;
import com.school.management.model.Teacher;
import com.school.management.services.CourseService;
import com.school.management.services.UserService;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes={ RootConfig.class, WebConfig.class })
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
	
	private Course course1;
	private Course course2;
	
	private Teacher teacher1;
	private Teacher teacher2;
	
	private CourseRequest courseRequest1;
	private CourseRequest courseRequest2;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = standaloneSetup(controller).build();
		
		course1 = new Course();
		course1.setName("Math");
		course1.setCode("MAT");
		
		course2 = new Course();
		course2.setName("Biology");
		course2.setCode("BIO");
		
		teacher1 = new Teacher();
		teacher1.setFirstName("profa1");
		
		teacher2 = new Teacher();
		teacher2.setFirstName("profa2");
		
		courseRequest1 = new CourseRequest();
		courseRequest1.setCourseName("Math");
		
		courseRequest1 = new CourseRequest();
		courseRequest1.setCourseName("Biology");
	}
	
	@Test
	public void whenRequestedShowAdminPage() throws Exception {
		doReturn(Arrays.asList(course1, course2)).when(courseService).getAllCourses();
		doReturn(Arrays.asList(teacher1, teacher2)).when(courseService).getAllTeachers();
		doReturn(Arrays.asList(courseRequest1, courseRequest2)).when(courseService).allPendingRequests();
		
		mockMvc.perform(post("/adminPage"))
		.andExpect(status().isOk())
		.andExpect(view().name("admin"))
		.andExpect(model().attribute("allCourses", hasSize(2)))
		.andExpect(model().attribute("allTeachers", hasSize(2)))
		.andExpect(model().attribute("allCourseRequests", hasSize(2)));
	}

	@Test 
	public void whenAdminAddsCourseCallControllerMethod() throws Exception {
		mockMvc.perform(post("/addCourse"))
			.andExpect(redirectedUrl("/adminPage")); 
	}
	
	
	////////// need to figure out how to properly test controllers which handle ajax calls ///////////////
	
	@Ignore
	@Test
	public void controllerMethodShouldReturnAllCoursesWichHaveNoAssignedTeacher() throws Exception {
		doReturn(Arrays.asList(course1, course2)).when(courseService).getAllUnassignedCourses();
		
		mockMvc.perform(get("getCoursesWithoutTeachers"))
			.andDo(print())
			.andExpect(jsonPath("$", hasSize(2)));
//			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}
	
	@Ignore
	@Test
	public void whenAdminApprovesCourseRequestCallControllerMethod() throws Exception {
		mockMvc.perform(post("approveCourserequest"))
			.andExpect(content().contentType(MediaType.ALL));
	}
	
	@Ignore
	@Test
	public void whenAdminAssignsCourseToTeacherCallControllerMethod() throws Exception {
		mockMvc.perform(post("/assignCourseToTeacher/{teacherId}/{courseId}", TEACHER_ID, COURSE_ID))
		.andExpect(content().contentType(MediaType.ALL));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
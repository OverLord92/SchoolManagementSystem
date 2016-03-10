package com.school.management.web.layer.spec;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.reset;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.school.management.config.RootConfig;
import com.school.management.controllers.LoginAndRegisterController;
import com.school.management.model.Admin;
import com.school.management.model.Student;
import com.school.management.model.Teacher;
import com.school.management.model.abstr.User;
import com.school.management.services.UserService;
import com.school.management.test.util.TestUtil;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes={ RootConfig.class })
public class LoginSpec {
	
	
	@Mock
	UserService userService;
	
	@InjectMocks
	LoginAndRegisterController controller;
	
	private MockMvc mockMvc;

	private User validUser;
	private User invalidUser;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = standaloneSetup(controller).build();
		reset(userService);
		
		validUser = new User();
		validUser.setUsername(TestUtil.createStringOfCertainLength(5));
		validUser.setRawPassword(TestUtil.createStringOfCertainLength(5));
		validUser.setFirstName("Imestudenta");
		validUser.setLastName("Prezime");
		
		invalidUser = new User();
		// set too long first name to violate validation constraints
		invalidUser.setFirstName(TestUtil.createStringOfCertainLength(20));
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
	public void shouldCheckUsernameAvailabilityAndReturnFalse() throws Exception {
		doReturn(true).when(userService).isUsernameAvaiable(anyString());
		
		boolean response = controller.isUsernameAvaiable(anyString());
		assertEquals(true, response);
	}
	
	@Test
	public void shouldRegisterStudent() throws Exception {
		doReturn(true).when(userService).isUsernameAvaiable(anyString());
		
		Student validStudent = new Student(validUser);
		
		System.out.println();
		mockMvc.perform(post("/registerStudent")
			.sessionAttr("student", validStudent)
				)
			.andExpect(view().name("redirect:/register"));
	}
	
	@Test
	public void shouldNotRegisterStudentInvalidCredentials() throws Exception {
		doReturn(true).when(userService).isUsernameAvaiable(anyString());

		Student student = new Student(invalidUser);
	
		mockMvc.perform(post("/registerStudent")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				//.content(TestUtil.convertObjectToFormUrlEncodedBytes(student))
				.sessionAttr("student", student)
				)//.andDo(MockMvcResultHandlers.print())
			.andExpect(view().name("/registration"));
	}
	
	@Test
	public void shouldNotRegisterStudentDuplicateUsername() throws Exception {
		doReturn(false).when(userService).isUsernameAvaiable(anyString());

		Student student = new Student(invalidUser);
	
		mockMvc.perform(post("/registerStudent")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				//.content(TestUtil.convertObjectToFormUrlEncodedBytes(student))
				.sessionAttr("student", student)
				)//.andDo(MockMvcResultHandlers.print())
			.andExpect(view().name("/registration"));
	}
	
	@Test
	public void shouldRegisterTeacher() throws Exception {
		doReturn(true).when(userService).isUsernameAvaiable(anyString());
		
		Teacher validTeacher = new Teacher(validUser);
		
		mockMvc.perform(post("/registerTeacher")
				.sessionAttr("teacher", validTeacher)
				)
			.andExpect(view().name("redirect:/register"));
	}
	
	@Test
	public void shouldNotRegisterTeacherBadCredentials() throws Exception {
		
		Teacher teacher = new Teacher(invalidUser);
		
		doReturn(true).when(userService).isUsernameAvaiable(anyString());
		mockMvc.perform(post("/registerTeacher")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				//.content(TestUtil.convertObjectToFormUrlEncodedBytes(student))
				.sessionAttr("teacher", teacher)
				)//.andDo(MockMvcResultHandlers.print())
			.andExpect(view().name("/registration"));
	}
	
	@Test
	public void shouldNotRegisterTeacherDuplicateUsername() throws Exception {
		doReturn(false).when(userService).isUsernameAvaiable(anyString());
		
		Teacher teacher = new Teacher(invalidUser);
	
		mockMvc.perform(post("/registerTeacher")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				//.content(TestUtil.convertObjectToFormUrlEncodedBytes(student))
				.sessionAttr("teacher", teacher)
				)//.andDo(MockMvcResultHandlers.print())
			.andExpect(view().name("/registration"));
	}
	
	@Test
	public void shouldRegisterAdmin() throws Exception {
		doReturn(true).when(userService).isUsernameAvaiable(anyString());
		
		Admin admin = new Admin(validUser);
		
		doReturn(true).when(userService).saveAdmin(any());
		mockMvc.perform(post("/registerAdmin")
				.sessionAttr("admin", admin)
				)
			.andExpect(view().name("redirect:/register"));
		
	}
	
	@Test
	public void shouldNotRegisterAdminInvalidCredentials() throws Exception {
		doReturn(true).when(userService).isUsernameAvaiable(anyString());
		
		Admin admin = new Admin(invalidUser);
		
		mockMvc.perform(post("/registerAdmin")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				//.content(TestUtil.convertObjectToFormUrlEncodedBytes(student))
				.sessionAttr("admin", admin)
				)//.andDo(MockMvcResultHandlers.print())
			.andExpect(view().name("/registration"));
	}
	
	@Test
	public void shouldNotRegisterAdminInvalidDuplcateUsername() throws Exception {
		doReturn(false).when(userService).isUsernameAvaiable(anyString());
		
		Admin admin = new Admin(invalidUser);
		
		mockMvc.perform(post("/registerAdmin")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				//.content(TestUtil.convertObjectToFormUrlEncodedBytes(student))
				.sessionAttr("admin", admin)
				)//.andDo(MockMvcResultHandlers.print())
			.andExpect(view().name("/registration"));
	}

}

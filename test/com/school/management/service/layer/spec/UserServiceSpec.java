package com.school.management.service.layer.spec;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import com.school.management.config.RootConfig;
import com.school.management.dao.impl.AdminDaoImpl;
import com.school.management.dao.impl.CourseDaoImpl;
import com.school.management.dao.impl.StudentDaoImpl;
import com.school.management.dao.impl.TeacherDaoImpl;
import com.school.management.model.Absence;
import com.school.management.model.Admin;
import com.school.management.model.Course;
import com.school.management.model.Grade;
import com.school.management.model.Student;
import com.school.management.model.Teacher;
import com.school.management.services.impl.UserServiceImpl;

@ContextConfiguration(classes={ RootConfig.class })
@RunWith(MockitoJUnitRunner.class)
public class UserServiceSpec {
	
	public static final Long STUDENT_ID = 5L;
	public static final Long TEACHER_ID = 7L;
	public static final Long COURSE_ID = 10L;
	public static final Long COURSE_REQUEST_ID = 12L;
	
	@Mock CourseDaoImpl courseDao;
	@Mock StudentDaoImpl studentDao;
	@Mock TeacherDaoImpl teacherDao;
	@Mock AdminDaoImpl adminDao;
	
	@InjectMocks
	UserServiceImpl userService;
	
	private Course course;
	private Student student;
	private Teacher teacher;
	private Admin admin;
	private Absence absence;
	private Grade grade;
	
	private Course course1;
	private Course course2;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		student = new Student();
		course = new Course();
		teacher = new Teacher();
		admin = new Admin();
		absence = new Absence();
		grade = new Grade();
		
		course1 = new Course();
		course1.setName("Math");
		course1.setCode("MAT");
		
		course2 = new Course();
		course2.setName("Biology");
		course2.setCode("BIO");
	}
	
	@Test
	public void whenRequestedServiceMethodShouldSaveAdmin() {
		doReturn(true).when(adminDao).save(admin);
		assertEquals(true, userService.saveAdmin(admin));
	}

	@Test
	public void whenRequestedServiceMethodShouldApproveRequest() {
		doReturn(true).when(studentDao).approveCourseRequest(COURSE_REQUEST_ID);
		assertEquals(true, userService.approveCourseRequest(COURSE_REQUEST_ID));
	}
	
	@Test
	public void whenRequestedServiceMethodShouldCheckUsernameAvailability() {
		String username = "username";
		
		doReturn(true).when(adminDao).isUsernameAvaiable(username);
		assertEquals(true, userService.isUsernameAvaiable(username));
	}
	
	@Test
	public void whenRequestedServiceMethodShouldSaveTeacher() {
		doReturn(true).when(teacherDao).save(teacher);
		assertEquals(true, userService.saveTeacher(teacher));
	}
	
	@Test
	public void whenRequestedServiceMethodShouldReturnTeacher() {
		String username = "username";
		
		doReturn(teacher).when(teacherDao).getTeacherByUsername(username);
		assertEquals(teacher, userService.getTeacherByUsername(username));
	}
	
	@Test
	public void whenRequestedServiceMethodShouldReturnCoursesAssignedToTeacher() {
		Set<Course> teacherCourses = new HashSet<Course>(Arrays.asList(course1, course2));
		
		doReturn(teacherCourses).when(courseDao).getTeachersCourses(teacher);
		assertEquals(teacherCourses, userService.getTeachersCourses(teacher));
	}
	
	@Test
	public void whenRequestedServiceMethodShouldReturnStudent() {
		doReturn(student).when(studentDao).get(STUDENT_ID);
		assertEquals(student, userService.getStudent(STUDENT_ID));
	}
	
	@Test
	public void whenRequestedServiceMethodShouldReturnStudentByUsername() {
		String username = "username";
		
		doReturn(student).when(studentDao).getStudentByUsername(username);
		assertEquals(student, userService.getStudentByUsername(username));
	}
	
	@Test
	public void whenRequestedServiceMethodShouldReturnInitializedStudentByUsername() {
		String username = "username";
		
		doReturn(student).when(studentDao).getStudentFullyInitializedByUsername(username);
		assertEquals(student, userService.getStudentFullyInitializedByUsername(username));
	}
	
	@Test
	public void whenRequestedServiceMethodShouldSaveStudent() {
		doReturn(true).when(studentDao).save(student);
		assertEquals(true, userService.saveStudent(student));
	}
	
	@Test
	public void whenRequestedServiceMethodShouldRequestCourseForStudent() {
		doReturn(course).when(courseDao).get(COURSE_ID);
		assertEquals(true, userService.requestCourse(student, COURSE_ID));
	}
	
	@Test
	public void whenTeacherAddsAbsenceAddAbsenceToStudent() {
		assertEquals(true, userService.addAbsence(STUDENT_ID, absence));
	}
	
	@Test
	public void whenTeacherAddsGradeAddGradeToStudent() {
		assertEquals(true, userService.addGrade(STUDENT_ID, grade));
	}
	
	
	
	
	
	
	
	
	
	
	
}

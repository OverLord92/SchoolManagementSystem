package com.school.management.service.layer.spec;

import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import com.school.management.config.RootConfig;
import com.school.management.dao.impl.CourseDaoImpl;
import com.school.management.dao.impl.StudentDaoImpl;
import com.school.management.dao.impl.TeacherDaoImpl;
import com.school.management.model.Absence;
import com.school.management.model.Course;
import com.school.management.model.Student;
import com.school.management.model.Teacher;
import com.school.management.services.impl.UserServiceImpl;

@ContextConfiguration(classes={ RootConfig.class })
@RunWith(MockitoJUnitRunner.class)
public class UserServiceSpec {
	
	public static final Long STUDENT_ID = 5L;
	public static final Long TEACHER_ID = 7L;
	public static final Long COURSE_ID = 10L;
	
	@Mock CourseDaoImpl courseDao;
	@Mock StudentDaoImpl studentDao;
	@Mock TeacherDaoImpl teacherDao;
	
	@InjectMocks
	UserServiceImpl userService;
	
	private Course course;
	private Student student;
	@SuppressWarnings("unused")
	private Teacher teacher;
	private Absence absence;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		student = new Student();
		course = new Course();
		teacher = new Teacher();
		absence = new Absence();
	}
	
	@Test
	public void whenStudentRequestsCourseAddCourseTORequestedCourses() {
		doReturn(student).when(studentDao).get(STUDENT_ID);
		doReturn(course).when(courseDao).get(COURSE_ID);
//		userService.requestCourse(STUDENT_ID, COURSE_ID);
	}
	
	@Test
	public void whenTeacherAddsAbsenceAddAbsenceToStudent() {
		doReturn(student).when(studentDao).get(STUDENT_ID);
		userService.addAbsence(STUDENT_ID, absence);
	}
	
	
	
	
	
	
	
	
	
	
	
}

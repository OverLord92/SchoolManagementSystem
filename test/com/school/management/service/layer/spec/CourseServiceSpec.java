package com.school.management.service.layer.spec;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

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
import com.school.management.model.Course;
import com.school.management.model.Student;
import com.school.management.model.Teacher;
import com.school.management.services.impl.CourseServiceImpl;

@ContextConfiguration(classes={ RootConfig.class })
@RunWith(MockitoJUnitRunner.class)
public class CourseServiceSpec {

	public static final Long STUDENT_ID = 5L;
	public static final Long TEACHER_ID = 7L;
	public static final Long COURSE_ID = 10L;
	
	@Mock
	CourseDaoImpl courseDao;
	@Mock
	StudentDaoImpl studentDao;
	@Mock
	TeacherDaoImpl teacherDao;
	
	@InjectMocks
	CourseServiceImpl courseService;
	
	private Course course;
	private Student student;
	private Teacher teacher;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		student = new Student();
		course = new Course();
		teacher = new Teacher();
	}
	
	@Test
	public void whenCoursesAddedDaoMethodShouldBeCalled() {
		courseService.addNewCourse(course);
		verify(courseDao).save(course);
	}
	
	@Test
	public void whenCourseIsApprovedAddCourseToStudent() {
		doReturn(student).when(studentDao).get(STUDENT_ID);
		doReturn(course).when(courseDao).get(COURSE_ID);
//		courseService.approveCourseRequest(STUDENT_ID, COURSE_ID);
	}
	
	@Test
	public void whenAdminAsssignsCourseAddCourseToTeacher() {
		doReturn(teacher).when(teacherDao).get(TEACHER_ID);
		doReturn(course).when(courseDao).get(COURSE_ID);
		courseService.assingCourseToTeacher(COURSE_ID, TEACHER_ID);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

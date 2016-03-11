package com.school.management.service.layer.spec;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;

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
import com.school.management.model.CourseRequest;
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
	
	private Course course1;
	private Course course2;
	
	private Student student1;
	private Student student2;
	
	private Teacher teacher1;
	private Teacher teacher2;
	
	private CourseRequest courseRequest1;
	private CourseRequest courseRequest2;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		student = new Student();
		student.setId(STUDENT_ID);
		
		course = new Course();
		teacher = new Teacher();
		
		course1 = new Course();
		course1.setName("Math");
		course1.setCode("MAT");
		
		course2 = new Course();
		course2.setName("Biology");
		course2.setCode("BIO");
		
		student1 = new Student();
		student2 = new Student();
		
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
	public void shouldAddNewCourse() {
		doReturn(true).when(courseDao).save(course);
		assertEquals(true, courseService.addNewCourse(course));
	}
	
	@Test
	public void shouldReturnRightCourse() {
		doReturn(course).when(courseDao).get(COURSE_ID);
		assertEquals(course, courseService.getCourse(COURSE_ID));
	}
	
	@Test
	public void whenRequestingAllCoursesReturnListOfAllCourses() {
		List<Course> allCourses = Arrays.asList(course1, course2);
		
		doReturn(allCourses).when(courseDao).getAll();
		assertEquals(allCourses, courseService.getAllCourses());
	}
	
	@Test
	public void whenRequestingAllCoursesReturnListOfAllUnassignedCourses() {
		List<Course> allFreeCourses = Arrays.asList(course1, course2);
		
		doReturn(allFreeCourses).when(courseDao).getAllUnassignedCourses();
		assertEquals(allFreeCourses, courseService.getAllUnassignedCourses());
	}
	
	@Test 
	public void whenRequestedServiceShouldReturnAllCourseThatCanBeREquesedByStudent() {
		List<Course> allCoursesThatCanBeRequested = Arrays.asList(course1, course2);
		
		doReturn(allCoursesThatCanBeRequested).when(courseDao)
			.getAllNeitherRequestedNorAttendedCourses(STUDENT_ID);
		assertEquals(allCoursesThatCanBeRequested, courseService
			.getAllNeitherRequestedNorAttendedCourses(student));
	}
	
	@Test
	public void whenRequestedServiceShouldReturnAllStudentsOfTheCourse() {
		List<Student> studentsOfCourse = Arrays.asList(student1, student2);
		
		doReturn(studentsOfCourse).when(courseDao).getAllStudentsOfCourse(COURSE_ID);
		assertEquals(studentsOfCourse, courseService.getAllStudentsOfCourse(COURSE_ID));
	}
	
	@Test
	public void whenRequestedServiceShouldReturnAllPendingRequests() {
		List<CourseRequest> allPendingRequests = Arrays.asList(courseRequest1, courseRequest2);
		
		doReturn(allPendingRequests).when(courseDao).getAllPendingRequests();
		assertEquals(allPendingRequests, courseService.allPendingRequests());
	}
	
	@Test
	public void whenRequestedServiceShouldReturnAllTeachers() {
		List<Teacher> allTeachers = Arrays.asList(teacher1, teacher2);
		
		doReturn(allTeachers).when(teacherDao).getAll();
		assertEquals(allTeachers, courseService.getAllTeachers());
	}
	
	@Test
	public void whenAdminAsssignsCourseAddCourseToTeacher() {
		doReturn(course).when(courseDao).get(COURSE_ID);
		doReturn(teacher).when(teacherDao).get(TEACHER_ID);
		
		doReturn(course).when(courseDao).update(course);
		assertEquals(true, courseService.assingCourseToTeacher(COURSE_ID, TEACHER_ID));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

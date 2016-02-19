package com.school.management.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.school.management.dao.generic.GenericDaoImpl;
import com.school.management.dao.interfaces.StudentDao;
import com.school.management.model.Course;
import com.school.management.model.CourseRequest;
import com.school.management.model.Student;

@Component
@Transactional
public class StudentDaoImpl extends GenericDaoImpl<Long, Student> implements StudentDao {

	public StudentDaoImpl() {
		super(Student.class);
	}
	
	@Override
	public boolean save(Student student) {
		student.setEncodedPassword(encoder.encode(student.getPassword()));
		super.save(student);
		return true;
	}
	
	@Override
	public Student getStudentByUsername(String username) {
		Criteria criteria = getSession().createCriteria(Student.class);
		criteria.add(Restrictions.eq("username", username));
		return (Student)criteria.uniqueResult();
	}

	@Override
	public Student getStudentByUsernameWithCollections(String username, 
			boolean requests, boolean courses, boolean grades, boolean absences) {
		
		System.out.println("dao metoda");
		
		Student student = getStudentByUsername(username);
		
		if(requests) {
			Hibernate.initialize(student.getWantedCourses());
		}
		if(courses) {
			Hibernate.initialize(student.getAttendourse());
		}
		

		System.out.println("dao metoda 22");
		return student;
	}

	@Override
	public Student getStudentByIdWithCollections(Long userId, boolean requests, boolean courses, boolean grades,
			boolean absences) {
		Student student = get(userId);
		
		if(requests) {
			Hibernate.initialize(student.getWantedCourses());
		}
		if(courses) {
			Hibernate.initialize(student.getAttendourse());
		}
		return student;
	}

	@Override
	public boolean addCourseToStudent(Long courseRequstId) {
		Session session = sessionFactory.openSession();
		Transaction txn = session.beginTransaction();
		
		CourseRequest courseRequest = session.get(CourseRequest.class, courseRequstId);
		
		Student student = session.get(Student.class, courseRequest.getStudentId());
		Course course = session.get(Course.class, courseRequest.getCourseId());
		
		student.addCourse(course);
		student.getWantedCourses().remove(courseRequest);
	
		txn.commit();
		session.close();
		return true;
	}


}

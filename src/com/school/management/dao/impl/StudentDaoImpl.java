package com.school.management.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.management.dao.generic.GenericDaoImpl;
import com.school.management.dao.interfaces.StudentDao;
import com.school.management.model.Absence;
import com.school.management.model.Course;
import com.school.management.model.CourseRequest;
import com.school.management.model.Grade;
import com.school.management.model.Student;

@Transactional
@Repository
public class StudentDaoImpl extends GenericDaoImpl<Long, Student> implements StudentDao {

	public StudentDaoImpl() {
		super(Student.class);
	}
	
	@Override
	public boolean save(Student student) {
		student.setEncodedPassword(encoder.encode(student.getRawPassword()));
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
	public boolean approveCourseRequest(Long courseRequstId) {
		Session session = sessionFactory.openSession();
		Transaction txn = session.beginTransaction();
		
		CourseRequest courseRequest = session.get(CourseRequest.class, courseRequstId);
		
		Student student = session.get(Student.class, courseRequest.getStudentId());
		Course course = session.get(Course.class, courseRequest.getCourseId());
		
		student.getAttendingCourses().add(course);
		student.getCourseRequests().remove(courseRequest);
		
		session.delete(courseRequest);
		
		txn.commit();
		session.close();
		return true;
	}
	
	@Override
	public void addAbsenceToStudent(Long studentId, Absence absence) {
		Session session = sessionFactory.openSession();
		Transaction txn = session.beginTransaction();
		
		Student student = session.get(Student.class, studentId);
		student.getAbsences().add(absence);
	
		txn.commit();
		session.close();
	}

	@Override
	public void addGradeToStudent(Long studentId, Grade grade) {
		Session session = sessionFactory.openSession();
		Transaction txn = session.beginTransaction();
		
		Student student = session.get(Student.class, studentId);
		student.getGrades().add(grade);
	
		txn.commit();
		session.close();
	}

	@Override
	public void addCourseRequestToStudent(Student student, CourseRequest courseRequst) {
		Session session = sessionFactory.openSession();
		Transaction txn = session.beginTransaction();
		
		Student mergedStudent = (Student)session.merge(student);
		CourseRequest mergedRequest = (CourseRequest)session.merge(courseRequst);
		
		mergedStudent.getCourseRequests().add(mergedRequest);
	
		txn.commit();
		session.close();
	}

	@Override
	public Student getStudentFullyInitializedByUsername(String username) {
		Session session = sessionFactory.openSession();
		Transaction txn = session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Student.class);
		criteria.add(Restrictions.eq("username", username));
		Student student = (Student)criteria.uniqueResult();
		
		student.getAttendingCourses().size();
		student.getCourseRequests().size();
		student.getGrades();
		student.getAbsences();

		txn.commit();
		session.close();
		
		return student;
	}


}

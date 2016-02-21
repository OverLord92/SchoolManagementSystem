package com.school.management.dao.impl;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.school.management.dao.generic.GenericDaoImpl;
import com.school.management.dao.interfaces.TeacherDao;
import com.school.management.model.Course;
import com.school.management.model.Teacher;

@Component
public class TeacherDaoImpl extends GenericDaoImpl<Long, Teacher> 
	implements TeacherDao {

	public TeacherDaoImpl() {
		super(Teacher.class);
	}
	
	@Override
	public boolean save(Teacher teacher) {
		teacher.setEncodedPassword(encoder.encode(teacher.getRawPassword()));
		super.save(teacher);
		return true;
	}

	@Override
	public Teacher getTeacherByIdWithCourses(Long teacherId) {
		Teacher teacher = get(teacherId);
		Hibernate.initialize(teacher.getCourses());
		return teacher;
	}

	@Override
	public Teacher getTeacherByUsername(String teacherUsername) {
		Criteria criteria = getSession().createCriteria(Teacher.class);
		criteria.add(Restrictions.eq("username", teacherUsername));
		
		return (Teacher)criteria.uniqueResult();
		
	}
	
	@Override
	public Teacher getTeacherByUsernameWithCourses(String teacherUsername) {
		Teacher teacher = getTeacherByUsername(teacherUsername);
		Hibernate.initialize(teacher.getCourses());
		
		Set<Course> courses = teacher.getCourses();
		Iterator<Course> iter = courses.iterator();
		while(iter.hasNext()) {
			Hibernate.initialize(iter.next().getStudents());
		}
		
		return teacher;
	}

	@Override
	public void addCourseToTeacher(Long teacherId, Long courseId) {
		Session session = sessionFactory.openSession();
		Transaction txn = session.beginTransaction();
				
		Teacher teacher = session.get(Teacher.class, teacherId);
		Course course = session.get(Course.class, courseId);
		
		teacher.addCourse(course);
		course.setTeacher(teacher);
		
		txn.commit();
		session.close();
	}

}

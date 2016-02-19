package com.school.management.dao.impl;

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
		teacher.setEncodedPassword(encoder.encode(teacher.getPassword()));
		super.save(teacher);
		return true;
	}

	@Override
	public Teacher getTeacherWithCourses(Long teacherId) {
		Teacher teacher = get(teacherId);
		Hibernate.initialize(teacher.getCourses());
		return teacher;
	}

	@Override
	public Teacher getTeacherByUsernameWithCourses(String teacherUsername) {
		Criteria criteria = getSession().createCriteria(Teacher.class);
		criteria.add(Restrictions.eq("username", teacherUsername));
		
		Teacher teacher = (Teacher)criteria.uniqueResult();
		Hibernate.initialize(teacher.getCourses());
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

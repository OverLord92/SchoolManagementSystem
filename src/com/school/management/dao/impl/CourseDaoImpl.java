package com.school.management.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.management.dao.generic.GenericDaoImpl;
import com.school.management.dao.interfaces.CourseDao;
import com.school.management.model.Course;
import com.school.management.model.CourseRequest;
import com.school.management.model.Student;
import com.school.management.model.Teacher;

@Repository
@Transactional
public class CourseDaoImpl extends GenericDaoImpl<Long, Course> implements CourseDao {

	public CourseDaoImpl() {
		super(Course.class);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getFreeCourses() {
		
		Criteria criteria = getSession().createCriteria(Course.class);
		criteria.add(Restrictions.isNull("teacher"));
		
		return criteria.list();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<CourseRequest> getAllPendingRequests() {
		Query query = getSession().createSQLQuery(
				"SELECT * FROM courserequest")
				.addEntity(CourseRequest.class);
		return query.list();
	}


	@SuppressWarnings("unchecked")
	@Override
	public Set<Course> getTeachersCourses(Teacher teacher) {
		Criteria criteria = getSession().createCriteria(Course.class);
		criteria.add(Restrictions.eq("teacher", teacher));
		return new HashSet<Course>(criteria.list());
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getAllStudentsOfCourse(Long courseId) {
		
		String hql = "SELECT s FROM Student s " 
				+ "JOIN s.attendingCourses c " 
				+ "WHERE c.id=:courseId";
		
		Query query = getSession().createQuery(hql);
		query.setParameter("courseId", courseId);
		
		List<Student> result = query.list();
	
		return result;
	}
}

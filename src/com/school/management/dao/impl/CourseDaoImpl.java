package com.school.management.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.school.management.dao.generic.GenericDaoImpl;
import com.school.management.dao.interfaces.CourseDao;
import com.school.management.model.Course;

@Component
public class CourseDaoImpl extends GenericDaoImpl<Long, Course> implements CourseDao {

	public CourseDaoImpl() {
		super(Course.class);
	}

	@Override
	public Course getCourseWithStudents(Long courseId) {
		Course course = get(courseId);
		Hibernate.initialize(course.getStudents());
		return course;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getFreeCourses() {
		
		Criteria criteria = getSession().createCriteria(Course.class);
		criteria.add(Restrictions.isNull("teacher"));
		
		return criteria.list();
	}

	
	
	
}

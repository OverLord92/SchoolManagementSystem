package com.school.management.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
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

	
	/** Returns all classes which don't have an assigned teacher */
	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getAllUnassignedCourses() {
		
		Criteria criteria = getSession().createCriteria(Course.class);
		criteria.add(Restrictions.isNull("teacher"));
		
		return criteria.list();
	}


	/** Returns all requests from the students */
	@SuppressWarnings("unchecked")
	@Override
	public List<CourseRequest> getAllPendingRequests() {
		Query query = getSession().createSQLQuery(
				"SELECT * FROM courserequest")
				.addEntity(CourseRequest.class);
		return query.list();
	}

	/** Returns all courses of requested teacher */
	@SuppressWarnings("unchecked")
	@Override
	public Set<Course> getTeachersCourses(Teacher teacher) {
		Criteria criteria = getSession().createCriteria(Course.class);
		criteria.add(Restrictions.eq("teacher", teacher));
		return new HashSet<Course>(criteria.list());
	}


	/** Returns all students who attend the course with the id courseId */
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


	/** Returns all courses which user with studentId can request
	 * excluding: 
	 * 		all already requested courses
	 * 		all already attending courses */
	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getAllNeitherRequestedNorAttendedCourses(Long studentId) {
		String queryString = "SELECT c.* FROM course c " +
				"WHERE id NOT IN ( " +
				"SELECT courseId " +
				"FROM courserequest " +
				"WHERE studentId=:studentId" +
				") AND id NOT IN ( " +
				"SELECT courseId " +
				"FROM student_course " +
				"WHERE studentId=:studentId" +
				")";
		
		Query query = getSession().createSQLQuery(queryString).addEntity(Course.class);
				query.setParameter("studentId", studentId);
		
		return query.list();
	}
}

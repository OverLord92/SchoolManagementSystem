package com.school.management.dao.impl;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.school.management.dao.generic.GenericDaoImpl;
import com.school.management.dao.interfaces.CourseRequestDao;
import com.school.management.model.CourseRequest;

@Component
public class CourseRequestDaoImpl  extends GenericDaoImpl<Long, CourseRequest> implements CourseRequestDao {

	public CourseRequestDaoImpl() {
		super(CourseRequest.class);
	}
	
	@Override
	public Set<CourseRequest> getAllCourseRequests() {
		// TODO Auto-generated method stub
		return null;
	}

}

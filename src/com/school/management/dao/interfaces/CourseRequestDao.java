package com.school.management.dao.interfaces;

import java.util.Set;

import com.school.management.dao.generic.GenericDao;
import com.school.management.model.CourseRequest;

public interface CourseRequestDao extends GenericDao<Long, CourseRequest>{

	Set<CourseRequest> getAllCourseRequests();
	
}

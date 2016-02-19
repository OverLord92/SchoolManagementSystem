package com.school.management.dao.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.school.management.dao.generic.GenericDao;
import com.school.management.model.Course;

@Repository
public interface CourseDao extends GenericDao<Long, Course>{

	List<Course> getFreeCourses();
}

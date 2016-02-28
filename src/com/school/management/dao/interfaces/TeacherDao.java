package com.school.management.dao.interfaces;

import java.util.Set;

import org.springframework.stereotype.Repository;

import com.school.management.dao.generic.GenericDao;
import com.school.management.model.Course;
import com.school.management.model.Teacher;

@Repository
public interface TeacherDao extends GenericDao<Long, Teacher>{

	Teacher getTeacherByUsername(String username);


	
	
}

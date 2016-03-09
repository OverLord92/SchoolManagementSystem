package com.school.management.dao.interfaces;

import org.springframework.stereotype.Repository;

import com.school.management.dao.generic.GenericDao;
import com.school.management.model.Teacher;

@Repository
public interface TeacherDao extends GenericDao<Long, Teacher>{

	Teacher getTeacherByUsername(String username);


	
	
}

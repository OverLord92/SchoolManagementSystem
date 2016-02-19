package com.school.management.dao.interfaces;

import org.springframework.stereotype.Repository;

import com.school.management.dao.generic.GenericDao;
import com.school.management.model.Course;
import com.school.management.model.Student;

@Repository
public interface StudentDao extends GenericDao<Long, Student>{

	Student getStudentByUsername(String username);
	
	Student getStudentByUsernameWithCollections(
			String username, boolean requests, boolean courses, boolean grades, boolean absences);
	
	Student getStudentByIdWithCollections(Long userId, boolean requests, boolean courses, boolean grades,
			boolean absences);

	boolean addCourseToStudent(Long courseRequstId);
	
}

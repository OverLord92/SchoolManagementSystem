package com.school.management.dao.interfaces;

import org.springframework.stereotype.Repository;

import com.school.management.dao.generic.GenericDao;
import com.school.management.model.Absence;
import com.school.management.model.CourseRequest;
import com.school.management.model.Grade;
import com.school.management.model.Student;

@Repository
public interface StudentDao extends GenericDao<Long, Student>{

	Student getStudentByUsername(String username);
	Student getStudentFullyInitializedByUsername(String username);
	
	boolean approveCourseRequest(Long courseRequstId);
	void addGradeToStudent(Long studentId, Grade grade);
	void addAbsenceToStudent(Long studentId, Absence absence);
	void addCourseRequestToStudent(Student student, CourseRequest courseRequst);

		
	


	
	
}

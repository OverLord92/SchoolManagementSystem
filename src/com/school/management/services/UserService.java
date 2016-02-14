package com.school.management.services;

import org.springframework.stereotype.Service;

import com.school.management.model.Admin;
import com.school.management.model.Student;
import com.school.management.model.Teacher;

@Service
public interface UserService {

	public boolean saveStudent(Student student);
	public boolean saveTeacher(Teacher teeacher);
	public boolean saveAdmin(Admin admin);
	
}

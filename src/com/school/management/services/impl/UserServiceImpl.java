package com.school.management.services.impl;

import org.springframework.stereotype.Component;

import com.school.management.model.Admin;
import com.school.management.model.Student;
import com.school.management.model.Teacher;
import com.school.management.services.UserService;

@Component
public class UserServiceImpl implements UserService{

	@Override
	public boolean saveStudent(Student student) {
		return true;
	}

	@Override
	public boolean saveTeacher(Teacher teeacher) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return false;
	}

}

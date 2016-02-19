package com.school.management.dao.impl;

import org.springframework.stereotype.Component;

import com.school.management.dao.generic.GenericDaoImpl;
import com.school.management.dao.interfaces.AdminDao;
import com.school.management.model.Admin;

@Component
public class AdminDaoImpl extends GenericDaoImpl<Long, Admin>
	implements AdminDao {

	public AdminDaoImpl() {
		super(Admin.class);
	}
	
}

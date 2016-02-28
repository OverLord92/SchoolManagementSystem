package com.school.management.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.management.dao.generic.GenericDaoImpl;
import com.school.management.dao.interfaces.AdminDao;
import com.school.management.model.Admin;

@Repository
@Transactional
public class AdminDaoImpl extends GenericDaoImpl<Long, Admin>
	implements AdminDao {

	public AdminDaoImpl() {
		super(Admin.class);
	}
	
}

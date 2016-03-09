package com.school.management.dao.impl;

import java.util.List;

import org.hibernate.Query;
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
	
	@Override
	public boolean save(Admin admin) {
		admin.setEncodedPassword(encoder.encode(admin.getRawPassword()));
		super.save(admin);
		return true;
	}

	@Override
	public boolean isUsernameAvaiable(String username) {
		Query query = getSession().createSQLQuery("SELECT username FROM user");
		
		@SuppressWarnings("unchecked")
		List<String> allUsernames= query.list();
		
		for(String strUser: allUsernames)
			System.out.println(strUser);
				
		return !allUsernames.contains(username);
	}
	
}

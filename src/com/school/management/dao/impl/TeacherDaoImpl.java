package com.school.management.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.management.dao.generic.GenericDaoImpl;
import com.school.management.dao.interfaces.TeacherDao;
import com.school.management.model.Teacher;

@Repository
@Transactional
public class TeacherDaoImpl extends GenericDaoImpl<Long, Teacher> 
	implements TeacherDao {

	public TeacherDaoImpl() {
		super(Teacher.class);
	}
	
	/** Sets encoded password and saves teacher */
	@Override
	public boolean save(Teacher teacher) {
		teacher.setEncodedPassword(encoder.encode(teacher.getRawPassword()));
		super.save(teacher);
		return true;
	}

	@Override
	public Teacher getTeacherByUsername(String username) {
		Criteria criteria = getSession().createCriteria(Teacher.class);
		criteria.add(Restrictions.eq("username", username));
		return (Teacher)criteria.uniqueResult();
	}

	

}

package com.school.management.dao.impl;

import com.school.management.dao.generic.GenericDaoImpl;
import com.school.management.dao.interfaces.AbsenceDao;
import com.school.management.model.Absence;

public class AbsenceDaoImpl extends GenericDaoImpl<Long, Absence> 
implements AbsenceDao {

	public AbsenceDaoImpl() {
		super(Absence.class);
	}

}

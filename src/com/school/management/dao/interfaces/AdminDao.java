package com.school.management.dao.interfaces;

import org.springframework.stereotype.Repository;

import com.school.management.dao.generic.GenericDao;
import com.school.management.model.Admin;

@Repository
public interface AdminDao extends GenericDao<Long, Admin> {

}

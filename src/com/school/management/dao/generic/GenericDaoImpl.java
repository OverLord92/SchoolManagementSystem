package com.school.management.dao.generic;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.school.management.model.abstr.BaseEntity;

@Transactional
public abstract class GenericDaoImpl<PK extends Serializable, T extends BaseEntity> 
	implements GenericDao<PK, T> {

	@Autowired
	public SessionFactory sessionFactory;
	
	private Class<T> persistentClass;
	
	@Autowired
	public PasswordEncoder encoder;
	
	public GenericDaoImpl(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}
	
	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	
	@Override
	public boolean save(T entity) {
		getSession().save(entity);
		return true;
	}

	@Override
	public T get(PK id) {
		return getSession().get(persistentClass, id);
	}

	@Override
	public T update(T entity) {
		getSession().update(entity);
		return entity;
	}
	
	@Override
	public T merge(T entity) {
		getSession().merge(entity);
		return entity;
	}

	@Override
	public void delete(T entity) {
		getSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		Criteria criteria = getSession().createCriteria(persistentClass);
		return criteria.list();
	}
	
}

package com.school.management.dao.generic;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface GenericDao<PK extends Serializable, T> {

	boolean save(T entity);
	T get(PK id);
	T update(T entity);
	public T merge(T entity);
	void delete(T entity);
	List<T> getAll();
	
}

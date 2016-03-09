package com.school.management.model.abstr;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/** A Base class which provides an id for all persistent.
 * 	All Entity classes must extend this class. */
@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

}

package com.citiustech.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class GenericRepo {

	@PersistenceContext
	protected EntityManager entitymanager;
	
	public Object save(Object obj) {
		return entitymanager.merge(obj);
		
	}
	
	public <E> E findById(Class<E> clazz,Object pk) {
		E e=entitymanager.find(clazz, pk);
		return e;
	}
}

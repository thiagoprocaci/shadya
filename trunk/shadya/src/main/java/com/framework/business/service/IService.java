package com.framework.business.service;

import java.io.Serializable;
import java.util.List;

import com.framework.entity.IEntity;
import com.framework.persistence.dao.dql.OrderBy;

public interface IService<T extends IEntity<ID>, ID extends Serializable> {
	/**
	 * 
	 * @param id
	 * @return Retorna objeto atraves do Id
	 */
	public T getById(ID id);

	/**
	 * 
	 * @return Retorna todos
	 */
	public List<T> findAll();
	
	/**
	 * 
	 * @return Retorna todos
	 * @param orderBy
	 */
	public List<T> findAll(OrderBy... orderBy);

	/**
	 * Persiste objeto
	 * 
	 * @param o
	 */
	public void persist(T o);

	/**
	 * Atualiza objeto
	 * 
	 * @param o
	 */
	public void update(T o);

	/**
	 * Exclui objeto
	 * 
	 * @param o
	 */
	public void delete(T o);
}

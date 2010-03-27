package com.framework.persistence.dao;

import java.io.Serializable;
import java.util.List;

import com.framework.entity.IEntity;
import com.framework.persistence.dao.dql.OrderBy;
import com.framework.persistence.dao.dql.support.ParameterQuery;

/**
 * 
 * Interface dao generica
 * 
 * @param <T>
 * @param <ID>
 */
public interface IDao<T extends IEntity<ID>, ID extends Serializable> {
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
	 * @param orderByList
	 */
	public List<T> findAll(OrderBy... orderByList);

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

	/**
	 * Executa query
	 * 
	 * @param jdoql
	 * @param parameterQueryList
	 * @return Lista com os resultados
	 */
	public List<T> executeQuery(String jdoql, ParameterQuery... parameterQueryList);
}

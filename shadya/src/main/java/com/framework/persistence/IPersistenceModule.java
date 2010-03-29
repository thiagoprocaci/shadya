package com.framework.persistence;

import java.io.Serializable;
import java.util.List;

import com.framework.kernel.module.IKernelModule;
import com.framework.persistence.dao.dql.OrderBy;
import com.framework.persistence.dao.dql.support.ParameterQuery;
import com.framework.persistence.jdo.manager.IJDOPersistenceManager;

/**
 * 
 * Modulo de persistencia do framework
 * 
 */
public interface IPersistenceModule extends IKernelModule {
	/**
	 * 
	 * @param id
	 * @param clazz
	 * @return Retorna objeto atraves do Id
	 */
	@SuppressWarnings("unchecked")
	public Object getById(Class clazz, Serializable id);

	/**
	 * @param clazz
	 * @return Retorna todos
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findAll(Class clazz);

	/**
	 * 
	 * @return Retorna todos
	 * @param orderByList
	 * @param clazz
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findAll(Class clazz, OrderBy... orderByList);

	/**
	 * Persiste objeto
	 * 
	 * @param o
	 * @param clazz
	 */
	@SuppressWarnings("unchecked")
	public void persist(Class clazz, Object o);

	/**
	 * Atualiza objeto
	 * 
	 * @param o
	 * @param clazz
	 * @param id
	 */
	@SuppressWarnings("unchecked")
	public void update(Class clazz, Object o, Serializable id);

	/**
	 * Exclui objeto
	 * 
	 * @param o
	 * @param clazz
	 */
	@SuppressWarnings("unchecked")
	public void delete(Class clazz, Object o);

	/**
	 * Executa query
	 * 
	 * @param query
	 * @param parameterQueryList
	 * @param clazz
	 * @return Lista com os resultados
	 */
	@SuppressWarnings("unchecked")
	public List<Object> executeQuery(Class clazz, String query, ParameterQuery... parameterQueryList);

	/**
	 * 
	 * @return Retorna o objeto que realmente realiza a persistencia dos dados
	 */
	public IJDOPersistenceManager getPersistenceManager();
}

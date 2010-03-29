package com.framework.persistence.dao.core;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.framework.entity.IEntity;
import com.framework.persistence.IPersistenceModule;
import com.framework.persistence.dao.IDao;
import com.framework.persistence.dao.dql.OrderBy;
import com.framework.persistence.dao.dql.support.ParameterQuery;

/**
 * 
 * Implementacao da interface dao generica
 * 
 * @param <T>
 * @param <ID>
 */
public abstract class GoogleDao<T extends IEntity<ID>, ID extends Serializable> implements IDao<T, ID> {
	private IPersistenceModule persistenceModule;
	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public GoogleDao() {
		persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Required
	public void setPersistenceModule(IPersistenceModule persistenceModule) {
		this.persistenceModule = persistenceModule;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 */
	@Override
	public void delete(T o) {
		persistenceModule.delete(persistentClass, o);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return (List<T>) persistenceModule.findAll(persistentClass);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T getById(ID id) {
		return (T) persistenceModule.getById(persistentClass, id);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 */
	@Override
	public void persist(T o) {
		persistenceModule.persist(persistentClass, o);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 */
	@Override
	public void update(T o) {
		persistenceModule.update(persistentClass, o, o.getId());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(OrderBy... orderByList) {
		return (List<T>) persistenceModule.findAll(persistentClass, orderByList);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> executeQuery(String jdoql, ParameterQuery... parameterQueryList) {
		return (List<T>) persistenceModule.executeQuery(persistentClass, jdoql, parameterQueryList);
	}
}

package com.kameshi.framework.support.persistence.core;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.kameshi.framework.module.persistence.IPersistenceModule;
import com.kameshi.framework.module.persistence.support.OrderBy;
import com.kameshi.framework.support.persistence.IDao;

/**
 * Implementacao do Dao generico
 * 
 * @param <T>
 *            o tipo da entidade tratada pelo Dao
 * @param <ID>
 *            o tipo do id da entitade
 */
public class CoreDao<T, ID extends Serializable> implements IDao<T, ID> {
	private Class<T> persistentClass;
	private IPersistenceModule persistenceModule;

	private Class<T> getPersistentClass() {
		return persistentClass;
	}

	/**
	 * Construtor default que inicializa a classe de pesistencia
	 */
	@SuppressWarnings("unchecked")
	public CoreDao() {
		Class tipo = getClass();
		while ((tipo.getGenericSuperclass() instanceof ParameterizedType) == false) {
			tipo = tipo.getSuperclass();
		}
		this.persistentClass = (Class<T>) ((ParameterizedType) tipo.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return (List<T>) persistenceModule.findAll(getPersistentClass());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param orderByList
	 *            {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(OrderBy... orderByList) {
		return (List<T>) persistenceModule.findAll(getPersistentClass(), orderByList);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param o
	 *            {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByExample(T o) {
		return (List<T>) persistenceModule.findByExample(o);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param id
	 *            {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T getById(final ID id) {
		return (T) persistenceModule.getById(getPersistentClass(), id);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param o
	 *            {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	@Transactional
	public T merge(T o) {
		persistenceModule.merge(o);
		return o;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param o
	 *            {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	@Transactional
	public T persist(T o) {
		persistenceModule.persist(o);
		return o;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param o
	 *            {@inheritDoc}
	 */
	@Override
	@Transactional
	public void remove(T o) {
		persistenceModule.remove(o);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param o
	 *            {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	@Transactional
	public T update(T o) {
		persistenceModule.update(o);
		return o;
	}

	@Override
	public IPersistenceModule getPersistenceModule() {
		return persistenceModule.getPersistenceModule();
	}

	@Required
	public void setPersistenceModule(IPersistenceModule persistenceModule) {
		this.persistenceModule = persistenceModule;
	}
}

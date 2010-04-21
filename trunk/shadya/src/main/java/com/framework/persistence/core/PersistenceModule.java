package com.framework.persistence.core;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.Persistent;

import org.springframework.beans.factory.annotation.Required;

import com.framework.common.ReflectionUtil;
import com.framework.kernel.module.core.KernelModule;
import com.framework.persistence.IPersistenceModule;
import com.framework.persistence.dao.dql.OrderBy;
import com.framework.persistence.dao.dql.support.ParameterQuery;
import com.framework.persistence.dao.dql.support.QueryHelper;
import com.framework.persistence.exception.PersistenceException;
import com.framework.persistence.jdo.manager.IJDOPersistenceManager;

/**
 * 
 * Modulo de persistencia do framework
 * 
 */
public class PersistenceModule extends KernelModule implements IPersistenceModule {
	private static final long serialVersionUID = -3266603364634330568L;
	private static final Logger LOG = Logger.getLogger(PersistenceModule.class.getName());
	private IJDOPersistenceManager jdoPersistenceManager;

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void delete(Class clazz, Object o) {
		PersistenceManager persistenceManager = jdoPersistenceManager.getPersistenceJDOManager();
		try {
			persistenceManager.deletePersistent(o);
		} finally {
			persistenceManager.close();			
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> executeQuery(Class clazz, String queryString, ParameterQuery... parameterQueryList) {
		PersistenceManager persistenceManager = jdoPersistenceManager.getPersistenceJDOManager();
		Query query = persistenceManager.newQuery(clazz, queryString);
		query.declareParameters(QueryHelper.getParametersAsString(parameterQueryList));
		try {
			return (List<Object>) QueryHelper.getQueryResult(query, QueryHelper.getParametersAsMap(parameterQueryList));
		} finally {
			query.closeAll();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findAll(Class clazz) {
		PersistenceManager persistenceManager = jdoPersistenceManager.getPersistenceJDOManager();
		Query query = persistenceManager.newQuery(clazz);
		try {			
			return (List<Object>) QueryHelper.getQueryResult(query);
		} finally {
			query.closeAll();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 * @param {@inheritDoc
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findAll(Class clazz, OrderBy... orderByList) {
		PersistenceManager persistenceManager = jdoPersistenceManager.getPersistenceJDOManager();
		Query query = persistenceManager.newQuery(clazz);
		query.setOrdering(QueryHelper.getOrderByAsString(orderByList));
		try {
			return (List<Object>) QueryHelper.getQueryResult(query);
		} finally {
			query.closeAll();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object getById(Class clazz, Serializable id) {
		PersistenceManager persistenceManager = jdoPersistenceManager.getPersistenceJDOManager();
		try {
			return persistenceManager.getObjectById(clazz, id);
		} finally {
			persistenceManager.close();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void persist(Class clazz, Object o) {
		PersistenceManager persistenceManager = jdoPersistenceManager.getPersistenceJDOManager();
		try {
			persistenceManager.makePersistent(o);
		} finally {
			persistenceManager.close();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void update(Class clazz, Object o, Serializable id) {
		PersistenceManager persistenceManager = jdoPersistenceManager.getPersistenceJDOManager();
		try {
			Object savedObject = persistenceManager.getObjectById(clazz, id);
			for (Field field : clazz.getDeclaredFields()) {
				if (field.isAnnotationPresent(Persistent.class)) {
					String setMethod = ReflectionUtil.getSetterMethodName(field);
					String getMethod = ReflectionUtil.getGetterMethodName(field);
					try {
						Object newValue = clazz.getMethod(getMethod).invoke(o);
						clazz.getMethod(setMethod, clazz.getMethod(getMethod).getReturnType()).invoke(savedObject, newValue);
					} catch (IllegalArgumentException e) {
						throw new PersistenceException(e);
					} catch (SecurityException e) {
						throw new PersistenceException(e);
					} catch (IllegalAccessException e) {
						throw new PersistenceException(e);
					} catch (InvocationTargetException e) {
						throw new PersistenceException(e);
					} catch (NoSuchMethodException e) {
						throw new PersistenceException(e);
					}
				}
			}
		} finally {
			persistenceManager.close();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize() {
		LOG.info("starting persistence module");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IJDOPersistenceManager getPersistenceManager() {
		return jdoPersistenceManager;
	}

	@Required
	public void setJdoPersistenceManager(IJDOPersistenceManager applicationPersistenceManager) {
		this.jdoPersistenceManager = applicationPersistenceManager;
	}
}

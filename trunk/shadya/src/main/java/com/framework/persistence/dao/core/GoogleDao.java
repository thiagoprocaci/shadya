package com.framework.persistence.dao.core;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.Persistent;

import org.springframework.beans.factory.annotation.Required;

import com.framework.common.ReflectionUtil;
import com.framework.entity.IEntity;
import com.framework.persistence.dao.IDao;
import com.framework.persistence.dao.dql.OrderBy;
import com.framework.persistence.dao.dql.support.ParameterQuery;
import com.framework.persistence.dao.dql.support.QueryHelper;
import com.framework.persistence.exception.PersistenceException;
import com.framework.persistence.manager.IApplicationPersistenceManager;

/**
 * 
 * Implementacao da interface dao generica
 * 
 * @param <T>
 * @param <ID>
 */
public abstract class GoogleDao<T extends IEntity<ID>, ID extends Serializable> implements IDao<T, ID> {
	private IApplicationPersistenceManager applicationPersistenceManager;
	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public GoogleDao() {
		persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Required
	public void setApplicationPersistenceManager(IApplicationPersistenceManager applicationPersistenceManager) {
		this.applicationPersistenceManager = applicationPersistenceManager;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 */
	@Override
	public void delete(T o) {
		PersistenceManager persistenceManager = applicationPersistenceManager.getPersistenceJDOManager();
		try {
			persistenceManager.deletePersistent(o);
		} finally {
			persistenceManager.close();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		PersistenceManager persistenceManager = applicationPersistenceManager.getPersistenceJDOManager();
		Query query = persistenceManager.newQuery(persistentClass);
		try {
			return (List<T>) QueryHelper.getQueryResult(query);
		} finally {
			query.closeAll();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 */
	@Override
	public T getById(ID id) {
		PersistenceManager persistenceManager = applicationPersistenceManager.getPersistenceJDOManager();
		try {
			return persistenceManager.getObjectById(persistentClass, id);
		} finally {
			persistenceManager.close();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 */
	@Override
	public void persist(T o) {
		PersistenceManager persistenceManager = applicationPersistenceManager.getPersistenceJDOManager();
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
	 */
	@Override
	public void update(T o) {
		PersistenceManager persistenceManager = applicationPersistenceManager.getPersistenceJDOManager();
		try {
			T savedObject = persistenceManager.getObjectById(persistentClass, o.getId());
			for (Field field : persistentClass.getDeclaredFields()) {
				if (field.isAnnotationPresent(Persistent.class)) {
					String setMethod = ReflectionUtil.getSetterMethodName(field);
					String getMethod = ReflectionUtil.getGetterMethodName(field);
					try {
						Object newValue = persistentClass.getMethod(getMethod).invoke(o);
						persistentClass.getMethod(setMethod, persistentClass.getMethod(getMethod).getReturnType()).invoke(savedObject, newValue);
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
	 * 
	 * @param {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(OrderBy... orderByList) {
		PersistenceManager persistenceManager = applicationPersistenceManager.getPersistenceJDOManager();
		String clause = QueryHelper.getOrderByAsString(orderByList);
		Query query = persistenceManager.newQuery(persistentClass);
		if (clause != null) {
			query.setOrdering(clause.toString());
		}
		try {
			return (List<T>) QueryHelper.getQueryResult(query);
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
	public List<T> executeQuery(String jdoql, ParameterQuery... parameterQueryList) {
		PersistenceManager persistenceManager = applicationPersistenceManager.getPersistenceJDOManager();
		Query query = persistenceManager.newQuery(persistentClass, jdoql);
		Object[] objects = null;
		if (parameterQueryList != null && parameterQueryList.length > 0) {
			objects = new Object[parameterQueryList.length];
			StringBuilder parameters = new StringBuilder();
			for (int i = 0; i < parameterQueryList.length; i++) {
				parameters.append(parameterQueryList[i].getType().getName() + " " + parameterQueryList[i].getParameterName());
				if (i != parameterQueryList.length - 1) {
					parameters.append(", ");
				}
				objects[i] = parameterQueryList[i].getValue();
			}
			query.declareParameters(parameters.toString());
		}
		try {
			return (List<T>) QueryHelper.getQueryResult(query, objects);
		} finally {
			query.closeAll();
		}
	}
}

package com.framework.persistence.jdo.manager.core;

import javax.jdo.PersistenceManager;

import org.springframework.beans.factory.annotation.Required;

import com.framework.persistence.jdo.factory.IJDOPersistenceFactory;
import com.framework.persistence.jdo.manager.IJDOPersistenceManager;

/**
 * 
 * Entidade que prove acesso a interface de persistencia do JDO
 * 
 */
public class JDOPersistenceManager implements IJDOPersistenceManager {
	private IJDOPersistenceFactory jdoPersistenceFactory;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PersistenceManager getPersistenceJDOManager() {
		return jdoPersistenceFactory.getPersistenceJDOManagerFactory().getPersistenceManager();
	}

	@Required
	public void setJdoPersistenceFactory(IJDOPersistenceFactory jdoPersistenceFactory) {
		this.jdoPersistenceFactory = jdoPersistenceFactory;
	}
}

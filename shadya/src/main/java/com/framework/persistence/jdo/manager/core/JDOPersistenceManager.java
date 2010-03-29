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
	private IJDOPersistenceFactory persistenceFactory;

	@Required
	public void setPersistenceFactory(IJDOPersistenceFactory persistenceFactory) {
		this.persistenceFactory = persistenceFactory;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PersistenceManager getPersistenceJDOManager() {
		return persistenceFactory.getPersistenceJDOManagerFactory().getPersistenceManager();
	}
}

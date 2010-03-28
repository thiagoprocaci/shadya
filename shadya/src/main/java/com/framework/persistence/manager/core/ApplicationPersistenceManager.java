package com.framework.persistence.manager.core;

import javax.jdo.PersistenceManager;

import org.springframework.beans.factory.annotation.Required;

import com.framework.persistence.factory.IPersistenceFactory;
import com.framework.persistence.manager.IApplicationPersistenceManager;

/**
 * 
 * Entidade que prove acesso a interface de persistencia do JDO
 * 
 */
public class ApplicationPersistenceManager implements IApplicationPersistenceManager {
	private IPersistenceFactory persistenceFactory;

	@Required
	public void setPersistenceFactory(IPersistenceFactory persistenceFactory) {
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

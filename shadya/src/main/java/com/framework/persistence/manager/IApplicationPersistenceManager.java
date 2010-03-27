package com.framework.persistence.manager;

import javax.jdo.PersistenceManager;

/**
 * 
 * Interface que prove acesso a interface de persistencia do JDO
 * 
 */
public interface IApplicationPersistenceManager {
	/**
	 * 
	 * @return Retorna persistenceManager do JDO
	 */
	public PersistenceManager getPersistenceJDOManager();
}

package com.framework.persistence.jdo.manager;

import javax.jdo.PersistenceManager;

/**
 * 
 * Interface que prove acesso a interface de persistencia do JDO
 * 
 */
public interface IJDOPersistenceManager {
	/**
	 * 
	 * @return Retorna persistenceManager do JDO
	 */
	public PersistenceManager getPersistenceJDOManager();
}

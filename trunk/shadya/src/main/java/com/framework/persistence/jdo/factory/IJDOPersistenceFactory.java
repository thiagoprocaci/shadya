package com.framework.persistence.jdo.factory;

import javax.jdo.PersistenceManagerFactory;

/**
 * 
 * Interface que da acesso a interface JDO factory do app engine.
 * 
 */
public interface IJDOPersistenceFactory {
	/**
	 * 
	 * @return Retorna PersistenceManagerFactory do JDO
	 */
	public PersistenceManagerFactory getPersistenceJDOManagerFactory();
}

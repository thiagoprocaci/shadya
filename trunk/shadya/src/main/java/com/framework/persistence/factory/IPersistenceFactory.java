package com.framework.persistence.factory;

import javax.jdo.PersistenceManagerFactory;

/**
 * 
 * Interface que da acesso a interface JDO factory do app engine.
 * 
 */
public interface IPersistenceFactory {
	/**
	 * 
	 * @return Retorna PersistenceManagerFactory do JDO
	 */
	public PersistenceManagerFactory getPersistenceJDOManagerFactory();
}

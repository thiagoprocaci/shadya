package com.framework.persistence.jdo.factory.core;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import com.framework.persistence.jdo.factory.IJDOPersistenceFactory;

/**
 * 
 * Entidade que prove acesso a interface JDO factory do app engine.
 * 
 */
public class JDOPersistenceFactory implements IJDOPersistenceFactory {
	private static final String TRANSACTIONS_OPTIONAL = "transactions-optional";
	private static PersistenceManagerFactory PMF_INSTANCE = JDOHelper.getPersistenceManagerFactory(TRANSACTIONS_OPTIONAL);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PersistenceManagerFactory getPersistenceJDOManagerFactory() {
		return PMF_INSTANCE;
	}
}

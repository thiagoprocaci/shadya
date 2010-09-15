package com.kameshi.framework.module.transaction.core;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.kameshi.framework.kernel.module.core.KernelModule;
import com.kameshi.framework.module.transaction.ITransactionModule;

/**
 * Implementacao do modulo de transacao do framework
 */
public class HibernateSessionManager extends KernelModule implements ITransactionModule {
	private static final long serialVersionUID = 427440941025298755L;
	private static final Logger LOG = LoggerFactory.getLogger(HibernateSessionManager.class);
	private HibernateTransactionManager transactionManager = null;
	private SessionHolder sessionHolder = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void beginTransaction() {
		getSession().getTransaction().begin();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void commit() {
		getSession().getTransaction().commit();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void rollback() {
		getSession().getTransaction().rollback();
	}
	
	public HibernateTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public SessionFactory getSessionFactory() {
		return transactionManager.getSessionFactory();
	}

	/**
	 * Retorna a sessao de hibernate
	 * 
	 * @return a sessao
	 */
	public Session getSession() {
		return SessionFactoryUtils.getSession(getSessionFactory(), false);
	}

	/**
	 * {@inheritDoc}
	 */
	public void openSession() {
		if (TransactionSynchronizationManager.hasResource(getSessionFactory())) {
			SessionFactoryUtils.getSession(getSessionFactory(), true);
		} else {
			if (sessionHolder == null || !sessionHolder.isOpen()) {
				sessionHolder = new SessionHolder(getSessionFactory().openSession());
				TransactionSynchronizationManager.bindResource(getSessionFactory(), sessionHolder);
			}
			// sessionHolder.getSession();
			SessionFactoryUtils.getSession(getSessionFactory(), true);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void closeSession() {
		SessionFactoryUtils.closeSession(getSessionFactory().getCurrentSession());
		getSessionFactory().close();
	}

	@Autowired
	@Required
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = (HibernateTransactionManager) transactionManager;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize() {
		LOG.info("starting transaction module");
	}
}

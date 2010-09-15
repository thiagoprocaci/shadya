package com.kameshi.framework.support.persistence.hibernate;

import java.io.Serializable;

import org.hibernate.Session;

import com.kameshi.framework.module.persistence.hibernate.core.HibernatePersistenceModule;
import com.kameshi.framework.support.persistence.core.CoreDao;

/**
 * Dao que fornece acesso a sessao do hibernate
 * 
 * @param <T>
 *            o tipo da entidade
 * @param <ID>
 *            o tipo do id da entidade
 */
public class HibernateDao<T, ID extends Serializable> extends CoreDao<T, ID> {
	public Session getSession() {
		return ((HibernatePersistenceModule) getPersistenceModule()).getSession();
	}
}

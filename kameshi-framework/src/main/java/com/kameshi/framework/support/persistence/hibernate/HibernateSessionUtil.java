package com.kameshi.framework.support.persistence.hibernate;

import org.hibernate.Session;

import com.kameshi.framework.kernel.Kernel;
import com.kameshi.framework.module.transaction.core.HibernateSessionManager;

/**
 * Classe utilitaria para operacoes Hibernate
 */
public class HibernateSessionUtil {
	/**
	 * Construtor default da classe estatica
	 */
	private HibernateSessionUtil() {
	}

	/**
	 * Limpa a sessao do hibernate
	 */
	public static void clear() {
		((HibernateSessionManager) Kernel.getTransactionModule()).getSession().clear();
	}

	/**
	 * Realiza o evict na entidade
	 * 
	 * @param entity
	 *            entidade a ser "evictada"
	 */
	public static void evict(Object entity) {
		((HibernateSessionManager) Kernel.getTransactionModule()).getSession().evict(entity);
	}

	/**
	 * Realiza o refresh da entidade
	 * 
	 * @param entity
	 *            entidade a ser "refreshada"
	 */
	public static void refresh(Object entity) {
		((HibernateSessionManager) Kernel.getTransactionModule()).getSession().refresh(entity);
	}

	/**
	 * Informa se a sessao contem a entidade
	 * 
	 * @param entity
	 *            entidade a ter a existencia verificada
	 * @return true se a entidade estiver na sessao
	 */
	public static boolean contains(Object entity) {
		return ((HibernateSessionManager) Kernel.getTransactionModule()).getSession().contains(entity);
	}

	/**
	 * Realiza o flush da sessao do hibernate
	 */
	public static void flush() {
		((HibernateSessionManager) Kernel.getTransactionModule()).getSession().flush();
	}

	public static Session getCurrentSession() {
		return ((HibernateSessionManager) Kernel.getTransactionModule()).getSession();
	}
}

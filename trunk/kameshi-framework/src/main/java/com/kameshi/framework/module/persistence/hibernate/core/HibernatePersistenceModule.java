package com.kameshi.framework.module.persistence.hibernate.core;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kameshi.framework.module.persistence.IPersistenceModule;
import com.kameshi.framework.module.persistence.hibernate.IHibernatePersistenceModule;
import com.kameshi.framework.module.persistence.support.OrderBy;
import com.kameshi.framework.module.persistence.support.OrderBy.Sort;

/**
 * 
 * Implementacao do modulo de persistencia do framwork
 * 
 */
public class HibernatePersistenceModule extends HibernateKernelModule implements IHibernatePersistenceModule {
	private static final long serialVersionUID = -4629146521355527311L;
	private static final Logger LOG = LoggerFactory.getLogger(HibernatePersistenceModule.class);

	/**
	 * Retorna uma lista da entidade Object de acordo com criterios
	 * especificados, seguindo as especificacoes de ordenacao fornecidas em
	 * <code>orderByList</code> atentando para a propriedade case sensitive de
	 * cada campo.
	 * 
	 * @param criteria
	 *            criterio(s) de busca
	 * @param orderByList
	 *            lista de criterios para a ordenacao
	 * @return o criteria modificado
	 */
	protected DetachedCriteria addOrderCriteria(DetachedCriteria criteria, OrderBy... orderByList) {
		for (OrderBy orderBy : orderByList) {
			if (Sort.ASCENDING.equals(orderBy.getSort())) {
				if (orderBy.isCaseSensitive()) {
					criteria.addOrder(Order.asc(orderBy.getName()));
				} else {
					criteria.addOrder(Order.asc(orderBy.getName()).ignoreCase());
				}
			} else if (Sort.DESCENDING.equals(orderBy.getSort())) {
				if (orderBy.isCaseSensitive()) {
					criteria.addOrder(Order.desc(orderBy.getName()));
				} else {
					criteria.addOrder(Order.desc(orderBy.getName()).ignoreCase());
				}
			}
		}
		return criteria;
	}

	/**
	 * Cria um criteria para a classe suportada pela implementacao do dao
	 * 
	 * @param clazz
	 *            a classe para o qual o criteria sera criado
	 * @return o criteria criado
	 */
	@SuppressWarnings("unchecked")
	private DetachedCriteria createDetachedCriteria(Class clazz) {
		return DetachedCriteria.forClass(clazz);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize() {
		LOG.info("starting hibernate persistence module");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Object> findAll(Class clazz) {
		return (List<Object>) super.loadAll(clazz);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param orderByList
	 *            {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Object> findAll(Class clazz, OrderBy... orderByList) {
		return findByCriteria(addOrderCriteria(createDetachedCriteria(clazz), orderByList));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param o
	 *            {@inheritDoc}
	 */
	@Override
	public void remove(Object o) {
		super.delete(o);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public Session getSession() {
		Session s = super.getSession();
		return s;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param clazz
	 *            {@inheritDoc}
	 * @param id
	 *            {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object getById(Class clazz, Serializable id) {
		return get(clazz.getName(), id);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param o
	 *            {@inheritDoc}
	 * 
	 */
	@Override
	public Object merge(Object o) {
		return super.merge(o.getClass().getName(), o);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param o
	 *            {@inheritDoc}
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findByExample(Object o) {
		return super.findByExample(o);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param o
	 *            {@inheritDoc}
	 * 
	 */
	@Override
	public void persist(Object o) {
		super.persist(o);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param o
	 *            {@inheritDoc}
	 * 
	 */
	@Override
	public void update(Object o) {
		super.update(o);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public IPersistenceModule getPersistenceModule() {
		return this;
	}
}

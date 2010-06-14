package com.framework.business.service.core;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.framework.business.service.IService;
import com.framework.entity.IEntity;
import com.framework.persistence.dao.IDao;
import com.framework.persistence.dao.dql.OrderBy;

public class Service<T extends IEntity<ID>, ID extends Serializable> implements IService<T, ID> {
	private IDao<T, ID> dao;

	@Required
	public void setDao(IDao<T, ID> dao) {
		this.dao = dao;
	}

	protected IDao<T, ID> getDao() {
		return dao;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 */
	@Override
	public void delete(T o) {
		dao.delete(o);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<T> findAll() {
		return dao.findAll();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 */
	@Override
	public T getById(ID id) {
		return dao.getById(id);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 */
	@Override
	public void persist(T o) {
		dao.persist(o);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 */
	@Override
	public void update(T o) {
		dao.update(o);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 */
	@Override
	public List<T> findAll(OrderBy... orderBy) {
		return dao.findAll(orderBy);
	}
}

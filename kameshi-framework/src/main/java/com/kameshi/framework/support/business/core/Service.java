package com.kameshi.framework.support.business.core;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.kameshi.framework.module.persistence.support.OrderBy;
import com.kameshi.framework.support.business.IService;
import com.kameshi.framework.support.entity.IEntity;
import com.kameshi.framework.support.persistence.IDao;

/**
 * Definição dos servicos que devem ser prestados por um componente da camada de
 * negócio que implementa os serviços prestados por um CRUD.
 * 
 * @param <T>
 *            O tipo especifico da entidade sobre a qual os servicos definidos
 *            nesta interface serão prestados.
 * @param <ID>
 *            O identificador unico da entidade.
 */
public class Service<T extends IEntity<ID>, ID extends Serializable> implements IService<T, ID> {
	private IDao<T, ID> dao;

	/**
	 * Constroi uma instancia desta classe sem um {@link IDao}. Este devera ser
	 * atribui­do previamente atraves do metodo <code>setDao()</code>.
	 */
	public Service() {
	}

	/**
	 * Construtor para ser utilizado sem o Spring ou com injecao via construtor.
	 * 
	 * @param dao
	 *            Data Access Object a ser utilizado pelo servico.
	 */
	public Service(IDao<T, ID> dao) {
		setDao(dao);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param o
	 *            {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Transactional
	@Override
	public T persist(T o) {
		return (T) dao.persist(o);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param o
	 *            {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Transactional
	@Override
	public T update(T o) {
		return (T) dao.update(o);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param o
	 *            {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Transactional
	@Override
	public T merge(T o) {
		return (T) dao.merge(o);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param id
	 *            {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public T getById(ID id) {
		return dao.getById(id);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param o
	 *            {@inheritDoc}
	 */
	@Transactional
	@Override
	public void remove(T o) {
		dao.remove(o);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public List<T> findAll() {
		return dao.findAll();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param ordering
	 *            {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public List<T> findAll(OrderBy... ordering) {
		return dao.findAll(ordering);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param o
	 *            {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public List<T> findByExample(T o) {
		return dao.findByExample(o);
	}

	/**
	 * Injeta o Data Acess Object a ser utilizado pelo servico.
	 * 
	 * @param dao
	 *            Data Access Object a ser utilizado pelo servico.
	 */
	public void setDao(IDao<T, ID> dao) {
		this.dao = dao;
	}

	/**
	 * Retorna o dao do service.
	 * 
	 * @return o dao do service.
	 */
	protected IDao<T, ID> getDao() {
		return dao;
	}
}
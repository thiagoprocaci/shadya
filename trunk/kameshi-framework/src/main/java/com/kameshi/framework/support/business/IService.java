package com.kameshi.framework.support.business;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.kameshi.framework.module.persistence.support.OrderBy;
import com.kameshi.framework.support.entity.IEntity;

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
public interface IService<T extends IEntity<ID>, ID extends Serializable> {
	/**
	 * Retorna uma instancia da entidade T.
	 * 
	 * @param id
	 *            identificador unico.
	 * @return a entidade correspondente ao id
	 */
	public T getById(ID id);

	/**
	 * Busca todos os registros a entidade T.
	 * 
	 * @return lista completa da entidade T
	 */
	public List<T> findAll();

	/**
	 * Procura todas as entidades de tipo T persistidas e as retorna conforme a
	 * ordem especificada.
	 * 
	 * @param orderByList
	 *            O criterio de ordenação a utilizar
	 * @return Lista de entidades de tipo T persistidas segundo a ordem
	 *         especificada
	 */
	public List<T> findAll(OrderBy... orderByList);

	/**
	 * Busca a lista da entidade T de acordo com criterios da propria entidade.
	 * 
	 * @param o
	 *            objeto exemplo
	 * @return lista da entidade T
	 */
	public List<T> findByExample(T o);

	/**
	 * Persiste (insert) uma instancia da entidade T.
	 * 
	 * @param t
	 *            objeto a ser persistido.
	 * @return referencia ao objeto persistido.
	 */
	@Transactional
	public T persist(T t);

	/**
	 * Atualiza(update) uma instancia da entidade T.
	 * 
	 * @param t
	 *            objeto a ser atualizado.
	 * @return referencia ao objeto atualizado.
	 */
	@Transactional
	public T update(T t);

	/**
	 * Atualiza ou Insere(save or update) uma instancia da entidade T.
	 * 
	 * @param t
	 *            objeto a ser atualizado ou inserido.
	 * @return referencia ao objeto atualizado ou inserido.
	 */
	@Transactional
	public T merge(T t);

	/**
	 * Remove (delete logico ou fisico) a entidade T.
	 * 
	 * @param t
	 *            objeto a ser removido.
	 */
	@Transactional
	public void remove(T t);
}
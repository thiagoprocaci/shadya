/*
 * IDao.java
 * ----------------------------------
 * Este arquivo pertence à Petrobras e não pode ser utilizado fora desta empresa sem prévia autorização.
 * ----------------------------------
 * Esta classe segue o padrao PE-1T0-00315
 */
package com.kameshi.framework.support.persistence;

import java.io.Serializable;
import java.util.List;

import com.kameshi.framework.module.persistence.IPersistenceModule;
import com.kameshi.framework.module.persistence.support.OrderBy;

/**
 * Interface DAO com os metodos mais comumente utilizados para persistencia.
 * 
 * @param <T>
 *            O tipo da entidade específica para a qual a implementação desta
 *            interface proverá os serviços.
 * @param <ID>
 *            O tipo do identificador único da entidade.
 */
public interface IDao<T, ID extends Serializable> {
	/**
	 * Retorna uma instancia da entidade T
	 * 
	 * @param id
	 *            O id da entidade
	 * @return objeto T de mesma chave
	 */
	public T getById(final ID id);

	/**
	 * Metodo que retorna a lista completa da entidade T
	 * 
	 * @return lista de todas as entidades
	 */
	public List<T> findAll();

	/**
	 * Retorna a lista completa da entidade T ordenada
	 * 
	 * @param orderByList
	 *            lista de criterios para a ordenacao
	 * @return lista completa da entidade T ordenada
	 */
	public List<T> findAll(OrderBy... orderByList);

	/**
	 * Metodo que retorna uma lista da entidade T de acordo com criterios da
	 * propria entidade (apenas parametros nao nulos serao levados em
	 * consideracao)
	 * 
	 * @param o
	 *            entidade usada como exemplo
	 * @return lista das entidades cujos valores dos campos correspondem ao
	 *         exemplo
	 */
	public List<T> findByExample(T o);

	/**
	 * Persiste (insert) uma instancia da entidade T. As implementações devem
	 * garantir que o mecanismo de persistência executará esta operação enquanto
	 * este método estiver em execução.
	 * 
	 * @param o
	 *            objeto a ser persistido
	 * @return referencia ao objeto persistido
	 */
	public T persist(T o);

	/**
	 * 'Atualiza' (update) uma instancia da entidade T. As implementações devem
	 * garantir que o mecanismo de persistência executará esta operação enquanto
	 * este método estiver em execução.
	 * 
	 * @param o
	 *            objeto a ser atualizado
	 * @return referencia ao objeto atualizado
	 */
	public T update(T o);

	/**
	 * 'Atualiza' (save or update) uma instancia da entidade T. As
	 * implementações devem garantir que o mecanismo de persistência executará
	 * esta operação enquanto este método estiver em execução.
	 * 
	 * @param o
	 *            objeto a ser atualizado ou inserido
	 * @return referencia ao objeto atualizado ou inserido
	 */
	public T merge(T o);

	/**
	 * Remove (delete logico ou fisico) a entidade T. As implementações devem
	 * garantir que o mecanismo de persistência executará esta operação enquanto
	 * este método estiver em execução.
	 * 
	 * @param o
	 *            objeto a ser removido
	 */
	public void remove(T o);

	/**
	 * Retorna o objeto "manager" do framework que de fato esta realizandoa
	 * persistencia
	 * 
	 * @return o objeto "manager" do framework que de fato esta realizandoa
	 *         persistencia
	 */
	public IPersistenceModule getPersistenceModule();
}

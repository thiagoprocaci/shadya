package com.kameshi.framework.module.persistence;

import java.io.Serializable;
import java.util.List;

import com.kameshi.framework.kernel.module.IKernelModule;
import com.kameshi.framework.module.persistence.support.OrderBy;

/**
 * 
 * Modulo de persistencia do framework
 * 
 */
public interface IPersistenceModule extends IKernelModule {
	/**
	 * Retorna uma instancia da entidade Object
	 * 
	 * @param clazz
	 *            a classe da entidade
	 * @param id
	 *            id da entidade
	 * @return objeto de mesma chave
	 */
	@SuppressWarnings("unchecked")
	public Object getById(Class clazz, Serializable id);

	/**
	 * Metodo que retorna a lista completa da entidade Object
	 * 
	 * @param clazz
	 *            a classe da entidade
	 * @return lista de todas as entidades
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findAll(Class clazz);

	/**
	 * Retorna a lista completa da entidade Object ordenada
	 * 
	 * @param clazz
	 *            a classe da entidade
	 * @param orderByList
	 *            lista de criterios para a ordenacao
	 * @return lista completa da entidade Object ordenada
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findAll(Class clazz, OrderBy... orderByList);

	/**
	 * Metodo que retorna uma lista da entidade Object de acordo com criterios
	 * da propria entidade (apenas parametros nao nulos serao levados em
	 * consideracao)
	 * 
	 * @param o
	 *            entidade usada como exemplo
	 * @return lista das entidades cujos valores dos campos correspondem ao
	 *         exemplo
	 */
	public List<Object> findByExample(Object o);

	/**
	 * Persiste (insert) uma instancia da entidade Object. As implementações
	 * devem garantir que o mecanismo de persistência executará esta operação
	 * enquanto este método estiver em execução.
	 * 
	 * @param o
	 *            objeto a ser persistido
	 */
	public void persist(Object o);

	/**
	 * 'Atualiza' (update) uma instancia da entidade Object. As implementações
	 * devem garantir que o mecanismo de persistência executará esta operação
	 * enquanto este método estiver em execução.
	 * 
	 * @param o
	 *            objeto a ser atualizado
	 */
	public void update(Object o);

	/**
	 * 'Atualiza' (save or update) uma instancia da entidade Object. As
	 * implementações devem garantir que o mecanismo de persistência executará
	 * esta operação enquanto este método estiver em execução.
	 * 
	 * @param o
	 *            objeto a ser atualizado ou inserido
	 * @return referencia ao objeto atualizado ou inserido
	 */
	public Object merge(Object o);

	/**
	 * Remove (delete logico ou fisico) a entidade Object. As implementações
	 * devem garantir que o mecanismo de persistência executará esta operação
	 * enquanto este método estiver em execução.
	 * 
	 * @param o
	 *            objeto a ser removido
	 */
	public void remove(Object o);

	/**
	 * Retorna o objeto "manager" do framework que de fato esta realizandoa
	 * persistencia
	 * 
	 * @return a instancia do modulo de persistencia
	 */
	public IPersistenceModule getPersistenceModule();
}

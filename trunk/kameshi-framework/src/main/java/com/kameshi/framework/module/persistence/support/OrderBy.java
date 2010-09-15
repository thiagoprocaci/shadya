/*
 * OrderBy.java
 * ----------------------------------
 * Este arquivo pertence à Petrobras e não pode ser utilizado fora desta empresa sem prévia autorização.
 * ----------------------------------
 * Esta classe segue o padrao PE-1T0-00315
 */
package com.kameshi.framework.module.persistence.support;

import java.io.Serializable;

/**
 * Retém informações que poderão ser usadas para ordenar buscas por objetos.
 */
public class OrderBy implements Serializable {
	private static final long serialVersionUID = 3160187263932074085L;
	private String name;
	private Sort sort;
	private boolean caseSensitive;

	/**
	 * Enumeracao que armazena os tipos de ordencao (ascendente, descendente).
	 */
	public enum Sort implements Serializable {
		ASCENDING, DESCENDING
	}

	/**
	 * Cria uma ordenação <b>ascendente</b> para o atributo fornecido como
	 * parâmetro. Assume case sensitive falso.
	 * 
	 * @param name
	 *            O nome do atributo pelo qual se deseja ordenar.
	 */
	public OrderBy(String name) {
		this(name, Sort.ASCENDING, false);
	}

	/**
	 * Instancia esta classe com um nome de atributo e uma direção de ordenação.
	 * Assume case sensitive falso.
	 * 
	 * @param name
	 *            O nome do atributo pelo qual se deseja ordenar.
	 * @param sort
	 *            A direção da ordenação. Vide {@link Sort}.
	 */
	public OrderBy(String name, Sort sort) {
		setName(name);
		setSort(sort);
		setCaseSensitive(false);
	}

	/**
	 * Instancia esta classe com um nome de atributo e uma direção de ordenação.
	 * 
	 * @param name
	 *            O nome do atributo pelo qual se deseja ordenar.
	 * @param sort
	 *            A direção da ordenação. Vide {@link Sort}.
	 * @param caseSensitive
	 *            informa se a ordenacao deve considerar a caixa.
	 */
	public OrderBy(String name, Sort sort, boolean caseSensitive) {
		setName(name);
		setSort(sort);
		setCaseSensitive(caseSensitive);
	}

	/**
	 * Retorna o nome do campo no banco de dados.
	 * 
	 * @return nome do campo
	 */
	public String getName() {
		return name;
	}

	/**
	 * Atribui o nome do campo do banco de dados. Não aceita valores nulos.
	 * 
	 * @param name
	 *            nome do campo no banco
	 */
	public void setName(String name) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Parameter 'name' can not be null or an empty String.");
		}
		this.name = name;
	}

	/**
	 * Retorna o tipo de ordenacao.
	 * 
	 * @see Sort
	 * @return tipo de ordenacao escolhido
	 */
	public Sort getSort() {
		return sort;
	}

	/**
	 * Atribui o tipo de ordenacao escolhido. Não aceita valores nulos.
	 * 
	 * @see Sort
	 * @param sort
	 *            tipo de ordenacao
	 */
	public void setSort(Sort sort) {
		if (sort == null) {
			throw new IllegalArgumentException("Attribute 'sort' can not be null");
		}
		this.sort = sort;
	}

	/**
	 * Verifica se um objeto é igual a este. Uma instância de OrderBy só é igual
	 * a outra se os atributos <code>name</code> e <code>sort</code> forem
	 * iguais.
	 * 
	 * @param obj
	 *            {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof OrderBy)) {
			return false;
		}
		OrderBy other = (OrderBy) obj;
		if (!name.equals(other.name)) {
			return false;
		}
		if (!sort.equals(other.sort)) {
			return false;
		}
		return true;
	}

	/**
	 * Baseado no nome do campo e na direção da ordenação.
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return name.hashCode() * sort.hashCode();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Sort:" + getName() + "/" + getSort().name();
	}

	public boolean isCaseSensitive() {
		return caseSensitive;
	}

	public void setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}
}

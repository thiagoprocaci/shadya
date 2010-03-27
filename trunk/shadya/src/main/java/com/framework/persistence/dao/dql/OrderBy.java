package com.framework.persistence.dao.dql;

/**
 * Classe que representa a ordenacao utilizada nas consultas
 */
public class OrderBy {
	private String name;
	private Sort sort;

	/**
	 * Construtor que receber os valores de inicializacao das propriedades
	 * 
	 * @param name
	 *            nome do campo da ordenacao
	 * @param sort
	 *            direcao da ordenacao
	 */
	public OrderBy(String name, Sort sort) {
		this.name = name;
		this.sort = sort;
	}

	/**
	 * Construtor que receber o valor de inicializacao da propriedade nome sem a
	 * direcao da ordenacao
	 * 
	 * @param name
	 *            nome do campo da ordenacao
	 */
	public OrderBy(String name) {
		this(name, Sort.ASCENDING);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	@Override
	public int hashCode() {
		return name.hashCode() + sort.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof OrderBy)) {
			return false;
		}
		if (((OrderBy) obj).name.trim().equalsIgnoreCase(this.name.trim())) {
			return true;
		}
		return false;
	}
}

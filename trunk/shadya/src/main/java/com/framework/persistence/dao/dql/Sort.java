package com.framework.persistence.dao.dql;

/**
 * Enum para a direcao de ordenacao
 */
public enum Sort {
	/** ascendente */
	ASCENDING("asc"),
	/** descendente */
	DESCENDING("desc");
	
	private String value;
	
	Sort(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

package com.framework.persistence.dao.dql.support;

/**
 * 
 * Entidade que representa o parametro de uma query
 * 
 */
public class ParameterQuery {
	@SuppressWarnings("unchecked")
	private Class type;
	private String parameterName;
	private Object value;

	@SuppressWarnings("unchecked")
	public ParameterQuery(Class type, String parameterName, Object value) {
		this.type = type;
		this.parameterName = parameterName;
		this.value = value;
	}

	public ParameterQuery() {
	}

	@SuppressWarnings("unchecked")
	public Class getType() {
		return type;
	}

	@SuppressWarnings("unchecked")
	public void setType(Class type) {
		this.type = type;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}

package com.framework.persistence.exception;

/**
 * 
 * Excecao lancada quando tenta-se executar uma query nula
 * 
 */
public class InvalidQueryException extends RuntimeException {
	private static final long serialVersionUID = 216621712199435162L;
	private static final String MESSAGE = "Query can not be null or empty";

	public InvalidQueryException() {
		super(MESSAGE);
	}
}

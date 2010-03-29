package com.framework.persistence.exception;

/**
 * 
 * Excecao lancada pelo Dao persistenceManager
 * 
 */
public class PersistenceException extends RuntimeException {
	private static final long serialVersionUID = -2356838988240622104L;

	public PersistenceException(Throwable cause) {
		super(cause);
	}
}

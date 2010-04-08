package com.framework.mail.exception;

/**
 * 
 * Mensagem de excecao de email
 * 
 */
public class MailException extends RuntimeException {
	private static final long serialVersionUID = 4883004131655018229L;

	public MailException(Throwable cause) {
		super(cause);
	}
}

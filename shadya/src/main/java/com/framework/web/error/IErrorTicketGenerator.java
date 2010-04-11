package com.framework.web.error;


/**
 * 
 * Gerador do ticket de erro
 * 
 */
public interface IErrorTicketGenerator  {
	/**
	 * Gera UID
	 * 
	 * @return o UID gerado
	 */
	String generateUID();
}

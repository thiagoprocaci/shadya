package com.framework.mail.factory;

import com.framework.mail.message.MailMessage;

/**
 * 
 * Factory de emails de excecao
 * 
 */
public interface IExceptionMailFactory {
	/**
	 * 
	 * @param exception
	 * @param url
	 * @param ticketCode
	 * @param insertTime
	 * @return Retorna mensagem de email de excecao
	 */
	public MailMessage create(Throwable exception, String url, String ticketCode, Long insertTime);
}

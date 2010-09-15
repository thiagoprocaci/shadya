package com.kameshi.framework.module.mail.factory;

import com.kameshi.framework.module.mail.message.MailMessage;

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

package com.framework.mail;

import com.framework.kernel.module.IKernelModule;
import com.framework.mail.message.MailMessage;

/**
 * 
 * Modulo de envio de email do framework
 * 
 */
public interface IMailModule extends IKernelModule {
	/**
	 * Envia email para um destinatio
	 * 
	 * @param msg
	 *            corpo do email
	 * @param subject
	 *            assunto do email
	 * @param to
	 *            destinatario do email (podem ser separados por ponto e
	 *            virgula)
	 * @param from
	 *            remetente do email
	 */
	public void sendMail(String msg, String subject, String to, String from);

	/**
	 * Envia email para varios destinatios
	 * 
	 * @param msg
	 *            corpo do email
	 * @param subject
	 *            assunto do email
	 * @param to
	 *            lista de destinatarios do email
	 * @param from
	 *            remetente do email
	 */
	public void sendMail(String msg, String subject, String[] to, String from);

	/**
	 * Envia email
	 * 
	 * @param mailMessage
	 */
	public void sendMail(MailMessage mailMessage);
}

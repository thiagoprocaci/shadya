package com.kameshi.framework.module.mail.factory.core;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Required;

import com.kameshi.framework.enviroment.EnvironmentInformation;
import com.kameshi.framework.kernel.Kernel;
import com.kameshi.framework.module.mail.factory.IExceptionMailFactory;
import com.kameshi.framework.module.mail.message.MailMessage;

/**
 * 
 * Factory de emails de excecao
 * 
 */
public class ExceptionMailFactory implements IExceptionMailFactory {
	private static final String SEPARATOR = " - ";
	private static final String HTML_LINE_BREAK = "<br />";
	private static final String USER_NAME = "USER NAME";	
	private static final String URL = "URL";
	private static final String TIME = "TIME";
	private static final String TICKET = "TICKET";
	private static final String SERVER = "SERVER";
	private static final String POINTS = ": ";
	private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
	private String to;
	private String from;
	private String subject;

	@Required
	public void setTo(String to) {
		this.to = to;
	}

	@Required
	public void setFrom(String from) {
		this.from = from;
	}

	@Required
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 */
	@Override
	public MailMessage create(Throwable exception, String url, String ticketCode, Long insertTime) {
		MailMessage mailMessage = new MailMessage();
		mailMessage.setFrom(from);
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(getMessage(exception, url, insertTime, ticketCode));
		return mailMessage;
	}

	/**
	 * Retorna mensagem do email de excecao
	 * 
	 * @param e
	 *            excecao a ser informada no email
	 * @param url
	 *            recurso que causou a excecao
	 * @param insertTime
	 *            o momento em que a excecao ocorreu
	 * 
	 * @return a mensagem montada com as informacoes da excecao
	 */
	private String getMessage(Throwable e, String url, Long insertTime, String ticketCode) {
		if (e == null || url == null || insertTime == null) {
			return "";
		}
		StringBuilder text = new StringBuilder();
		String userName = SEPARATOR;
		if (Kernel.getSecurityModule() != null && Kernel.getSecurityModule().getCurrentUserName() != null) {
			userName = Kernel.getSecurityModule().getCurrentUserName();
		}
		text.append(USER_NAME + POINTS + userName + HTML_LINE_BREAK);
		text.append(SERVER + POINTS + EnvironmentInformation.getServer() + HTML_LINE_BREAK);
		text.append(URL + POINTS + url + HTML_LINE_BREAK);
		text.append(TICKET + POINTS + ticketCode + HTML_LINE_BREAK);
		text.append(TIME + POINTS + new SimpleDateFormat(DATE_FORMAT).format(new Date(insertTime)) + HTML_LINE_BREAK + HTML_LINE_BREAK);
		while (e != null) {
			if (e.getClass() != null) {
				text.append(e.getClass() + HTML_LINE_BREAK);
			}
			if (e.getMessage() != null) {
				text.append("Message: " + e.getMessage() + HTML_LINE_BREAK);
			}
			if (e.getStackTrace() != null) {
				for (StackTraceElement stackTraceElement : e.getStackTrace()) {
					text.append(stackTraceElement);
					text.append(HTML_LINE_BREAK);
				}
			}
			text.append(HTML_LINE_BREAK);
			e = e.getCause();
		}
		return text.toString();
	}
}

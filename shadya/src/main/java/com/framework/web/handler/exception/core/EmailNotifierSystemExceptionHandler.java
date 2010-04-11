package com.framework.web.handler.exception.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Required;

import com.framework.kernel.Kernel;
import com.framework.mail.exception.MailException;
import com.framework.mail.factory.IExceptionMailFactory;
import com.framework.mail.message.MailMessage;
import com.framework.web.flow.IFlowManager;
import com.framework.web.handler.exception.ISystemExceptionHandler;
import com.framework.web.handler.exception.holder.ExceptionHolder;

/**
 * 
 * Notifica excecoes lancadas pela aplicacao por email
 * 
 */
public class EmailNotifierSystemExceptionHandler implements ISystemExceptionHandler {
	private static final Logger LOG = Logger.getLogger(EmailNotifierSystemExceptionHandler.class.getName());
	private static final double EXPIRACY = 180;
	private Map<String, ExceptionHolder> exceptions = new HashMap<String, ExceptionHolder>();
	private IExceptionMailFactory exceptionMailFactory;

	@Required
	public void setExceptionMailFactory(IExceptionMailFactory exceptionMailFactory) {
		this.exceptionMailFactory = exceptionMailFactory;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 */
	@Override
	public synchronized void handleException(IFlowManager flowManager, Throwable exception, String ticketCode) {
		LOG.info("Exception handle ");
		LOG.info("Sending exception mail: " + exception.getMessage());
		try {
			ExceptionHolder exceptionHolder = null;
			if (!exceptions.containsKey(ExceptionHolder.generateId(exception, flowManager.getRequest().getRequestURL().toString()))) {
				exceptionHolder = new ExceptionHolder(exception, flowManager.getRequest().getRequestURL().toString());
			} else if (exceptions.get(ExceptionHolder.generateId(exception, flowManager.getRequest().getRequestURL().toString())).getAge() > EXPIRACY) {
				exceptionHolder = new ExceptionHolder(exception, flowManager.getRequest().getRequestURL().toString());
			}
			if (exceptionHolder != null) {
				exceptions.put(exceptionHolder.getId(), exceptionHolder);
				MailMessage mail = exceptionMailFactory.create(exception, flowManager.getRequest().getRequestURL().toString(), ticketCode, exceptionHolder.getInsertTime());
				Kernel.getMailModule().sendMail(mail);
			}
		} catch (MailException e) {
			LOG.severe("Error on send exception mail");
			System.out.println();
		}
		removeExpiredExceptions();
	}

	/**
	 * Remove excecoes que ja foram lancadas a mais de um minuto
	 */
	private void removeExpiredExceptions() {
		List<ExceptionHolder> list = new ArrayList<ExceptionHolder>(exceptions.values());
		for (ExceptionHolder exceptionHolder : list) {
			if (exceptionHolder.getAge() > EXPIRACY) {
				exceptions.remove(exceptionHolder.getId());
			}
			if (exceptions.isEmpty()) {
				break;
			}
		}
	}
}

package com.kameshi.framework.module.web.handler.exception.core;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.kameshi.framework.kernel.Kernel;
import com.kameshi.framework.module.mail.exception.MailException;
import com.kameshi.framework.module.mail.factory.IExceptionMailFactory;
import com.kameshi.framework.module.mail.message.MailMessage;
import com.kameshi.framework.module.web.flow.IFlowManager;
import com.kameshi.framework.module.web.handler.exception.ISystemExceptionHandler;
import com.kameshi.framework.module.web.handler.exception.holder.ExceptionHolder;

/**
 * 
 * Notifica excecoes lancadas pela aplicacao por email
 * 
 */
public class EmailNotifierSystemExceptionHandler implements ISystemExceptionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(EmailNotifierSystemExceptionHandler.class);
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
			LOG.error("Error on send exception mail");
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

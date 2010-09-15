package com.kameshi.framework.module.web.handler.exception.core;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kameshi.framework.module.web.flow.IFlowManager;
import com.kameshi.framework.module.web.handler.exception.ISystemExceptionHandler;

/**
 * 
 * Notifica excecoes para o usuario imprimindo o stack trace. Esse handle deve
 * ser usado somente em ambiente de desenvolvimento.
 * 
 */
public class StackTraceNotifierSystemExceptionHandler implements ISystemExceptionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(StackTraceNotifierSystemExceptionHandler.class);

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 */
	@Override
	public void handleException(IFlowManager flowManager, Throwable exception, String ticketCode) {
		LOG.info("Exception handle ");
		LOG.info("Requested URL: " + flowManager.getRequest().getRequestURL().toString());
		exception.printStackTrace();
	}
}

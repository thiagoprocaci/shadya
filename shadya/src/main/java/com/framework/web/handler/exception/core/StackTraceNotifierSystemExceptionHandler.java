package com.framework.web.handler.exception.core;

import java.util.logging.Logger;

import com.framework.web.flow.IFlowManager;
import com.framework.web.handler.exception.ISystemExceptionHandler;

/**
 * 
 * Notifica excecoes para o usuario imprimindo o stack trace. Esse handle deve
 * ser usado somente em ambiente de desenvolvimento.
 * 
 */
public class StackTraceNotifierSystemExceptionHandler implements ISystemExceptionHandler {
	private static final Logger LOG = Logger.getLogger(StackTraceNotifierSystemExceptionHandler.class.getName());

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

	@Override
	public void initialize() {
		// do nothing
	}
}

package com.framework.web.handler.exception.core;

import com.framework.web.flow.IFlowManager;
import com.framework.web.handler.exception.ISystemExceptionHandler;

/**
 * 
 * Notifica excecoes para o usuario imprimindo o stack trace. Esse handle deve
 * ser usado somente em ambiente de desenvolvimento.
 * 
 */
public class StackTraceNotifierSystemExceptionHandler implements ISystemExceptionHandler {
	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 */
	@Override
	public void handleException(IFlowManager flowManager, Throwable exception, String ticketCode) {
		System.out.println("Exception handle " + this.getClass().getName());	
		System.out.println("Requested URL: " + flowManager.getRequest().getRequestURL().toString());
		exception.printStackTrace();
	}

	@Override
	public void initialize() {
		// do nothing
	}
}

package com.framework.web.handler.exception.core;

import java.io.Serializable;

import com.framework.web.flow.IFlowManager;
import com.framework.web.handler.exception.ISystemExceptionHandler;

/**
 * 
 * Notifica excecoes lancadas pela aplicacao por email
 * 
 */
public class EmailNotifierSystemExceptionHandler implements ISystemExceptionHandler, Serializable {
	private static final long serialVersionUID = -2264678952346094229L;

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 */
	@Override
	public void handleException(IFlowManager flowManager, Throwable exception, String ticketCode) {
		// TODO criar exception holder e enviar por email
		System.out.println("Enviando email com excecao " + exception.getMessage());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize() {
		// do nothing
	}
}

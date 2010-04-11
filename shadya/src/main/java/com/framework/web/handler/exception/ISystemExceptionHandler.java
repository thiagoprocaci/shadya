package com.framework.web.handler.exception;

import com.framework.web.flow.IFlowManager;

/**
 * Tratador de excecoes
 */
public interface ISystemExceptionHandler  {
	/**
	 * Processa o tratamento de uma excecao
	 * 
	 * @param action
	 *            o gerenciador de navegacao
	 * @param exception
	 *            a excecao a ser tratada
	 * @param ticketCode
	 *            codigo de erro
	 * 
	 */
	public void handleException(IFlowManager flowManager, Throwable exception, String ticketCode);
}

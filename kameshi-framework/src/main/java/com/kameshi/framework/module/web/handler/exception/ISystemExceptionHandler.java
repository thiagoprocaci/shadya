package com.kameshi.framework.module.web.handler.exception;

import com.kameshi.framework.module.web.flow.IFlowManager;

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

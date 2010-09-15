package com.kameshi.framework.module.web.barrier;

import com.kameshi.framework.module.web.flow.IFlowManager;

/**
 * Realiza a captura das excecoes e impedindo que a excecao chegue ao usuario
 */
public interface IExceptionBarrier {
	/**
	 * Realiza a notificacao de uma excecao de sistema
	 * 
	 * @param flowController
	 *            o gerenciador de navegacao
	 * @param exception
	 *            a excecao a ser tratada
	 */
	void notifySystemException(IFlowManager flowManager, Throwable exception);
}

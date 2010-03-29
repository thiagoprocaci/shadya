package com.framework.web.barrier;

import com.framework.kernel.module.IKernelModule;
import com.framework.web.flow.IFlowManager;

/**
 * Realiza a captura das excecoes e impedindo que a excecao chegue ao usuario
 */
public interface IExceptionBarrier extends IKernelModule {
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

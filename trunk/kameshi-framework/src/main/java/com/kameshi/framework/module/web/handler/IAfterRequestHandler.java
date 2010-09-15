package com.kameshi.framework.module.web.handler;

import com.kameshi.framework.module.web.flow.IFlowManager;

/**
 * 
 * Handler chamado antes do request
 * 
 */
public interface IAfterRequestHandler {
	/**
	 * Execucao do handler
	 * 
	 * @param flowManager
	 *            o gerenciador de navegacao
	 * 
	 * 
	 */
	void execute(IFlowManager flowManager);
}

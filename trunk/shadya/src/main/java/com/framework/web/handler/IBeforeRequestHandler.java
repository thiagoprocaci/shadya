package com.framework.web.handler;

import com.framework.web.flow.IFlowManager;

/**
 * 
 * Handler chamado antes do request
 * 
 */
public interface IBeforeRequestHandler {
	/**
	 * Execucao do handler
	 * 
	 * @param flowManager
	 *            o gerenciador de navegacao
	 * 
	 */
	void execute(IFlowManager flowManager);
}

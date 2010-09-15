package com.kameshi.framework.module.web;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.kameshi.framework.kernel.module.IKernelModule;
import com.kameshi.framework.module.web.barrier.IExceptionBarrier;
import com.kameshi.framework.module.web.flow.IFlowManager;

/**
 * 
 * Interface do modulo web do Framework
 * 
 */
public interface IWebModule extends IKernelModule {
	/**
	 * Esse metodo eh a entrada principal de yuma requisicao no framework Ele
	 * coordena as regras de injecao do beforeRequest, do afterRequest e da
	 * barreira de excecao
	 * 
	 * 
	 * 
	 * @param request
	 *            objeto request
	 * @param response
	 *            objeto response
	 * @param chain
	 *            cadeia de filtros
	 */
	void processRequest(ServletRequest request, ServletResponse response, FilterChain chain);

	/**
	 * Retorna o gerenciador de navegacao
	 * 
	 * @return o gerenciador de navegacao
	 */
	IFlowManager getFlowManager();

	/**
	 * Retorna a barreira de excecao
	 * 
	 * @return a barreira de excecao
	 */
	IExceptionBarrier getExceptionBarrier();
}

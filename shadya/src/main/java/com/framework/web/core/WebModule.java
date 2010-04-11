package com.framework.web.core;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Required;

import com.framework.web.IWebModule;
import com.framework.web.barrier.IExceptionBarrier;
import com.framework.web.flow.IFlowManager;
import com.framework.web.handler.IAfterRequestHandler;
import com.framework.web.handler.IBeforeRequestHandler;

/**
 * 
 * Implementacao do modulo web do Framework
 * 
 */
public class WebModule implements IWebModule {
	private static final Logger LOG = Logger.getLogger(WebModule.class.getName());
	private List<IBeforeRequestHandler> beforeRequestHandlers;
	private List<IAfterRequestHandler> afterRequestHandlers;
	private IExceptionBarrier exceptionBarrier;
	private IFlowManager flowManager;

	public void setBeforeRequestHandlers(List<IBeforeRequestHandler> beforeRequestHandlers) {
		this.beforeRequestHandlers = beforeRequestHandlers;
	}

	public void setAfterRequestHandlers(List<IAfterRequestHandler> afterRequestHandlers) {
		this.afterRequestHandlers = afterRequestHandlers;
	}

	@Required
	public void setExceptionBarrier(IExceptionBarrier exceptionBarrier) {
		this.exceptionBarrier = exceptionBarrier;
	}

	@Required
	public void setFlowManager(IFlowManager flowManager) {
		this.flowManager = flowManager;
	}

	@Override
	public IExceptionBarrier getExceptionBarrier() {
		return exceptionBarrier;
	}

	@Override
	public IFlowManager getFlowManager() {
		return flowManager;
	}

	@Override
	public void processRequest(ServletRequest request, ServletResponse response, FilterChain chain) {
		// Assumimos que o filtro web ira somente processar HttpRequests.
		if (request instanceof HttpServletRequest) {
			try {
				// deixamos o flowManager saber o que esta acontecendo
				flowManager.processRequest(request, response);
				// Chama todos os BeforeRequestHandlers registrados
				notifyBeforeRequestHandlers();
				// invoca aplicacao
				invokeApplication(chain, request, response);
			} catch (Throwable exception) {
				// Excecao capturada pela barreira
				exceptionBarrier.notifySystemException(getFlowManager(), exception);
			} finally {
				notifyAfterRequestHandlers();
			}
		} else {
			LOG.warning("This web module implementation works only for http requests");
		}
	}

	@Override
	public void initialize() {
		// do nothing
	}

	/**
	 * Check if we have an handler before request registrated, loop over the
	 * handler and execute them
	 * 
	 * @throws Exception
	 *             quando ocorre erro na execucao pelo handler
	 */
	protected void notifyBeforeRequestHandlers() {
		if (beforeRequestHandlers != null && !beforeRequestHandlers.isEmpty()) {
			for (IBeforeRequestHandler handler : beforeRequestHandlers) {
				handler.execute(getFlowManager());
			}
		}
	}

	/**
	 * Executado apos o tratamento pelo handler
	 */
	protected void notifyAfterRequestHandlers() {
		if (afterRequestHandlers != null && !afterRequestHandlers.isEmpty()) {
			for (IAfterRequestHandler handler : afterRequestHandlers) {
				try {
					// parte perigosa do framework.. Tudo injetado deveria ser
					// executado antes da barreira de excecao
					handler.execute(getFlowManager());
				} catch (Throwable exception) {
					// programador bacalhau... Bem, neste caso tudo que pode-se
					// fazer eh logar o erro
					LOG.severe("Error after the exception barrier " + exception.getMessage());
				}
			}
		}
	}

	/**
	 * Da sequencia ao encadeamento de filtros
	 * 
	 * @param chain
	 *            cadeia para o encadeamento de filtros
	 * @param request
	 *            objeto request
	 * @param response
	 *            objeto response
	 * @throws IOException
	 *             quando ocorre erro no processamento da cadeia
	 * @throws ServletException
	 *             quando ocorre erro no processamento da cadeia
	 * 
	 */
	private void invokeApplication(FilterChain chain, ServletRequest request, ServletResponse response) throws IOException, ServletException {
		if (!getFlowManager().isRequestCommitted()) {
			chain.doFilter(request, response);
		} else {
			LOG.warning("Response had been commited before application was invoked...!");
		}
	}
}

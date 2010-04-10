package com.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.framework.kernel.Kernel;

/**
 * 
 * Filtro responsavel por capturar qualquer excecao lancada pela aplicacao
 * 
 */
public class FrameworkFilter implements Filter {
	private static final String MSG_FRAMEWORK_INITILIZATION = "FrameworkInitializationException please see framework documentation";
	private FilterConfig filterConfig;

	/**
	 * {@inheritDoc}
	 * 
	 * @param filterConfig
	 *            {@inheritDoc}
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void destroy() {
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param request
	 *            {@inheritDoc}
	 * @param response
	 *            {@inheritDoc}
	 * @param chain
	 *            {@inheritDoc}
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			logDebugEnterMethod();
			// lugar onde tudo comeca e termina
			Kernel.getWebModule().processRequest(request, response, chain);
		} catch (Throwable exception) {
			// Nao confundir isto com barreira de excecao.
			// A Barreira ja existiu.
			// Isto eh literalmente um FrameworkInitializationException.
			// TODO colocar log aqui
			System.out.println(MSG_FRAMEWORK_INITILIZATION + " " + exception);
		} finally {
			logDebugLeaveMethod();
		}
	}

	/**
	 * Log da entrada no metodo
	 */
	private final void logDebugEnterMethod() {
		// TODO colocar log aqui
		System.out.println("Entering filter " + filterConfig.getFilterName() + ".");
	}

	/**
	 * Log da saida no metodo
	 */
	private final void logDebugLeaveMethod() {
		// TODO colocar log aqui
		System.out.println("Leaving filter " + filterConfig.getFilterName());
	}
}

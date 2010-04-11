package com.framework.web.handler.exception.core;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import com.framework.web.flow.IFlowManager;
import com.framework.web.handler.exception.ISystemExceptionHandler;

/**
 * 
 * Handler que coloca a excecao lancada e o ticket no request para manipulacao
 * no jsp
 * 
 */
public class PageNotifierSystemExceptionHandler implements ISystemExceptionHandler {
	private static final Logger LOG = Logger.getLogger(PageNotifierSystemExceptionHandler.class.getName());
	private static final String EXCEPTION = "exception";
	private static final String TICKET = "ticket";

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 * @param {@inheritDoc}
	 * @throws IOException
	 */
	@Override
	public void handleException(IFlowManager flowManager, Throwable exception, String ticketCode) {
		LOG.info("Exception handle ");
		if (!flowManager.isRequestCommitted()) {
			flowManager.setAttribute(EXCEPTION, exception);
			flowManager.setAttribute(TICKET, ticketCode);
			try {
				flowManager.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			} catch (IOException e) {
				LOG.severe("Internal server error: " + e.getMessage());
			}
		}
	}
}

package com.kameshi.framework.module.web.handler.exception.core;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kameshi.framework.module.web.flow.IFlowManager;
import com.kameshi.framework.module.web.handler.exception.ISystemExceptionHandler;

/**
 * 
 * Handler que coloca a excecao lancada e o ticket no request para manipulacao
 * no jsp
 * 
 */
public class PageNotifierSystemExceptionHandler implements ISystemExceptionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(PageNotifierSystemExceptionHandler.class);
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
				LOG.error("Internal server error: " + e.getMessage());
			}
		}
	}
}

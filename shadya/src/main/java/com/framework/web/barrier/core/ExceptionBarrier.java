package com.framework.web.barrier.core;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Required;

import com.framework.web.barrier.IExceptionBarrier;
import com.framework.web.error.IErrorTicketGenerator;
import com.framework.web.flow.IFlowManager;
import com.framework.web.handler.exception.ISystemExceptionHandler;

/**
 * Realiza a captura das excecoes e impedindo que a excecao chegue ao usuario
 */
public class ExceptionBarrier implements IExceptionBarrier {
	private static final long serialVersionUID = 7294175674809258L;
	private static final Logger LOG = Logger.getLogger(ExceptionBarrier.class.getName());
	private static final String LINEBREAK = "\n";
	private IErrorTicketGenerator ticketGenerator;
	private List<ISystemExceptionHandler> systemExceptionHandlers;

	@Required
	public void setTicketGenerator(IErrorTicketGenerator ticketGenerator) {
		this.ticketGenerator = ticketGenerator;
	}

	public void setSystemExceptionHandlers(List<ISystemExceptionHandler> systemExceptionHandlers) {
		this.systemExceptionHandlers = systemExceptionHandlers;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param {@inheritDoc}
	 */
	@Override
	public void notifySystemException(IFlowManager flowManager, Throwable exception) {
		LOG.info("Exception caught: " + exception.getMessage());
		String errorTicket = ticketGenerator.generateUID();
		LOG.info("Error ticket: " + errorTicket);
		if (systemExceptionHandlers != null && !systemExceptionHandlers.isEmpty()) {
			for (ISystemExceptionHandler handler : systemExceptionHandlers) {
				// o exception handler tambem pode lancar excecoes
				try {
					handler.handleException(flowManager, exception, errorTicket);
				} catch (Throwable e) {
					LOG.severe("Error on notify exception " + ticketGenerator.generateUID() + LINEBREAK + e.getMessage());
				}
			}
		}
	}
}

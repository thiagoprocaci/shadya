package com.framework.web.handler.core;

import com.framework.web.flow.IFlowManager;
import com.framework.web.handler.IBeforeRequestHandler;

/**
 * 
 * Handle que adiciona cabecalho no cache no request
 * 
 */
public class NoCacheBeforeRequestHandler implements IBeforeRequestHandler {
	private static final String CACHE_CONTROL = "Cache-Control";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(IFlowManager flowManager) {
		//TODO rever depois
		flowManager.setHeader("Expires", "Sat, 1 Jan 1998 12:00:00 GMT");
		flowManager.setHeader(CACHE_CONTROL, "no-store, no-cache, must-revalidate");
		flowManager.addHeader(CACHE_CONTROL, "post-check=0, pre-check=0");
		flowManager.setHeader("Pragma", "no-cache");
	}
}

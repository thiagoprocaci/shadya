package com.kameshi.framework.module.web.error.core;

import java.util.UUID;

import com.kameshi.framework.module.web.error.IErrorTicketGenerator;

/**
 * 
 * Gerador do ticket de erro
 * 
 */
public class TicketGenerator implements IErrorTicketGenerator {
	private static final long serialVersionUID = -4976634516088295818L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String generateUID() {
		return UUID.randomUUID().toString();
	}
}

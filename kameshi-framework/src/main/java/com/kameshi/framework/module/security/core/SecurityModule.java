package com.kameshi.framework.module.security.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kameshi.framework.module.security.ISecurityModule;
import com.kameshi.framework.module.security.support.IUser;

/**
 * 
 * Implementacao do modulo de seguranca do framwork
 * 
 */
public class SecurityModule implements ISecurityModule {
	private static final Logger LOG = LoggerFactory.getLogger(SecurityModule.class);

	// TODO terminar a intergracao com o spring security depois
	@Override
	public IUser getCurrentUser() {
		return null;
	}

	@Override
	public String getCurrentUserName() {
		return null;
	}

	@Override
	public void initialize() {
		LOG.info("starting security module");
	}
}

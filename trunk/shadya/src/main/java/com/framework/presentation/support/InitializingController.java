package com.framework.presentation.support;

import java.io.Serializable;

import org.springframework.beans.factory.InitializingBean;

/**
 * 
 * Classe encapusulado de InitializingBean. Ele deve ser extendida quando um
 * controller necessitar de acoes durante sua criacao.
 * 
 */
public abstract class InitializingController implements InitializingBean, Serializable {
	private static final long serialVersionUID = -6588083752223322738L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void afterPropertiesSet() throws Exception {
		onCreate();
	}

	/**
	 * Acoes realizadas durante a criacao de controller
	 * 
	 */
	public abstract void onCreate();
}

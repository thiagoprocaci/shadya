package com.kameshi.framework.module.security;

import com.kameshi.framework.kernel.module.IKernelModule;
import com.kameshi.framework.module.security.support.IUser;

/**
 * 
 * Modulo de seguranca do framework
 * 
 */
public interface ISecurityModule extends IKernelModule {
	// esse modulo sera mais simples devido a intergracao com o spring security
	// para a seguranca do sistema.
	/**
	 *@return Retorna usuario corrente da aplicacao
	 */
	IUser getCurrentUser();

	/**
	 * 
	 * @return Retorna o user name do usuario corrente da aplicacao.
	 */
	String getCurrentUserName();
}
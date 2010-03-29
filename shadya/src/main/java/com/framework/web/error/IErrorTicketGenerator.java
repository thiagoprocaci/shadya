package com.framework.web.error;

import com.framework.kernel.module.IKernelModule;

/**
 * 
 * Gerador do ticket de erro
 * 
 */
public interface IErrorTicketGenerator extends IKernelModule {
	/**
	 * Gera UID
	 * 
	 * @return o UID gerado
	 */
	String generateUID();
}

package com.kameshi.framework.kernel.module;

/**
 * Interface que define um modulo do Kernel Todo modulo/bean tem seu initilize
 * invocado apos a criacao deste pelo container de injecao de dependecia (apos
 * contrutor).
 */
public interface IKernelModule {
	/**
	 * Inicializa o modulo
	 */
	void initialize();
}

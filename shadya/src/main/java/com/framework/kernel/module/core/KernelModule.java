package com.framework.kernel.module.core;

import java.io.Serializable;

import org.springframework.beans.factory.InitializingBean;

import com.framework.kernel.module.IKernelModule;

/**
 * Implementacao abstrata de modulo do Kernel
 */
public abstract class KernelModule implements InitializingBean, IKernelModule, Serializable {
	private static final long serialVersionUID = 8676028210935141520L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		initialize();
	}
}
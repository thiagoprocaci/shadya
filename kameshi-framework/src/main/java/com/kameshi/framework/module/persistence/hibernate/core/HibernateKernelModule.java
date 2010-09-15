package com.kameshi.framework.module.persistence.hibernate.core;

import java.io.Serializable;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.kameshi.framework.kernel.module.IKernelModule;

/**
 * Implementacao abstrata de modulo do Kernel voltada para o hibernate
 */
public abstract class HibernateKernelModule extends HibernateTemplate implements InitializingBean, IKernelModule, Serializable {
	private static final long serialVersionUID = -4502456822836859978L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void afterPropertiesSet() {
		initialize();
	}
}

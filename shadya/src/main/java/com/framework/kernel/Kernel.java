package com.framework.kernel;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Classe utilitaria central que prove acesso as interfaces do framework que
 * contem os principais servicos oferecidos
 */
public class Kernel implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Kernel.applicationContext = applicationContext;
	}

	/**
	 * Metodo executado durante a inicializacao do Kernel
	 */
	public void initialize() {
	}

	/**
	 * 
	 * @param beanId
	 * @return Retorna bean do container do spring
	 */
	public static Object getBean(String beanId) {
		return Kernel.applicationContext.getBean(beanId);
	}
}

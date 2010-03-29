package com.framework.kernel;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.framework.kernel.module.IKernelModule;
import com.framework.kernel.module.KernelModules;
import com.framework.persistence.IPersistenceModule;
import com.framework.web.IWebModule;

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
		System.out.println("inicializacao framework");
	}

	/**
	 * Retorna um modulo existente em KernelModules (enum)
	 * 
	 * @param module
	 *            modulo a ser retornado
	 * @return modulo presente no contexto correspondente ao modulo informado
	 */
	public static IKernelModule getModule(KernelModules module) {
		return (IKernelModule) Kernel.applicationContext.getBean(module.value());
	}

	/**
	 * 
	 * @param beanId
	 * @return Retorna bean do container do spring
	 */
	public static Object getBean(String beanId) {
		return Kernel.applicationContext.getBean(beanId);
	}

	/**
	 * 
	 * @return Retorna modulo web do framework
	 */
	public static IWebModule getWebModule() {
		return (IWebModule) Kernel.getModule(KernelModules.WEB_MODULE);
	}

	/**
	 * 
	 * @return Retorna modulo de persistencia do framework
	 */
	public static IPersistenceModule persistenceModule() {
		return (IPersistenceModule) Kernel.getModule(KernelModules.PERSISTENCE_MODULE);
	}
}

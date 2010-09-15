package com.kameshi.framework.kernel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.kameshi.framework.kernel.module.IKernelModule;
import com.kameshi.framework.kernel.module.KernelModules;
import com.kameshi.framework.module.mail.IMailModule;
import com.kameshi.framework.module.messenger.IMessengerModule;
import com.kameshi.framework.module.persistence.IPersistenceModule;
import com.kameshi.framework.module.security.ISecurityModule;
import com.kameshi.framework.module.transaction.ITransactionModule;
import com.kameshi.framework.module.web.IWebModule;

/**
 * Classe utilitaria central que prove acesso as interfaces do framework que
 * contem os principais servicos oferecidos
 */
public class Kernel implements ApplicationContextAware {
	private static final Logger LOG = LoggerFactory.getLogger(Kernel.class);
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		Kernel.applicationContext = applicationContext;
	}

	/**
	 * Metodo executado durante a inicializacao do Kernel
	 */
	public void initialize() {
		LOG.info("--- starting framework ---");
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
	public static IPersistenceModule getPersistenceModule() {
		return (IPersistenceModule) Kernel.getModule(KernelModules.PERSISTENCE_MODULE);
	}

	/**
	 * 
	 * @return Retorna modulo de envio de email do sistema
	 */
	public static IMailModule getMailModule() {
		return (IMailModule) Kernel.getModule(KernelModules.MAIL_MODULE);
	}

	/**
	 * 
	 * @return Retorna modulo de mensageria do framework
	 */
	public static IMessengerModule getMessengerModule() {
		return (IMessengerModule) Kernel.getModule(KernelModules.MESSENGER_MODULE);
	}

	/**
	 * 
	 * @return Retorna modulo de seguranca do framework
	 */
	public static ISecurityModule getSecurityModule() {
		return (ISecurityModule) Kernel.getModule(KernelModules.SECURITY_MODULE);
	}

	/**
	 * 
	 * @return Retorna modulo de transacao do framework
	 */
	public static ITransactionModule getTransactionModule() {
		return (ITransactionModule) Kernel.getModule(KernelModules.TRANSACTION_MODULE);
	}
}

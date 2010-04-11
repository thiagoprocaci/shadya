package com.framework.kernel;

import java.util.logging.Logger;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.framework.kernel.module.IKernelModule;
import com.framework.kernel.module.KernelModules;
import com.framework.mail.IMailModule;
import com.framework.messenger.IMessengerModule;
import com.framework.persistence.IPersistenceModule;
import com.framework.security.IUser;
import com.framework.web.IWebModule;

/**
 * Classe utilitaria central que prove acesso as interfaces do framework que
 * contem os principais servicos oferecidos
 */
public class Kernel implements ApplicationContextAware {
	private static final Logger LOG = Logger.getLogger(Kernel.class.getName());
	private static ApplicationContext applicationContext;
	private static String CURRENT_USER = "currentUser";

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
	 * @return Retorna usuario corrente da aplicacao
	 */
	public static IUser getCurrentUser() {
		return null;
		// return (IUser) Kernel.getBean(CURRENT_USER);
	}

	/**
	 * Seta usuario corrente da aplicacao
	 * 
	 * @param user
	 */
	public static void setCurrentUser(IUser user) {
		IUser currentUser = (IUser) Kernel.getBean(CURRENT_USER);
		currentUser.setName(user.getName());
		currentUser.setUsername(user.getUsername());
	}
}

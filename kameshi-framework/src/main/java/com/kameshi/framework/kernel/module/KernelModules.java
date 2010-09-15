package com.kameshi.framework.kernel.module;

/**
 * 
 * Modulos do Kernel
 * 
 */
public enum KernelModules {
	MESSENGER_MODULE("messengerModule"),
	PERSISTENCE_MODULE("persistenceModule"),
	MAIL_MODULE("mailModule"),
	SECURITY_MODULE("securityModule"),
	TRANSACTION_MODULE("transactionModule"),
	WEB_MODULE("webModule");
	private String value;

	KernelModules(String value) {
		this.value = value;
	}

	/**
	 * 
	 * @return Retorna value
	 */
	public String value() {
		return value;
	}
}

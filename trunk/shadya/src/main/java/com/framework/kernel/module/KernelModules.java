package com.framework.kernel.module;

/**
 * 
 * Modulos do Kernel
 * 
 */
public enum KernelModules {
	MESSENGER_MODULE("messengerModule"),
	PERSISTENCE_MODULE("persistenceModule"),
	MAIL_MODULE("mailModule"),
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

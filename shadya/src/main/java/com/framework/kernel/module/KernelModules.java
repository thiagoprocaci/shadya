package com.framework.kernel.module;

/**
 * 
 * Modulos do Kernel
 * 
 */
public enum KernelModules {
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

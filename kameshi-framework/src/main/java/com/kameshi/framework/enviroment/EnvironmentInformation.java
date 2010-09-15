package com.kameshi.framework.enviroment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Classe com informacoes do ambiente que a aplicacao roda.
 * 
 */
public class EnvironmentInformation {
	private EnvironmentInformation() {
		// construtor privado.
		// acesso somente por metodos estaticos.
	}

	/**
	 * @return Retorna servidor
	 */
	public static String getServer() {
		try {
			InetAddress i = java.net.InetAddress.getLocalHost();
			return i.toString();
		} catch (UnknownHostException e) {
			return "UnknownHost";
		}
	}
}

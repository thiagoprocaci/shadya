package com.framework.common;

/**
 * 
 * Classe helper para facilitar operacoes com strings
 * 
 */
public final class StringUtil {
	/**
	 * 
	 * @param string
	 * @return Retorna string com a primeira letra mauiscula
	 */
	public static String toUpperFirst(String string) {
		return (string != null ? string.replaceFirst(String.valueOf(string.charAt(0)), String.valueOf(string.charAt(0)).toUpperCase()) : null);
	}
}

package com.kameshi.framework.common;

/**
 * 
 * Classe helper para facilitar operacoes com strings
 * 
 */
public final class StringUtil {
	private StringUtil() {
		// construtor privado pq nao ha necessidade de criar instancias dessa
		// classe.
		// todos os metodos sao estaticos
	}

	/**
	 * 
	 * @param string
	 * @return Retorna string com a primeira letra mauiscula
	 */
	public static String toUpperFirst(String string) {
		return (string != null ? string.replaceFirst(String.valueOf(string.charAt(0)), String.valueOf(string.charAt(0)).toUpperCase()) : null);
	}
}

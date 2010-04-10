package com.framework.messenger.support;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Classe utilitaria para recuperar arquivo de mensagens
 */
public class MessageBundleUtils {
	private static final String ERROR_PREFIX = "key ";
	private static final String ERROR_SUFIX = " not found";

	/**
	 * Metodo para auxiliar a captura do arquivo de mensagens.
	 * 
	 * @param defaultObject
	 *            objeto usado para recuperar o classloader
	 * @return o classLoader do objeto
	 */
	private static ClassLoader getCurrentClassLoader(Object defaultObject) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader == null) {
			loader = defaultObject.getClass().getClassLoader();
		}
		return loader;
	}

	/**
	 * Recupera uma mensagem atraves da chave e do bundle
	 * 
	 * @param bundleName
	 *            nome do arquivo de bundle a ser utilizado
	 * @param key
	 *            Chave de uma mensagem presente no arquivo de mensagens
	 * @return Mensagem vinculada a chave passada por paramentro
	 */
	public static String getMessageResourceString(String key, String bundleName) {
		String text = null;
		ResourceBundle bundle = ResourceBundle.getBundle(bundleName);
		try {
			text = bundle.getString(key);
		} catch (MissingResourceException e) {
			text = ERROR_PREFIX + key + ERROR_SUFIX;
		}
		return text;
	}

	/**
	 * Recupera uma mensagem atraves da chave e ainda acrescenta os parametros
	 * 
	 * @param key
	 *            Chave de uma mensagem presente no arquivo de mensagens
	 * @param params
	 *            Array de parametros para acrescetado na mensagem
	 * @return Mesagem vinculada a chave passada por paramentro
	 */
	public static String getMessageResourceString(String key, String bundleName, Object params[]) {
		return MessageBundleUtils.getMessageResourceString(key, bundleName, null, params);
	}

	/**
	 * Recupera uma mensagem atraves da chave e ainda acrescenta os parametros
	 * 
	 * @param bundleName
	 *            nome do arquivo de bundle a ser utilizado
	 * @param key
	 *            Chave de uma mensagem presente no arquivo de mensagens
	 * @param locale
	 *            locale da mensagem
	 * @param params
	 *            Array de parametros para acrescetado na mensagem
	 * 
	 * @return Mesagem vinculada a chave passada por paramentro
	 */
	public static String getMessageResourceString(String key, String bundleName, Locale locale, Object params[]) {
		String text = null;
		if (locale == null) {
			locale = Locale.getDefault();
		}
		if (params == null) {
			params = new Object[0];
		}
		ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale, getCurrentClassLoader(params));
		try {
			text = bundle.getString(key);
		} catch (MissingResourceException e) {
			text = ERROR_PREFIX + key + ERROR_SUFIX;
		}
		if (params != null && params.length > 0) {
			MessageFormat mf = new MessageFormat(text, locale);
			text = mf.format(params, new StringBuffer(), null).toString();
		}
		return text;
	}
}

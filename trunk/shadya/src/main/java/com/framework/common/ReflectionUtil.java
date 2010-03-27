package com.framework.common;

import java.lang.reflect.Field;

/**
 * 
 * Classe helper para operacao com reflections
 * 
 */
public final class ReflectionUtil {
	private static final String GET = "get";
	private static final String SET = "set";

	/**
	 * 
	 * @param field
	 * @return Retorna o nome do getter de um campo
	 */
	public static String getGetterMethodName(Field field) {
		return GET + StringUtil.toUpperFirst(field.getName());
	}

	/**
	 * 
	 * @param field
	 * @return Retorna o nome do setter de um campo
	 */
	public static String getSetterMethodName(Field field) {
		return SET + StringUtil.toUpperFirst(field.getName());
	}
}

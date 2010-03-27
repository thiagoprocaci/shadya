package com.framework.persistence.dao.dql.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.Query;

import com.framework.persistence.dao.dql.OrderBy;

/**
 * 
 * Classe helper para facilitar a manipulacao de queries
 * 
 */
public final class QueryHelper {
	/**
	 * Executa query e retorna resultado em uma lista
	 * 
	 * @param query
	 * @param parameters
	 * @return Retorna resultado da query
	 */
	@SuppressWarnings("unchecked")
	public static List<Object> getQueryResult(Query query, Map<String, Object> parameters) {
		List<Object> list = new ArrayList<Object>();
		List<Object> results = null;
		if (parameters == null || parameters.isEmpty()) {
			results = (List<Object>) query.execute();
		} else {
			results = (List<Object>) query.executeWithMap(parameters);
		}
		if (results != null) {
			for (Object object : results) {
				list.add(object);
			}
		}
		return list;
	}

	/**
	 * Executa query e retorna resultado em uma lista
	 * 
	 * @param query
	 * @param parameters
	 * @return Retorna resultado da query
	 */
	public static List<Object> getQueryResult(Query query) {
		return getQueryResult(query, null);
	}

	/**
	 * 
	 * @param orderByList
	 * @return Retorna orderBy como string
	 */
	public static String getOrderByAsString(OrderBy... orderByList) {
		StringBuilder clause = null;
		if (orderByList != null && orderByList.length > 0) {
			clause = new StringBuilder();
			for (int i = 0; i < orderByList.length; i++) {
				clause.append(orderByList[i].getName() + " " + orderByList[i].getSort().getValue());
				if (i != orderByList.length - 1) {
					clause.append(", ");
				}
			}
		}
		return clause == null ? null : clause.toString();
	}

	/**
	 * 
	 * @param parameterQueryList
	 * @return Retorna parametros como string
	 */
	public static String getParametersAsString(ParameterQuery... parameterQueryList) {
		StringBuilder parameters = null;
		if (parameterQueryList != null && parameterQueryList.length > 0) {
			parameters = new StringBuilder();
			for (int i = 0; i < parameterQueryList.length; i++) {
				parameters.append(parameterQueryList[i].getType().getName() + " " + parameterQueryList[i].getParameterName());
				if (i != parameterQueryList.length - 1) {
					parameters.append(", ");
				}
			}
		}
		return parameters == null ? null : parameters.toString();
	}

	/**
	 * 
	 * @param parameterQueryList
	 * @return Retorna parametros como um map
	 */
	public static Map<String, Object> getParametersAsMap(ParameterQuery... parameterQueryList) {
		Map<String, Object> parameters = null;
		if (parameterQueryList != null && parameterQueryList.length > 0) {
			parameters = new HashMap<String, Object>();
			for (int i = 0; i < parameterQueryList.length; i++) {
				parameters.put(parameterQueryList[i].getParameterName(), parameterQueryList[i].getValue());
			}
		}
		return parameters;
	}
}

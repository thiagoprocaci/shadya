package com.framework.persistence.dao.dql.support;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Query;

import com.framework.persistence.dao.dql.OrderBy;

/**
 * 
 * Classe helper para facilitar a construcao de queries
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
	public static List<Object> getQueryResult(Query query, Object... parameters) {
		List<Object> list = new ArrayList<Object>();
		List<Object> results = null;
		if (parameters == null || parameters.length <= 0) {
			results = (List<Object>) query.execute();
		} else {
			results = (List<Object>) query.executeWithArray(parameters);
		}
		if (results != null) {
			for (Object object : results) {
				list.add(object);
			}
		}
		return list;
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
}

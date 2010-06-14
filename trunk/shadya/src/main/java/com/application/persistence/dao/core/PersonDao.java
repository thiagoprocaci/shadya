package com.application.persistence.dao.core;

import java.util.List;

import com.application.model.Person;
import com.application.persistence.dao.IPersonDao;
import com.framework.persistence.dao.core.GoogleDao;
import com.framework.persistence.dao.dql.support.ParameterQuery;

public class PersonDao extends GoogleDao<Person, Long> implements IPersonDao {

	@Override
	public List<Person> findByName(String name) {
		return executeQuery("name == nome", new ParameterQuery(String.class, "nome", name));
	}
}

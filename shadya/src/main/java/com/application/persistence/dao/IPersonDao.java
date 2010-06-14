package com.application.persistence.dao;

import java.util.List;

import com.application.model.Person;
import com.framework.persistence.dao.IDao;

public interface IPersonDao extends IDao<Person, Long> {
	List<Person> findByName(String name);
}

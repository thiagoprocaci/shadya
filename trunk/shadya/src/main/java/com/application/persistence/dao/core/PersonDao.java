package com.application.persistence.dao.core;

import com.application.model.Person;
import com.application.persistence.dao.IPersonDao;
import com.framework.persistence.dao.core.GoogleDao;

public class PersonDao extends GoogleDao<Person, Long> implements IPersonDao {
}

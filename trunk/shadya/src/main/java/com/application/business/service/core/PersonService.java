package com.application.business.service.core;

import java.util.List;

import com.application.business.service.IPersonService;
import com.application.model.Person;
import com.application.persistence.dao.IPersonDao;
import com.framework.business.service.core.Service;

public class PersonService extends Service<Person, Long> implements IPersonService {
	@Override
	protected IPersonDao getDao() {
		return (IPersonDao) super.getDao();
	}

	@Override
	public List<Person> findByName(String name) {		
		return getDao().findByName(name);
	}
}

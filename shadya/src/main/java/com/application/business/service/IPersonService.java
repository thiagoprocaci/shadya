package com.application.business.service;

import java.util.List;

import com.application.model.Person;
import com.framework.business.service.IService;

public interface IPersonService extends IService<Person, Long> {
	
	List<Person> findByName(String name);
}

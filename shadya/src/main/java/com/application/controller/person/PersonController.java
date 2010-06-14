package com.application.controller.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.application.business.service.IPersonService;
import com.application.model.Person;
import com.framework.presentation.support.InitializingController;

/**
 * 
 * Controller de pessoa
 * 
 */
public class PersonController extends InitializingController {
	private static final long serialVersionUID = 5408771229655841584L;
	private IPersonService personService;
	private List<Person> personList;
	private Person person;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Person> getPersonList() {		
		return personList;
	}

	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}

	@Required
	public void setPersonService(IPersonService personService) {
		this.personService = personService;
	}

	public void save() {
		if (person != null) {
			if (person.isNew()) {
				personService.persist(person);
			} else {
				personService.update(person);
			}
		}
	}

	@Override
	public void onCreate() {
		person = new Person();
	}
}

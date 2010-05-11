package com.application.controller.person;

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
	@SuppressWarnings("unused")
	private IPersonService personService;
	private Person person;

	

	public Person getPerson() {
		return person;
	}



	public void setPerson(Person person) {
		this.person = person;
	}



	@Required
	public void setPersonService(IPersonService personService) {
		this.personService = personService;
	}



	@Override
	public void onCreate() {
		System.out.println("Controller");
		
	}

}

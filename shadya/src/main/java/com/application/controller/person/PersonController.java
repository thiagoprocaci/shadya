package com.application.controller.person;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.webflow.action.FormAction;
import org.springframework.webflow.execution.ScopeType;

import com.application.business.service.IPersonService;
import com.application.model.Person;

/**
 * 
 * Controller de pessoa
 * 
 */
public class PersonController extends FormAction {
	private static final long serialVersionUID = 5408771229655841584L;
	@SuppressWarnings("unused")
	private IPersonService personService;
	private String name = "teste";

	public String getName() {
		return name;
	}

	@Required
	public void setPersonService(IPersonService personService) {
		this.personService = personService;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
		setFormObjectClass(Person.class);
		setFormObjectName("person");
		setFormObjectScope(ScopeType.FLOW);
	}
}

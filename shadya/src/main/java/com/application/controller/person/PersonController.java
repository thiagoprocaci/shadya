package com.application.controller.person;

import org.springframework.beans.factory.annotation.Required;

import com.application.business.service.IPersonService;
import com.framework.presentation.support.InitializingController;

/**
 * 
 * Controller de pessoa
 * 
 */
public class PersonController extends InitializingController {
	private static final long serialVersionUID = 5408771229655841584L;
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
	public void onCreate() {
		System.out.println("onCreate");
	}
}

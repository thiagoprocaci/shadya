package com.application;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.business.service.IPersonService;
import com.application.model.Person;
import com.application.persistence.dao.IPersonDao;
import com.framework.kernel.Kernel;
import com.framework.persistence.dao.dql.support.ParameterQuery;

@SuppressWarnings("serial")
public class ShadyaServlet extends HttpServlet {
	private IPersonService personService = (IPersonService) Kernel.getBean("personService");
	private IPersonDao personDao = (IPersonDao) Kernel.getBean("personDao");

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
		List<Person> list = personDao.executeQuery("id == identificador || name == n", new ParameterQuery(Long.class,"identificador",12L),new ParameterQuery(String.class,"n","name2"));
		for (Person person : list) {
			System.out.println(person.getId());
			System.out.println(person.getName());
		}
	}
}

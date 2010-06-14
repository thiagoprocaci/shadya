package com.application;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.model.Person;
import com.application.persistence.dao.IPersonDao;
import com.framework.kernel.Kernel;
import com.framework.persistence.dao.dql.OrderBy;
import com.framework.persistence.dao.dql.Sort;
import com.framework.persistence.dao.dql.support.ParameterQuery;

@SuppressWarnings("serial")
public class ShadyaServlet extends HttpServlet {
	private IPersonDao personDao;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
		Kernel.getMessengerModule().addSuccessMessage("mensagemTeste", new Object[] { "ola", "oi" });
		resp.getWriter().println(Kernel.getMessengerModule().getSuccessMessages());
		personDao.findAll();
		List<Person> list = personDao.executeQuery("id == identificador && name == nome", new ParameterQuery(Long.class, "identificador", 12L), new ParameterQuery(String.class, "nome", "name"));
		System.out.println("Query 1: id == identificador && name == nome");
		resp.getWriter().println("Query 1: id == identificador && name == nome");
		for (Person person : list) {
			System.out.println(person.getId() + " " + person.getName());
			resp.getWriter().println(person.getId() + " " + person.getName());
		}
		list = personDao.executeQuery("id == 12");
		System.out.println("Query 2: id == 12");
		resp.getWriter().println("Query 2: id == 12");
		for (Person person : list) {
			System.out.println(person.getId() + " " + person.getName());
			resp.getWriter().println(person.getId() + " " + person.getName());
		}
		list = personDao.findAll();
		System.out.println("Query 3: findAll");
		resp.getWriter().println("Query 3: findAll");
		for (Person person : list) {
			System.out.println(person.getId() + " " + person.getName());
			resp.getWriter().println(person.getId() + " " + person.getName());
		}
		list = personDao.findAll(new OrderBy("id", Sort.DESCENDING));
		System.out.println("Query 4: findAll(OrderBy)");
		resp.getWriter().println("Query 4: findAll(OrderBy)");
		for (Person person : list) {
			System.out.println(person.getId() + " " + person.getName());
			resp.getWriter().println(person.getId() + " " + person.getName());
		}
	}
}

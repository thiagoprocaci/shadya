/*
 * IHibernatePersistenceModule.java
 * ----------------------------------
 * Este arquivo pertence à Petrobras e não pode ser utilizado fora desta empresa sem prévia autorização.
 * ----------------------------------
 * Esta classe segue o padrao PE-1T0-00315
 */
package com.kameshi.framework.module.persistence.hibernate;

import org.hibernate.Session;

import com.kameshi.framework.module.persistence.IPersistenceModule;

/**
 * Modulo de persistencia especifico do Hibernate. Ele herda do modulo geral de
 * persistencia do framework.
 */
public interface IHibernatePersistenceModule extends IPersistenceModule {
	/**
	 * Retorna a sessao do hibernate
	 * 
	 * @return a sessao
	 */
	Session getSession();
}

/*
 * IHibernatePersistenceModule.java
 * ----------------------------------
 * Este arquivo pertence � Petrobras e n�o pode ser utilizado fora desta empresa sem pr�via autoriza��o.
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

package com.gmail.genadyms.server.dataaccess.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryBuilder {

	private  static EntityManagerFactory entityManagerFactory;

	public static EntityManagerFactory getEntityManagerFactory() {
		if(entityManagerFactory==null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("gwt");
		}
		return entityManagerFactory;
	}

}

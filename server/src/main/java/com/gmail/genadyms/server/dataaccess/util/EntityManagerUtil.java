package com.gmail.genadyms.server.dataaccess.util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerUtil {

	private  static EntityManager entityManager;

	public static EntityManager getEntityManager() {
		if(entityManager==null) {
			entityManager = Persistence.createEntityManagerFactory("gwt").createEntityManager();
		}
		return entityManager;
	}

}

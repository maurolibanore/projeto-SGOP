package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUTIL {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenciaPU");
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public static void close() {
		emf.close();
	}
	
	
}

package my.example.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EmfProducer {
	public static EntityManager createLocalEntityManager() {
		return Persistence.createEntityManagerFactory("jpa_local").createEntityManager();
	}
}

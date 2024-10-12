package my.example.jpa;

import java.io.Serializable;
import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EmfProducer implements EmfProducible, Serializable {
	private static final long serialVersionUID = 1L;
	private transient EntityManagerFactory emf;

	@Override
	@AppDb
	@ApplicationScoped
	@Produces
	public EntityManagerFactory produce() {
	    if (Objects.isNull(this.emf)) {
	        if (System.getProperty("catalina.home") != null) {
	            this.emf = Persistence.createEntityManagerFactory("jpa_tomcat");
	        } else {
	            this.emf = Persistence.createEntityManagerFactory("jpa");
	        }
	    }
	    return this.emf;
	}


	@Override
	public void dispose(@Disposes @AppDb
	final EntityManagerFactory disposingEmf) {
		disposingEmf.close();
	}

	public static EntityManager createLocalEntityManager() {
		return Persistence.createEntityManagerFactory("jpa").createEntityManager();
	}
}
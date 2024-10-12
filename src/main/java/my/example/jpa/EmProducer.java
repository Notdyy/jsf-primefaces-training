package my.example.jpa;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@ApplicationScoped
public class EmProducer implements EmProducible, Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	@AppDb
	private EntityManagerFactory emf;

	@Override
	@AppDb
	@RequestScoped
	@Produces
	public EntityManager produce() {
		return this.emf.createEntityManager();
	}

	@Override
	public void dispose(@Disposes @AppDb final EntityManager disposingEm) {
		disposingEm.close();
	}
}

package my.example.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApplicationScoped
public class AppDbService extends JpaService {
	private static final long serialVersionUID = 1L;

	// ---- for Unit Test
	public AppDbService() {
	}

	public AppDbService(EntityManager em) {
		this.em = em;
	}

	@Inject
	@AppDb
	private EntityManager em;
}

package my.example.dao.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import my.example.dao.EmployeeDaoServiceable;
import my.example.entity.EmployeeData;
import my.example.jpa.AppDbService;
import my.example.model.Employee;

@ApplicationScoped
public class EmployeeDaoJpaImpl extends AbstractJpa<EmployeeData> implements EmployeeDaoServiceable {
	// ---- for Unit Test
	private EntityManager em;

	public EmployeeDaoJpaImpl(EntityManager em) {
		this.setClazz(EmployeeData.class);
		this.em = em;
	}

	// --- after create new class
	@Inject
	protected AppDbService db;

	@Override
	public EntityManager getEm() {
		return (this.em != null) ? em : db.getEm();
	}

	public EmployeeDaoJpaImpl() {
		this.setClazz(EmployeeData.class);
	}
	
	// ---- implement DAO method
	
	@Override
	public List<EmployeeData> findByName(Employee emp) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT e FROM EmployeeData e ");
		if (emp != null && emp.getFirstName() != null || emp != null && emp.getLastName() != null) {
			sb.append("WHERE ");
			if (emp.getFirstName() != null) {
				sb.append("e.firstName LIKE :firstName ");
			}
			if (emp.getLastName() != null) {
				if (emp.getFirstName() != null) {
					sb.append("AND ");
				}
				sb.append("e.lastName LIKE :lastName ");
			}
		}

		TypedQuery<EmployeeData> query = this.getEm().createQuery(sb.toString(), EmployeeData.class);
		if (emp != null && emp.getFirstName() != null) {
			query.setParameter("firstName", emp.getFirstName().concat("%"));
		}
		if (emp != null && emp.getLastName() != null) {
			query.setParameter("lastName", emp.getLastName().concat("%"));
		}

		return query.getResultList();
	}
	
	@Override
	public int countDataDb() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("SELECT COUNT(e) FROM EmployeeData e ");
	    TypedQuery<Long> query = this.getEm().createQuery(sb.toString(), Long.class);
	    Long count = query.getSingleResult();
	    return count != null ? count.intValue() : 0;
	}


}

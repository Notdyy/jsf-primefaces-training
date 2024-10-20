package my.example.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import my.example.dao.EmployeeDaoServiceable;
import my.example.entity.EmployeeData;
import my.example.jpa.AppDbService;
import my.example.model.Employee;
import my.example.model.EmployeeCriteria;
import my.example.utils.EmployeeCustomFilterAndSortBy;

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
	
	private static String firstNameParam = "firstName";
	private static String lastNameParam = "lastName";
	
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
			query.setParameter(firstNameParam, emp.getFirstName().concat("%"));
		}
		if (emp != null && emp.getLastName() != null) {
			query.setParameter(lastNameParam, emp.getLastName().concat("%"));
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

	@Override
	public Long count(EmployeeCriteria employeeCriteria, Map<String, FilterMeta> filterBy) {
		CriteriaBuilder cb = this.getEm().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        List<Predicate> predicates = new ArrayList<>();
		Root<EmployeeData> adj = cq.from(EmployeeData.class);
		
		cq.multiselect(cb.count(adj));
		
		if (employeeCriteria.getId() != null) {
			predicates.add(cb.equal(adj.get("id"), employeeCriteria.getId()));
		}
		if (StringUtils.isNotBlank(employeeCriteria.getFirstName())) {
			predicates.add(cb.like(cb.lower(adj.get(firstNameParam)), employeeCriteria.getFirstName().toLowerCase().concat("%")));
		}
		if (StringUtils.isNotBlank(employeeCriteria.getLastName())) {
			predicates.add(cb.like(cb.lower(adj.get(lastNameParam)), employeeCriteria.getLastName().toLowerCase().concat("%")));
		}
		if (employeeCriteria.getBirthDate() != null) {
	        predicates.add(cb.equal(adj.get("birthDate"), employeeCriteria.getBirthDate()));
	    }
		if (filterBy != null && !filterBy.isEmpty()) {
			List<Predicate> predicatesFilter = EmployeeCustomFilterAndSortBy.customFilterMatchMode(filterBy, cb, adj);
			if (!predicatesFilter.isEmpty()) {
			    predicates.addAll(predicatesFilter);
			}
		}
		

		
		
		cq.where(predicates.toArray(new Predicate[] {}));
		TypedQuery<Long> query = getEm().createQuery(cq);
		
		return query.getSingleResult();
	}
	
	@Override
	public List<EmployeeData> search(int offset, int pageSize, EmployeeCriteria employeeCriteria, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
		
		CriteriaBuilder cb = this.getEm().getCriteriaBuilder();
        CriteriaQuery<EmployeeData> cq = cb.createQuery(EmployeeData.class);
        List<Predicate> predicates = new ArrayList<>();

        Root<EmployeeData> adj = cq.from(EmployeeData.class);
        
        if (employeeCriteria.getId() != null) {
			predicates.add(cb.equal(adj.get("id"), employeeCriteria.getId()));
		}
		if (StringUtils.isNotBlank(employeeCriteria.getFirstName())) {
			predicates.add(cb.like(cb.lower(adj.get(firstNameParam)), employeeCriteria.getFirstName().toLowerCase().concat("%")));
		}
		if (StringUtils.isNotBlank(employeeCriteria.getLastName())) {
			predicates.add(cb.like(cb.lower(adj.get(lastNameParam)), employeeCriteria.getLastName().toLowerCase().concat("%")));
		}
		if (employeeCriteria.getBirthDate() != null) {
	        predicates.add(cb.equal(adj.get("birthDate"), employeeCriteria.getBirthDate()));
	    }
		if (filterBy != null && !filterBy.isEmpty()) {
			List<Predicate> predicatesFilter = EmployeeCustomFilterAndSortBy.customFilterMatchMode(filterBy, cb, adj);
			if (!predicatesFilter.isEmpty()) {
			    predicates.addAll(predicatesFilter);
			}
		}
		if (sortBy != null && !sortBy.isEmpty()) {
			List<Order> orders = EmployeeCustomFilterAndSortBy.customSortBy(sortBy, cb, adj);
		    if (!orders.isEmpty()) {
		        cq.orderBy(orders);
		    }
		}
        
        cq.where(predicates.toArray(new Predicate[] {}));
		TypedQuery<EmployeeData> query = this.getEm().createQuery(cq);
		
		
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}


}

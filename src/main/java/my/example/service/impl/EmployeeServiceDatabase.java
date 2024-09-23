package my.example.service.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import my.example.model.Employee;
import my.example.service.EmployeeServiceable;
import my.example.service.qulifier.Repository;

@ApplicationScoped
@Repository(name = Repository.DATABASE)
public class EmployeeServiceDatabase implements EmployeeServiceable {
	
	@PersistenceContext
	private EntityManager em;
	private EntityTransaction transaction = em.getTransaction();


	@Override
	public void add(Employee employee) {
		try {
			transaction.begin();
			em.persist(employee);
			transaction.commit();
		} finally {
			em.close();
		}
	}

	@Override
	public List<Employee> search(Employee empSearch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Employee employee) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Employee> getEmployees(int size) {
		// TODO Auto-generated method stub
		return null;
	}

}

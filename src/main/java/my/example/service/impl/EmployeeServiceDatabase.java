package my.example.service.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import my.example.dao.EmployeeDaoServiceable;
import my.example.dto.EmployeeDto;
import my.example.entity.EmployeeData;
import my.example.jpa.AppDbService;
import my.example.model.Employee;
import my.example.service.EmployeeServiceable;
import my.example.service.qulifier.Repository;

@ApplicationScoped
@Repository(name = Repository.DATABASE)
public class EmployeeServiceDatabase implements EmployeeServiceable {

	// --- after create new class
	@Inject
	protected AppDbService db;

	
	@Inject
	EmployeeDaoServiceable employeeDao;


	@Override
	public void add(Employee employee) {
		try {
			EmployeeData employeeData = EmployeeDto.toEntityEmp(employee);
			db.begin();
			employeeDao.create(employeeData);
			db.commit();
		} catch (Exception e) {
			db.rollback();
			throw e;
		}
	}

	@Override
	public List<Employee> search(Employee empSearch) {

		List<EmployeeData> datas = employeeDao.findByName(empSearch);
		return EmployeeDto.toModelEmp(datas);
	}

	@Override
	public void delete(String id) {
		try {
			db.begin();
			employeeDao.deleteById(id);
			db.commit();
		} catch (Exception e) {
			db.rollback();
			throw e;
		}
	}
	
	@Override
	public void update (Employee employee) {
		try {
			db.begin();
			EmployeeData employeeData = EmployeeDto.toEntityEmp(employee);
			employeeDao.update(employeeData);
			db.commit();
		} catch (Exception e) {
			db.rollback();
			throw e;
		}
	}

	@Override
	public List<Employee> getEmployees(int size) {
		List<EmployeeData> datas = employeeDao.findAll();
		return EmployeeDto.toModelEmp(datas);
	}



}

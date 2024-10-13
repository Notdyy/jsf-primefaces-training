package my.example.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import lombok.extern.slf4j.Slf4j;
import my.example.dao.EmployeeDaoServiceable;
import my.example.dto.EmployeeDto;
import my.example.entity.EmployeeData;
import my.example.jpa.AppDbService;
import my.example.model.Employee;
import my.example.model.EmployeeCriteria;
import my.example.service.EmployeeServiceable;
import my.example.service.qulifier.Repository;
import my.example.utils.EmployeeUtils;


@Slf4j
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
		return EmployeeDto.toModelListEmp(datas);
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
	    try {
	        List<EmployeeData> datas = new ArrayList<>();
	        int countEmpDb = employeeDao.countDataDb();
	        int minAge = 2;
	        int maxAge = 100;
	        log.debug("countEmpDb -> {}", countEmpDb);
	        if (size > countEmpDb) {
	            for (int i = countEmpDb; i < size; i++) {
	            	Employee employeeMock = EmployeeUtils.createMockEmployee(minAge, maxAge);
	                EmployeeData employeeData = EmployeeDto.toEntityEmp(employeeMock);
	                db.begin();
	                employeeDao.create(employeeData);
	                db.commit();
	                datas.add(employeeData);
	            }
	        }
	        return EmployeeDto.toModelListEmp(datas);
	    } catch (Exception e) {
	        db.rollback();
	        throw e;
	    }
	}

	@Override
	public Long countTicket(EmployeeCriteria employeeCriteria, Map<String, FilterMeta> filterBy) {
		log.debug("countTicket ({})", employeeCriteria);
		return employeeDao.count(employeeCriteria, filterBy);
	}

	@Override
	public List<Employee> searchEmployee(int first, int pageSize, EmployeeCriteria employeeCriteria, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getById(String rowKey) {
		EmployeeData employeeData = employeeDao.find(rowKey);
		return EmployeeDto.toModelEmp(employeeData);
	}

	@Override
	public String getRowKey(Employee employee) {
		return employee.getId();
	}

	@Override
	public EmployeeCriteria getEmployeesCriterias(int i) {
		// TODO Auto-generated method stub
		return null;
	}




}

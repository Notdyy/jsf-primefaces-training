package my.example.dao;

import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;

import my.example.entity.EmployeeData;
import my.example.model.Employee;
import my.example.model.EmployeeCriteria;

public interface EmployeeDaoServiceable extends OrmDao<EmployeeData> {
	
	public List<EmployeeData> findByName(Employee emp);
	
	public int countDataDb();

	public Long count(EmployeeCriteria employeeCriteria, Map<String, FilterMeta> filterBy);
	
}

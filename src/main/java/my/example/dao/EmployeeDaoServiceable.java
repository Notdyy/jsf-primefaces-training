package my.example.dao;

import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import my.example.entity.EmployeeData;
import my.example.model.Employee;
import my.example.model.EmployeeCriteria;

public interface EmployeeDaoServiceable extends OrmDao<EmployeeData> {

	public Long count(EmployeeCriteria employeeCriteria, Map<String, FilterMeta> filterBy);

	public List<EmployeeData> search(int offset, int pageSize, EmployeeCriteria employeeCriteria, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy);
	
	public List<EmployeeData> findByName(Employee emp);
	
	public int countDataDb();
	
}

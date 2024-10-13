package my.example.service;

import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import my.example.model.Employee;
import my.example.model.EmployeeCriteria;


public interface EmployeeServiceable {

	public void add(Employee employee);

	public List<Employee> search(Employee empSearch);

	public void delete(String id);

	public void update(Employee employee);

	public List<Employee> getEmployees(int size);

	public Long countTicket(EmployeeCriteria employeeCriteria, Map<String, FilterMeta> filterBy);

	public List<Employee> searchEmployee(int first, int pageSize, EmployeeCriteria employeeCriteria, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy);

	public Employee getById(String rowKey);

	String getRowKey(Employee employee);

	public EmployeeCriteria getEmployeesCriterias(int size);
}
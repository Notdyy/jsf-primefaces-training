package my.example.service;

import java.util.List;

import my.example.model.Employee;

public interface EmployeeServiceable {
	
	public void add(Employee employee);
	
	public List<Employee> search(Employee empSearch);
	
	public int delete(String id);
	
	public int update(Employee employee);
}

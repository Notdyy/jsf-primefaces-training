package my.example.dao;

import java.util.List;

import my.example.entity.EmployeeData;
import my.example.model.Employee;

public interface EmployeeDaoServiceable extends OrmDao<EmployeeData> {
	
	public List<EmployeeData> findByName(Employee emp);
	
}

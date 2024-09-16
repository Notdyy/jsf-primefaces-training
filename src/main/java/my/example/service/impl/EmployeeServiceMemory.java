package my.example.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import my.example.model.Employee;
import my.example.service.EmployeeServiceable;

@ApplicationScoped
public class EmployeeServiceMemory implements EmployeeServiceable  {

	private static  Map<String, Employee> employeeMap = new HashMap<>();

	public void add(Employee employee) {
		employeeMap.put(employee.getId(), employee);
	}

	public int update(Employee employee) {
		if (employeeMap.containsKey(employee.getId())) {
			employeeMap.put(employee.getId(),employee);
			return 1;
		}else {
			return 0;
		}
	}

	public List<Employee> search(Employee empSearch) {
		List<Employee> employeeList = new ArrayList<>();
		for (Map.Entry<String, Employee> entry : employeeMap.entrySet()) {
			employeeList.add(entry.getValue());
		}
		return employeeList;
	}

	public int delete(String id) {
		if (employeeMap.containsKey(id)) {
			employeeMap.remove(id);
			return 1;
		}else {
			return 0;
		}
	}

}

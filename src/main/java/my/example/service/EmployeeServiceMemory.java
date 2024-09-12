package my.example.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import my.example.model.Employee;

public class EmployeeServiceMemory implements Serializable  {

	private static final long serialVersionUID = 1L;
	
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

	public List<Employee> search(Employee employeeCriteria) {
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

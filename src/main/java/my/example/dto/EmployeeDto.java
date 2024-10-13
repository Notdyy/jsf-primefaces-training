package my.example.dto;

import java.util.List;
import java.util.stream.Collectors;

import my.example.entity.EmployeeData;
import my.example.model.Employee;
import my.example.model.EmployeeCriteria;

public class EmployeeDto {
	
	private EmployeeDto() {
		throw new IllegalStateException("Data Transfer Object class");
	}
	
	public static EmployeeData toEntityEmp (Employee data) {
		return EmployeeData.builder()
				.firstName(data.getFirstName())
				.lastName(data.getLastName())
				.birthDate(data.getBirthDate())
				.build();
	}
	
	public static List<Employee> toModelListEmp(List<EmployeeData> datas) {
	    return datas.stream()
	        .map(data -> new Employee(data.getId(), data.getFirstName(), data.getLastName(), data.getBirthDate()))
	        .collect(Collectors.toList());
	}
	
	public static Employee toModelEmp (EmployeeData data) {
		return Employee.builder()
				.firstName(data.getFirstName())
				.lastName(data.getLastName())
				.birthDate(data.getBirthDate())
				.build();
	}
	
	public static EmployeeCriteria mapEmpToEmpCriteria(Employee data) {
		return EmployeeCriteria.builder()
				.id(data.getId())
				.firstName(data.getFirstName())
				.lastName(data.getLastName())
				.birthDate(data.getBirthDate())
				.build();
	}

}

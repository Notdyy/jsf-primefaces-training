package my.example.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import com.github.javafaker.Faker;

import lombok.extern.slf4j.Slf4j;
import my.example.model.Employee;
import my.example.service.EmployeeServiceable;
@Slf4j
@ApplicationScoped
public class EmployeeServiceMemory implements EmployeeServiceable  {

	private static  Map<String, Employee> employeeMap = new HashMap<>();
	
	private List<Employee> employees;
	
	private static final Faker faker = new Faker();
	
	@PostConstruct
    public void init() {
		log.debug("begin employees  -> {}",employees);
		employees = new ArrayList<>();
		employees.add(new Employee("1",faker.name().firstName(),faker.name().lastName(),LocalDate.of(2002,01,01)) );
		employees.add(new Employee("2",faker.name().firstName(),faker.name().lastName(),LocalDate.of(2015,01,01)) );
		employees.add(new Employee("3",faker.name().firstName(),faker.name().lastName(),LocalDate.of(2001,01,01)) );
		employees.add(new Employee("4",faker.name().firstName(),faker.name().lastName(),LocalDate.of(2000,01,01)) );
		employees.add(new Employee("5",faker.name().firstName(),faker.name().lastName(),LocalDate.of(2009,01,01)) );
		employees.add(new Employee("6",faker.name().firstName(),faker.name().lastName(),LocalDate.of(2001,01,01)) );
		employees.add(new Employee("7",faker.name().firstName(),faker.name().lastName(),LocalDate.of(2002,01,01)) );
		employees.add(new Employee("8",faker.name().firstName(),faker.name().lastName(),LocalDate.of(2002,01,01)) );
		employees.add(new Employee("9",faker.name().firstName(),faker.name().lastName(),LocalDate.of(2007,01,01)) );
		employees.add(new Employee("10",faker.name().firstName(),faker.name().lastName(),LocalDate.of(2012,01,01)) );
		employees.add(new Employee("11",faker.name().firstName(),faker.name().lastName(),LocalDate.of(2019,01,01)) );
		employees.add(new Employee("12",faker.name().firstName(),faker.name().lastName(),LocalDate.of(2018,01,01)) );
		employees.add(new Employee("13",faker.name().firstName(),faker.name().lastName(),LocalDate.of(2016,01,01)) );
		employees.add(new Employee("14",faker.name().firstName(),faker.name().lastName(),LocalDate.of(2017,01,01)) );
		employees.add(new Employee("15",faker.name().firstName(),faker.name().lastName(),LocalDate.of(2018,01,01)) );
		employees.add(new Employee("16",faker.name().firstName(),faker.name().lastName(),LocalDate.of(2015,01,01)) );
		employees.add(new Employee("17",faker.name().firstName(),faker.name().lastName(),LocalDate.of(2014,01,01)) );
		employees.add(new Employee("18",faker.name().firstName(),faker.name().lastName(),LocalDate.of(2001,01,01)) );
		employees.add(new Employee("19",faker.name().firstName(),faker.name().lastName(),LocalDate.of(2002,01,01)) );
		employees.add(new Employee("20",faker.name().firstName(),faker.name().lastName(),LocalDate.of(2000,01,01)) );
		employees.add(new Employee("21",faker.name().firstName(),faker.name().lastName(),LocalDate.of(2000,01,01)) );
		employees.add(new Employee("22",faker.name().firstName(),faker.name().lastName(),LocalDate.of(2009,01,01)) );
		employees.add(new Employee("23",faker.name().firstName(),faker.name().lastName(),LocalDate.of(2001,01,01)) );
		employees.add(new Employee("24",faker.name().firstName(),faker.name().lastName(),LocalDate.of(2011,01,01)) );
		employees.add(new Employee("25",faker.name().firstName(),faker.name().lastName(),LocalDate.of(2003,01,01)) );
		log.debug("end employees  -> {}",employees);
	}

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
	
	@Override
	public List<Employee> getEmployees(int size) {
	    List<Employee> employeeList = new ArrayList<>(employeeMap.values());

	    if (size > employeeList.size()) {
	        log.warn("Requested size {} exceeds available employees, filling with existing dummy data.", size);
	        for (int i = employeeList.size(); i < size; i++) {
	            if (i < employees.size()) {
	                Employee additionalEmployee = employees.get(i);
	                employeeList.add(additionalEmployee);
	                employeeMap.put(additionalEmployee.getId(), additionalEmployee);
	            } else {
	                log.warn("Not enough dummy employees to fill the requested size.");
	                break;
	            }
	        }
	    }

	    return new ArrayList<>(employeeList.subList(0, size));
	}



}

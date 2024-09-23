package my.example.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import com.github.javafaker.Faker;

import lombok.extern.slf4j.Slf4j;
import my.example.model.Employee;
import my.example.service.EmployeeServiceable;
import my.example.service.qulifier.Repository;

@Slf4j
@ApplicationScoped
@Repository(name = Repository.MEMORY)
public class EmployeeServiceMemory implements EmployeeServiceable {

	private static Map<String, Employee> employeeMap = new HashMap<>();

	private static final Faker faker = new Faker();

	public void add(Employee employee) {
		employeeMap.put(employee.getId(), employee);
	}

	public int update(Employee employee) {
		if (employeeMap.containsKey(employee.getId())) {
			employeeMap.put(employee.getId(), employee);
			return 1;
		} else {
			return 0;
		}
	}

	public List<Employee> search(Employee empSearch) {
		List<Employee> employeeList = new ArrayList<>();
		String firstName = empSearch.getFirstName() != null ? empSearch.getFirstName().toLowerCase() : "";
		String lastName = empSearch.getLastName() != null ? empSearch.getLastName().toLowerCase() : "";
		for (Employee e : employeeMap.values()) {
			boolean matchesFirstName = firstName.isEmpty() || e.getFirstName().toLowerCase().contains(firstName);
			boolean matchesLastName = lastName.isEmpty() || e.getLastName().toLowerCase().contains(lastName);
			if (matchesFirstName && matchesLastName) {
				employeeList.add(e);
			}

		}
		return employeeList;
	}

	public int delete(String id) {
		if (employeeMap.containsKey(id)) {
			employeeMap.remove(id);
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<Employee> getEmployees(int size) {
		List<Employee> employeeList = new ArrayList<>(employeeMap.values());
		int minAge = 2;
		int maxAge = 100;
		if (size > employeeList.size()) {
			for (int i = employeeList.size(); i < size; i++) {
				int randomAge = faker.number().numberBetween(minAge, maxAge);
				LocalDate hireDate = LocalDate.now().minusYears(randomAge)
						.withDayOfYear(faker.number().numberBetween(1, 365));
				Employee employeeMock = new Employee();
				employeeMock.setFirstName(faker.name().firstName());
				employeeMock.setLastName(faker.name().lastName());
				employeeMock.setBirthDate(hireDate);
				employeeList.add(employeeMock);
				employeeMap.put(employeeMock.getId(), employeeMock);
			}
			log.warn("Requested size {} exceeds available employees, filling with dummy data.", size);
		}

		return employeeList.subList(0, Math.min(size, employeeList.size()));
	}

}

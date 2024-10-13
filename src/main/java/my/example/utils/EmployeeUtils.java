package my.example.utils;

import java.time.LocalDate;

import com.github.javafaker.Faker;

import my.example.model.Employee;

public class EmployeeUtils {
	
	private static final Faker faker = new Faker();
	
	private EmployeeUtils() {
		throw new IllegalStateException("utils class");
	}
	
	public static Employee createMockEmployee(int minAge, int maxAge) {
	    int randomAge = faker.number().numberBetween(minAge, maxAge);
	    LocalDate hireDate = LocalDate.now().minusYears(randomAge).withDayOfYear(faker.number().numberBetween(1, 365));
	    return Employee.builder()
	                   .firstName(faker.name().firstName())
	                   .lastName(faker.name().lastName())
	                   .birthDate(hireDate)
	                   .build();
	}
}

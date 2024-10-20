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

    /**
     * Converts an Employee model object to an EmployeeData entity.
     *
     * @param data the Employee model object to be converted
     * @return the EmployeeData entity object converted from the given Employee
     */
    public static EmployeeData toEntityEmp(Employee data) {
        return EmployeeData.builder()
                .id(data.getId())
                .firstName(data.getFirstName())
                .lastName(data.getLastName())
                .birthDate(data.getBirthDate())
                .build();
    }

    /**
     * Converts a list of EmployeeData entities to a list of Employee model objects.
     *
     * @param datas the list of EmployeeData entities to be converted
     * @return a list of Employee model objects converted from the given EmployeeData entities
     */
    public static List<Employee> toModelListEmp(List<EmployeeData> datas) {
        return datas.stream()
                .map(data -> new Employee(data.getId(), data.getFirstName(), data.getLastName(), data.getBirthDate()))
                .collect(Collectors.toList());
    }

    /**
     * Converts an EmployeeData entity to an Employee model object.
     *
     * @param data the EmployeeData entity to be converted
     * @return the Employee model object converted from the given EmployeeData entity
     */
    public static Employee toModelEmp(EmployeeData data) {
        return Employee.builder()
                .id(data.getId())
                .firstName(data.getFirstName())
                .lastName(data.getLastName())
                .birthDate(data.getBirthDate())
                .build();
    }

    /**
     * Maps an Employee model object to an EmployeeCriteria object.
     *
     * @param data the Employee model object to be mapped
     * @return the EmployeeCriteria object mapped from the given Employee
     */
    public static EmployeeCriteria mapEmpToEmpCriteria(Employee data) {
        return EmployeeCriteria.builder()
                .id(data.getId())
                .firstName(data.getFirstName())
                .lastName(data.getLastName())
                .birthDate(data.getBirthDate())
                .build();
    }

    /**
     * Maps an EmployeeCriteria object to an Employee model object.
     *
     * @param data the EmployeeCriteria object to be mapped
     * @return the Employee model object mapped from the given EmployeeCriteria
     */
    public static Employee mapEmpCriteriaToEmp(EmployeeCriteria data) {
        return Employee.builder()
                .id(data.getId())
                .firstName(data.getFirstName())
                .lastName(data.getLastName())
                .birthDate(data.getBirthDate())
                .build();
    }
}
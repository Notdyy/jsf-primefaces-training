package my.example.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.ComparatorUtils;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import lombok.extern.slf4j.Slf4j;
import my.example.comparator.LazySorter;
import my.example.dto.EmployeeDto;
import my.example.model.Employee;
import my.example.model.EmployeeCriteria;
import my.example.service.EmployeeServiceable;
import my.example.service.qulifier.Repository;
import my.example.utils.EmployeeUtils;

@Slf4j
@ApplicationScoped
@Repository(name = Repository.MEMORY)
public class EmployeeServiceMemory implements EmployeeServiceable {

	private static Map<String, Employee> employeeMap = new HashMap<>();
	
	@Override
	public void add(Employee employee) {
		employeeMap.put(employee.getId(), employee);
	}

	@Override
	public void update(Employee employee) {
		if (employeeMap.containsKey(employee.getId())) {
			employeeMap.put(employee.getId(), employee);
		}
	}
	
	@Override
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
	
	@Override
	public void delete(String id) {
		if (employeeMap.containsKey(id)) {
			employeeMap.remove(id);
		}
	}

	@Override
	public List<Employee> getEmployees(int size) {
		List<Employee> employeeList = new ArrayList<>(employeeMap.values());
		int minAge = 2;
		int maxAge = 100;
		if (size > employeeList.size()) {
			for (int i = employeeList.size(); i < size; i++) {
				Employee employeeMock = EmployeeUtils.createMockEmployee(minAge, maxAge);
				employeeList.add(employeeMock);
				employeeMap.put(employeeMock.getId(), employeeMock);
			}
			log.warn("Requested size {} exceeds available employees, filling with dummy data.", size);
		}

		return employeeList.subList(0, Math.min(size, employeeList.size()));
	}

	@Override
	public Long countTicket(EmployeeCriteria employeeCriteria, Map<String, FilterMeta> filterBy) {
	    return (long) employeeMap.values().stream()
	        .filter(o -> EmployeeUtils.filter(FacesContext.getCurrentInstance(), filterBy.values(), o))
	        .filter(e -> 
	            (employeeCriteria.getFirstName() == null || employeeCriteria.getFirstName().isEmpty() || 
	            e.getFirstName().toLowerCase().contains(employeeCriteria.getFirstName().toLowerCase())) &&
	            (employeeCriteria.getLastName() == null || employeeCriteria.getLastName().isEmpty() || 
	            e.getLastName().toLowerCase().contains(employeeCriteria.getLastName().toLowerCase()))
	        )
	        .count();
	}


	@Override
	public List<Employee> searchEmployee(int first, int pageSize, EmployeeCriteria employeeCriteria,
			Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
		log.debug("searchTicket ({})", employeeCriteria);

		// apply filters
		List<Employee> employees = employeeMap.values().stream()
				.filter(o -> EmployeeUtils.filter(FacesContext.getCurrentInstance(), filterBy.values(), o))
				.filter(e -> 
						(employeeCriteria.getFirstName() == null || employeeCriteria.getFirstName().isEmpty() ||
						 e.getFirstName().toLowerCase().contains(employeeCriteria.getFirstName().toLowerCase())) && 
						(employeeCriteria.getLastName() == null || employeeCriteria.getLastName().isEmpty() || 
						 e.getLastName().toLowerCase().contains(employeeCriteria.getLastName().toLowerCase())))
				.collect(Collectors.toList());

		// apply sorting
		if (!sortBy.isEmpty()) {
			List<Comparator<? super Employee>> comparators = sortBy.values().stream()
					.map(o -> new LazySorter(o.getField(), o.getOrder())).collect(Collectors.toList());

			@SuppressWarnings("unchecked")
			Comparator<Employee> comparator = ComparatorUtils.chainedComparator(comparators);
			employees.sort(comparator);
		}
		int toIndex = Math.min(first + pageSize, employees.size());
		return employees.subList(first, toIndex);
	}

	@Override
	public Employee getById(String rowKey) {
	    log.debug("Fetching employee with ID: {}", rowKey);
	    return employeeMap.get(rowKey);
	}
	
	@Override
	public String getRowKey(Employee employee) {
	    log.debug("Getting row key for employee: {}", employee.getId());
	    return employee.getId();
	}

	@Override
	public EmployeeCriteria getEmployeesCriterias(int size) {
	    List<Employee> employeeList = new ArrayList<>(employeeMap.values());
	    int minAge = 2;
	    int maxAge = 100;
	    EmployeeCriteria aggregatedCriteria = new EmployeeCriteria();

	    if (size > employeeList.size()) {
	        for (int i = employeeList.size(); i < size; i++) {
	            Employee employeeMock = EmployeeUtils.createMockEmployee(minAge, maxAge);
	            employeeList.add(employeeMock);
	            employeeMap.put(employeeMock.getId(), employeeMock);
	            aggregatedCriteria = EmployeeDto.mapEmpToEmpCriteria(employeeMock); 
	        }
	        log.warn("Requested size {} exceeds available employees, filling with dummy data.", size);
	    }

	    return aggregatedCriteria;
	}
}

package my.example.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import my.example.model.Employee;
import my.example.model.EmployeeCriteria;
import my.example.service.EmployeeServiceable;
import my.example.service.qulifier.Repository;


@Slf4j
@Getter
@Setter 
@ViewScoped
public class LazyEmployeeDataModel extends LazyDataModel<Employee> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	@Repository(name = Repository.MEMORY)
	private EmployeeServiceable service;
	
	private EmployeeCriteria employeeCriteria;


	@Override
	public int count(Map<String, FilterMeta> filterBy) {
	    if (employeeCriteria == null) {
	        log.warn("employeeCriteria is null when counting employees.");
	        return 0; // or some appropriate value
	    }
	    return service.countTicket(employeeCriteria, filterBy).intValue();
	}


	@Override
	public List<Employee> load(int first, int pageSize, Map<String, SortMeta> sortBy,Map<String, FilterMeta> filterBy) {
		try {
		log.debug("first:{}", first);
		log.debug("pageSize:{}", pageSize);
		log.debug("sortBy:{}", sortBy);
		log.debug("filterBy:{}", filterBy);
		 return service.searchEmployee(first, pageSize, employeeCriteria, sortBy, filterBy);
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
	
	@Override
    public String getRowKey(Employee employee) {
    	
		return employee.getId();

    }
	
	@Override
	public Employee getRowData(String rowKey) {
		log.debug("--- getRowData ------------------");
		log.debug("getRowData({})", rowKey);

		try {
			return service.getById(rowKey);
		} catch (Exception e) {
			log.debug("--- getRowData------------------{}", e);
			return null;
		}

	}

}

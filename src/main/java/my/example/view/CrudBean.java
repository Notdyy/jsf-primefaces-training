package my.example.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import my.example.exception.AgeUnderLimitException;
import my.example.model.Employee;
import my.example.service.EmployeeServiceable;
import my.example.service.qulifier.Repository;

@Slf4j
@Setter
@Getter
@ViewScoped
@Named
public class CrudBean extends ViewBase implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private Employee employeeCriteria;
	private Employee employeeEdit;
	private Employee selectedMember;
	private List<Employee> employeeList;

	@Inject
	@Repository(name = Repository.DATABASE)
	private EmployeeServiceable service;

	@PostConstruct
	public void init() {
		this.setMode(READ_MODE);
		employeeCriteria = new Employee();
		employeeList = service.getEmployees(1000);
		employeeList = service.search(employeeCriteria);
	}

	public void searchBtnOnclick() {
		log.debug("begin searchBtnOnclick employeeList -> {}", employeeList);
		employeeList = service.search(employeeCriteria);
		log.debug("end searchBtnOnclick employeeEdit -> {}",employeeEdit);
	}

	public void addBtnOnclick() {
		log.debug("begin addBtnOnclick employeeEdit -> {}", employeeEdit);
		this.setMode(CREATE_MODE);
		employeeEdit = new Employee();
		log.debug("end addBtnOnclick employeeEdit -> {}",employeeEdit);

	}

	public void editBtnOnclick(Employee p) {
		log.debug("begin editBtnOnclick employeeEdit -> {}", employeeEdit);
		this.setMode(UPDATE_MODE);
		employeeEdit = new Employee(p);
		log.debug("end editBtnOnclick, {}", employeeEdit);
	}
	
	public void saveBtnOnclick() {
		try {
			log.debug("begin saveBtnOnclick employeeEdit -> {}, selectedMember -> {}", employeeEdit, selectedMember);
			if (this.employeeEdit.getAge(this.employeeEdit.getBirthDate()).getYears() < 2) {
				messageError();
				throw new AgeUnderLimitException("Age must be more than 2 years.");
			}
			service.add(employeeEdit);
			this.setMode(UPDATE_MODE);
			messageCompelete();
			this.selectedMember = new Employee(employeeEdit);
			PrimeFaces.current().ajax().update("formc");
		} catch (AgeUnderLimitException e) {
			log.error("error employeeEdit {}", employeeEdit);
			log.error("error Exception {}", e);
		}
	}

	public void updateBtnOnclick() {
		try {
			log.debug("begin updateBtnOnclick employeeEdit -> {}, selectedMember -> {}", employeeEdit, selectedMember);
			if (this.employeeEdit.getAge(this.employeeEdit.getBirthDate()).getYears() < 2) {
				messageError();
				employeeEdit = new Employee(selectedMember);
				throw new AgeUnderLimitException("Age must be more than 2 years.");
			}
			service.update(employeeEdit);
			messageEditCompelete();
		} catch (AgeUnderLimitException e) {
			log.error("error employeeEdit {}", employeeEdit);
			log.error("error Exception {}", e);
		} finally {
			log.debug("end updateBtnOnclick, {}", employeeEdit);
		}

	}

	public void deleteBtnOnclick() {
		log.debug("begin deleteBtnOnclick employeeEdit -> {}", employeeEdit);
		service.delete(employeeEdit.getId());
		messageDeleteCompelete();
		log.debug("end deleteBtnOnclick, {}", employeeEdit);
	}

	public void backBtnOnclick() {
		log.debug("begin backBtnOnclick mode -> {}", mode);
		this.setMode(READ_MODE);
		employeeList = service.search(employeeCriteria);
		log.debug("end backBtnOnclick, {}", mode);
	}

	public void resetBtnOnclick() {
		log.debug("begin resetBtnOnclick mode -> {}", mode);
		switch (mode) {
		case CREATE_MODE:
			this.employeeEdit = new Employee();
			break;
		case UPDATE_MODE:
			this.employeeEdit = new Employee(selectedMember);
			break;
		case READ_MODE:
			this.employeeCriteria = new Employee();
			employeeList = service.search(employeeCriteria);
			break;
		default:
			break;
		}

		log.debug("end resetBtnOnclick {}", mode);
	}

	public void onRowSelect(SelectEvent<Employee> event) {
		log.debug("begin onRowSelect employeeEdit -> {}",employeeEdit);
		this.selectedMember = event.getObject();
		if (this.selectedMember != null) {
			this.setMode(UPDATE_MODE);
			this.employeeEdit = new Employee(selectedMember);
		}
		log.debug("begin onRowSelect employeeEdit -> {}",employeeEdit);
	}
}
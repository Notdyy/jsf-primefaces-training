package my.example.view;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.beanutils.BeanUtils;
import org.primefaces.event.SelectEvent;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import my.example.dto.EmployeeDto;
import my.example.exception.AgeUnderLimitException;
import my.example.model.Employee;
import my.example.model.EmployeeCriteria;
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
	
	private EmployeeCriteria employeeCriteria = EmployeeCriteria.builder().build();
	private Employee employeeEdit;
	private Employee selectedMember;

	@Inject
	@Repository(name = Repository.DATABASE)
	private EmployeeServiceable service;
	
	@Inject
	private LazyEmployeeDataModel lazyEmployeeModel;

	@PostConstruct
	public void init() {
		this.setMode(READ_MODE);
		lazyEmployeeModel.setEmployeeCriteria(service.getEmployeesCriterias(1000));
		lazyEmployeeModel.setEmployeeCriteria(employeeCriteria);
	}

	public void searchBtnOnclick() {
		log.debug("begin searchBtnOnclick employeeList -> {}", employeeCriteria);
		lazyEmployeeModel.setEmployeeCriteria(employeeCriteria);
		log.debug("end searchBtnOnclick employeeEdit -> {}",employeeEdit);
	}

	public void addBtnOnclick() {
		log.debug("begin addBtnOnclick employeeEdit -> {}", employeeEdit);
		this.setMode(CREATE_MODE);
		employeeEdit = EmployeeDto.mapEmpCriteriaToEmp(EmployeeCriteria.builder().build());
		log.debug("end addBtnOnclick employeeEdit -> {}",employeeEdit);

	}

	public void editBtnOnclick(Employee p) {
		log.debug("begin editBtnOnclick employeeEdit -> {}", employeeEdit);
		try {
			this.setMode(UPDATE_MODE);
			employeeEdit = new Employee();
			BeanUtils.copyProperties(employeeEdit, p);
			log.debug("end editBtnOnclick, {}", employeeEdit);
		} catch (IllegalAccessException | InvocationTargetException e) {
			messageError(e);
		}
	}
	
	public void saveBtnOnclick(){
		log.debug("begin saveBtnOnclick employeeEdit -> {}, selectedMember -> {}", employeeEdit, selectedMember);
		try {
			if (this.employeeEdit.getAge(this.employeeEdit.getBirthDate()).getYears() < 2) {
				messageErrorInfo();
				throw new AgeUnderLimitException("Age must be more than 2 years.");
			}
			service.add(employeeEdit);
			this.setMode(UPDATE_MODE);
			messageCompelete();
			selectedMember = new Employee();
			BeanUtils.copyProperties(selectedMember, employeeEdit);
		} catch (IllegalAccessException | InvocationTargetException | AgeUnderLimitException e) {
			messageError(e);
		}
	}

	public void updateBtnOnclick() {
		log.debug("begin updateBtnOnclick employeeEdit -> {}, selectedMember -> {}", employeeEdit, selectedMember);
		try {
			if (this.employeeEdit.getAge(this.employeeEdit.getBirthDate()).getYears() < 2) {
				BeanUtils.copyProperties(employeeEdit, selectedMember);
				throw new AgeUnderLimitException("Age must be more than 2 years.");
			}
			service.update(employeeEdit);
			messageEditCompelete();
		} catch (IllegalAccessException | InvocationTargetException | AgeUnderLimitException e) {
			messageError(e);
		}

	}

	public void deleteBtnOnclick() {
		log.debug("begin deleteBtnOnclick employeeEdit -> {}", employeeEdit);
		service.delete(employeeEdit.getId());
		messageDeleteCompelete();
		log.debug("end deleteBtnOnclick, {}", employeeEdit);
	}

	public void backBtnOnclick() {
		this.setMode(READ_MODE);
		lazyEmployeeModel.setEmployeeCriteria(employeeCriteria);
	}

	public void resetBtnOnclick() {
		try {
			log.debug("begin resetBtnOnclick mode -> {}", mode);
			switch (mode) {
			case CREATE_MODE:
				this.employeeEdit = new Employee();
				break;
			case UPDATE_MODE:
				BeanUtils.copyProperties(employeeEdit, selectedMember);
				break;
			case READ_MODE:
				this.employeeCriteria = EmployeeCriteria.builder().build();
				lazyEmployeeModel.setEmployeeCriteria(employeeCriteria);
				break;
			default:
				break;
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			messageError(e);
		}
	}

	public void onRowSelect(SelectEvent<Employee> event) {
		log.debug("begin onRowSelect employeeEdit -> {}", employeeEdit);
		try {
			this.selectedMember = event.getObject();
			if (this.selectedMember != null) {
				this.setMode(UPDATE_MODE);
				employeeEdit = new Employee();
				BeanUtils.copyProperties(employeeEdit, selectedMember);
			}
			log.debug("end onRowSelect employeeEdit -> {}", employeeEdit);
		} catch (IllegalAccessException | InvocationTargetException e) {
			messageError(e);
			e.printStackTrace();
		}
	}
}
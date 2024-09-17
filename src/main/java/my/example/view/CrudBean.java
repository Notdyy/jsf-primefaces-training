package my.example.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import my.example.exception.AgeUnderLimitException;
import my.example.model.Employee;
import my.example.service.EmployeeServiceable;

@Slf4j
@Getter
@Setter
@Named
@ViewScoped
public class CrudBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mode;
	private Employee employeeCriteria;
	private Employee employeeEdit;
	private Employee selectedMember;
	private List<Employee> employeeList;

	@Inject
	EmployeeServiceable service;

	@PostConstruct
	public void init() {
		log.debug("begin init employeeEdit -> {}",employeeEdit);

		mode = "R";
		employeeCriteria = new Employee();
		employeeList = service.getEmployees(25);
		employeeList = service.search(employeeCriteria);
		log.debug("end init employeeEdit -> {}",employeeEdit);
	}

	public void searchBtnOnclick() {
		log.debug("begin searchBtnOnclick employeeEdit -> {}",employeeEdit);

		employeeList = service.search(employeeCriteria);
		log.debug("end searchBtnOnclick employeeEdit -> {}",employeeEdit);

	}

	public void addBtnOnclick() {
		log.debug("begin addBtnOnclick employeeEdit -> {}",employeeEdit);

		mode = "C";
		employeeEdit = new Employee();
		log.debug("end addBtnOnclick employeeEdit -> {}",employeeEdit);

	}

	public void editBtnOnclick(Employee e) {
		log.debug("begin editBtnOnclick employeeEdit -> {}",employeeEdit);
		mode = "U";
		employeeEdit = e.copy();
		log.debug("end editBtnOnclick employeeEdit -> {}",employeeEdit);
	}

	public void onRowSelect(SelectEvent<Employee> event) {
		log.debug("begin onRowSelect employeeEdit -> {}",employeeEdit);
		this.selectedMember = event.getObject();
		if (this.selectedMember != null) {
			this.mode = "U";
			this.employeeEdit = this.selectedMember.copy();
		}
		log.debug("begin onRowSelect employeeEdit -> {}",employeeEdit);
	}

	public void saveBtnOnclick() {
		try {
			log.debug("begin saveBtnOnclick employeeEdit -> {}",employeeEdit);
			if (this.employeeEdit.getAgeBean(this.employeeEdit.getBirthDate()).getYears() < 2) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "อายุต้องมากกว่า 2 ปี"));
				throw new AgeUnderLimitException("อายุต้องมากกว่า 2 ปี");
			} else {

				service.add(employeeEdit);
				mode = "U";

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "บันทึกข้อมูลเรียบร้อย"));
				this.selectedMember = this.employeeEdit.copy();
			}
			log.debug("end saveBtnOnclick employeeEdit -> {}",employeeEdit);
		} catch (AgeUnderLimitException e) {
			log.error("error saveBtnOnclick employeeEdit -> {}", employeeEdit);
		}
	}

	public void updateBtnOnclick() {
		try {
			log.debug("begin updateBtnOnclick employeeEdit -> {}",employeeEdit);
			if (this.employeeEdit.getAgeBean(this.employeeEdit.getBirthDate()).getYears() < 2) {
				employeeEdit = this.selectedMember.copy();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "อายุต้องมากกว่า 2 ปี"));
				throw new AgeUnderLimitException("อายุต้องมากกว่า 2 ปี");
			}
				service.update(employeeEdit);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "บันทึกข้อมูลเรียบร้อย"));
			log.debug("end updateBtnOnclick employeeEdit -> {}",employeeEdit);
		} catch (AgeUnderLimitException e) {
			log.error("error updateBtnOnclick employeeEdit -> {}",employeeEdit);
		}
	}

	public void deleteBtnOnclick() {
		log.debug("begin deleteBtnOnclick employeeEdit -> {}",employeeEdit);

		service.delete(employeeEdit.getId());
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "ลบข้อมูลเรียบร้อย"));
		log.debug("end deleteBtnOnclick employeeEdit -> {}",employeeEdit);

	}

	public void backBtnOnclick() {
		log.debug("begin backBtnOnclick employeeEdit -> {}",employeeEdit);

		mode = "R";
		employeeList = service.search(employeeCriteria);
		log.debug("end backBtnOnclick employeeEdit -> {}",employeeEdit);

	}

	public void resetBtnOnclick() {
		log.debug("begin resetBtnOnclick mode -> {}",mode);
		switch (mode) {
		case "C":
			this.employeeEdit = new Employee();
			break;
		
		case "U":
			this.employeeEdit = this.selectedMember.copy();
			break;	
	
		case "R":
			this.employeeCriteria = new Employee();
			break;
		default:
			break;
		}
		
		log.debug("begin resetBtnOnclick employeeEdit -> {}",employeeEdit);
	} 
}
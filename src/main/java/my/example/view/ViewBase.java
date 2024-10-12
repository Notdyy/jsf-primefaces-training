package my.example.view;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewBase {
	
	protected String mode;
	
	protected static final String READ_MODE = "R";
	
	protected static final String CREATE_MODE = "C";
	
	protected static final String UPDATE_MODE = "U";
	
	private static final String RECORD_COMPELETE = "บันทึกข้อมูลเรียบร้อย";
	
	private static final String RECORD_EDIT_COMPELETE = "แก้ไขข้อมูลเรียบร้อย";
	
	private static final String AGEUNDER_LIMIT = "อายุต้องมากกว่า 2 ปี";
	
	private static final String DELETE_COMPELETE = "ลบข้อมูลสำเร็จ";
	
	protected void messageCompelete() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", RECORD_COMPELETE));
	}
	
	protected void messageEditCompelete() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", RECORD_EDIT_COMPELETE));
	}
	
	protected void messageError() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", AGEUNDER_LIMIT));
	}
	
	protected void messageDeleteCompelete() {
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", DELETE_COMPELETE));
	}



}

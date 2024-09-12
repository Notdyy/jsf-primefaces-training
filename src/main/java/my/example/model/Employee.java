package my.example.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String firstName;
	
	private String lastName;
	
	private Date birthDate;
	
	public Employee() {
		id = UUID.randomUUID().toString();
	}
}

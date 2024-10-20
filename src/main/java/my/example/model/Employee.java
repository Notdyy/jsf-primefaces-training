package my.example.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String firstName;
	
	private String lastName;
	
	private LocalDate birthDate;

	public Period getAge(LocalDate birthDate) {
	    if (birthDate != null) {
	        return Period.between(birthDate, LocalDate.now()); 
	    }
	    return null; 
	}


}

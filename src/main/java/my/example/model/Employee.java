package my.example.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@AllArgsConstructor
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String firstName;

	private String lastName;

	private LocalDate birthDate;

	public Employee() {
		id = UUID.randomUUID().toString();
	}

	public String getAge(LocalDate getbirthDateDisplay) {
		if (getbirthDateDisplay != null) {
			Period period = Period.between(getbirthDateDisplay, LocalDate.now());
			return period.getYears() + " years " + period.getMonths() + " months " + "and " + period.getDays()
					+ " days.";
		}
		return null;
	}
	
	public Period getAgeBean(LocalDate birthDate) {
	    if (birthDate != null) {
	        return Period.between(birthDate, LocalDate.now()); 
	    }
	    return null; 
	}
	
	 public Employee copy() {
	        return new Employee(this.getId(), this.getFirstName(),this.getLastName(),this.getBirthDate());
	 }


}

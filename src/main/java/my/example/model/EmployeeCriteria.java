package my.example.model;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCriteria  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String firstName;
	
	private String lastName;
	
	private LocalDate birthDate;

}

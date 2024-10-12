package my.example.model;

import java.io.Serializable;
import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Person implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id = UUID.randomUUID().toString();
	private String firstName;
	private String lastName;
	private String contact = "Phone";
	private String phone;
	private String email;
	private String selectedOption;
	private String option;
	
}

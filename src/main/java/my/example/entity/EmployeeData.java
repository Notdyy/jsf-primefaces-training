package my.example.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.example.converter.LocalDateToTimestampConverter;

@Slf4j
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "DATA_EMPLOYEE")
public class EmployeeData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "FNAME")
	private String firstName;
	
	@Column(name = "LNAME")
	private String lastName;
	
	@Column(name = "BIRTHDATE")
	@Convert(converter = LocalDateToTimestampConverter.class)
	private LocalDate birthDate;
	
	@PrePersist
	public void prePersist() {
		this.id = UUID.randomUUID().toString();
	}


}

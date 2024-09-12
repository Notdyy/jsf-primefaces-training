package my.example.service;

import java.io.Serializable;

import my.example.model.Person;

public class NameService implements Serializable {
	private static final long serialVersionUID = 1L;

	public String display(Person person) {
		return person.getFirstName()
				.concat(" ")
				.concat(person.getLastName());
	}
}

	

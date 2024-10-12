package my.example.service.impl;

import javax.enterprise.context.ApplicationScoped;

import my.example.model.Person;
import my.example.service.NameServiceable;


@ApplicationScoped
public class NameServiceimpl implements NameServiceable {
	
	@Override
	public String display(Person person) {
		return person.getFirstName()
				.concat(" ")
				.concat(person.getLastName());
	}
}

	

package my.example.view;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import my.example.model.Person;
import my.example.service.NameServiceable;

@SessionScoped
@Named("jsfBean")
public class JsfBean  implements Serializable{
	
	/**
	 * 
	 */
	private void writeObject(java.io.ObjectOutputStream stream)
	        throws IOException {
	    stream.defaultWriteObject();
	}

	private void readObject(java.io.ObjectInputStream stream)
	        throws IOException, ClassNotFoundException {
	    stream.defaultReadObject();
	}
	
	private static final long serialVersionUID = 1L;
	
	private Person person= new Person();
	private String fullName;
	
	@Inject
	private NameServiceable service;
	
	public String returnPage() {
		this.fullName = service.display(person);
		return "form-04-result";
	}
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public String getFullName() {
		return this.fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
}
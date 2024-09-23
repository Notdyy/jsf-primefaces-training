package my.example.view;

import java.io.IOException;
import java.io.Serializable;


import javax.faces.view.ViewScoped;
import javax.inject.Named;

import my.example.model.Person;
import my.example.service.NameService;


@ViewScoped
@Named("pfBean")
public class PrimefacesBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private void writeObject(java.io.ObjectOutputStream stream)
	        throws IOException {
	    stream.defaultWriteObject();
	}

	private void readObject(java.io.ObjectInputStream stream)
	        throws IOException, ClassNotFoundException {
	    stream.defaultReadObject();
	}
	private Person person = new Person();
	private NameService service = new NameService();
	private String fullName;


	public void submitButtonOnClick() {
		this.fullName = service.display(person);
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
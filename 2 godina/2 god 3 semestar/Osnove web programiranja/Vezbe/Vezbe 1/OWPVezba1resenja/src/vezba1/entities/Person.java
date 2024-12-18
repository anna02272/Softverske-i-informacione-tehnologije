package vezba1.entities;

import java.util.Date;

public class Person {

	private String firstName;
	private String lastName;
	
	// prosirenje za zadatak 4
	private Date date;

	public Person(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Person(String firstName, String lastName, Date date) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.date = date;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
